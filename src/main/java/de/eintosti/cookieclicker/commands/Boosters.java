package de.eintosti.cookieclicker.commands;

import de.eintosti.cookieclicker.CookieClicker;
import de.eintosti.cookieclicker.inventories.BoosterInventory;
import de.eintosti.cookieclicker.messages.Messages;
import de.eintosti.cookieclicker.misc.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author einTosti
 */
public class Boosters implements CommandExecutor {
    private String mPrefix = Utils.getInstance().mPrefix;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        if (CookieClicker.plugin.getConfig().get(player.getName()) == null)
            Utils.getInstance().addPlayerToConfig(player);
        if (!player.hasPermission("cookies.boosters")) {
            Utils.getInstance().sendPermError(player);
            return true;
        }

        if (args.length == 0) {
            BoosterInventory.getInstance().openInventory(player);
        } else {
            Messages.getInstance().sendMessage(player, mPrefix + Utils.getInstance().getString("usage_booster"));
        }
        return true;
    }
}