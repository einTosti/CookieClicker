package de.eintosti.cookieclicker.commands;

import de.eintosti.cookieclicker.CookieClicker;
import de.eintosti.cookieclicker.messages.Messages;
import de.eintosti.cookieclicker.misc.Utils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

import static org.bukkit.Bukkit.getServer;

/**
 * @author einTosti
 */
public class Cookies implements CommandExecutor {
    private String mPrefix = Utils.getInstance().mPrefix;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        if (CookieClicker.plugin.getConfig().get(player.getName()) == null)
            Utils.getInstance().addPlayerToConfig(player);
        int numCookies = CookieClicker.plugin.getConfig().getInt(player.getName() + ".cookies", 0);
        String message = "";

        if (args.length == 0) {
            if (!player.hasPermission("cookies.self")) {
                Utils.getInstance().sendPermError(player);
                return true;
            }
            String cookiesMessage = getPluralMessage(numCookies, "player_cookies");
            message = cookiesMessage.replace("%cookies%", String.valueOf(numCookies));
        } else if (args.length == 1) {
            switch (args[0].toLowerCase()) {
                case "item":
                    if (!player.hasPermission("cookies.item")) {
                        Utils.getInstance().sendPermError(player);
                        return true;
                    }
                    player.getInventory().addItem(cookieItem());
                    break;
            }

            if (!player.hasPermission("cookies.others")) {
                Utils.getInstance().sendPermError(player);
                return true;
            }
            String targetPlayerName = args[0];

            if (doesTargetExist(targetPlayerName)) {
                int targetCookies = CookieClicker.plugin.getConfig().getInt(targetPlayerName + ".cookies", 0);
                String cookiesMessage = getPluralMessage(targetCookies, "target_cookies");
                message = cookiesMessage.replace("%target%", targetPlayerName).replace("%cookies%", String.valueOf(targetCookies));
            } else {
                message = Utils.getInstance().getString("target_unknown");
            }
        }
        Messages.getInstance().sendMessage(player, mPrefix.concat(message));
        return true;
    }

    private String getPluralMessage(int count, String keyPrefix) {
        String key = (count == 1 ? keyPrefix + "_singular" : keyPrefix + "_plural");
        return Utils.getInstance().getString(key);
    }

    private boolean doesTargetExist(String targetPlayerName) {
        String targetConfigName = CookieClicker.plugin.getConfig().getString(targetPlayerName + ".cookies");
        return targetConfigName != null;
    }

    @SuppressWarnings("deprecation")
    private ItemStack cookieItem() {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);

        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwner("QuadratCookie");
        meta.setDisplayName("§eCookie§6Clicker");

        ArrayList<String> lore = new ArrayList<>();
        lore.add("§e» §7Place this to start clicking.");
        meta.setLore(lore);

        skull.setItemMeta(meta);
        return skull;
    }
}

