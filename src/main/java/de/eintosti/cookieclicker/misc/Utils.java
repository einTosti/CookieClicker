package de.eintosti.cookieclicker.misc;

import de.eintosti.cookieclicker.CookieClicker;
import de.eintosti.cookieclicker.messages.Messages;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

/**
 * @author einTosti
 */
public class Utils {
    public final String mPrefix = getString("prefix");
    private static Utils instance;

    public static synchronized Utils getInstance() {
        if (instance == null) instance = new Utils();
        return instance;
    }

    public String getString(String string) {
        try {
            return Messages.getInstance().mMessageData.get(string).replace("&", "§");
        } catch (NullPointerException e) {
            Messages.getInstance().createMessageFile();
            return getString(string);
        }
    }

    public void sendPermError(Player player) {
        Messages.getInstance().sendMessage(player, mPrefix + getString("no_permissions"));
    }

    public void addPlayerToConfig(Player player) {
        String name = player.getName();
        FileConfiguration config = CookieClicker.plugin.getConfig();

        int cookies = config.getInt(name + ".cookies");
        if (cookies < 0)
            config.set(name + ".cookies", 0);
        int booster = config.getInt(name + ".booster");
        if (booster <= 0)
            config.set(name + ".booster", 1);
        CookieClicker.plugin.saveConfig();
    }

    public void addItemStack(Inventory inv, int position, org.bukkit.Material material, int id, String displayName, String... lore) {
        ItemStack itemStack = new ItemStack(material, 1, (byte) id);
        ItemMeta meta = itemStack.getItemMeta();

        meta.setDisplayName(displayName);
        meta.setLore(Arrays.asList(lore));
        meta.addItemFlags(ItemFlag.values());

        itemStack.setItemMeta(meta);
        inv.setItem(position, itemStack);
    }

    @SuppressWarnings("deprecation")
    public void addSkull(Inventory inv, int position, String displayName, String skullOwner, String... lore) {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD_ITEM.getMaterial(), 1, (short) 3);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();

        skullMeta.setOwner(skullOwner);
        skullMeta.setDisplayName(displayName);
        skullMeta.setLore(Arrays.asList(lore));
        skullMeta.addItemFlags(ItemFlag.values());
        skull.setItemMeta(skullMeta);

        inv.setItem(position, skull);
    }

    public void addGlassPane(Inventory inv, int position) {
        ItemStack itemStack = new ItemStack(Material.BLACK_STAINED_GLASS_PANE.getMaterial(), 1, (byte) 15);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(" ");
        itemStack.setItemMeta(meta);
        inv.setItem(position, itemStack);
    }
}