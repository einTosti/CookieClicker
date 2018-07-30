package de.eintosti.cookieclicker.inventories;

import de.eintosti.cookieclicker.CookieClicker;
import de.eintosti.cookieclicker.misc.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * @author einTosti
 */
public class ClickerInventory {
    private static ClickerInventory instance;

    public static synchronized ClickerInventory getInstance() {
        if (instance == null) instance = new ClickerInventory();
        return instance;
    }

    private Inventory getClickerInventory(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, "§eCookie§6Clicker §e» §8Clicker");

        int numCookies = CookieClicker.plugin.getConfig().getInt(player.getName() + ".cookies", 0);
        int numBooster = CookieClicker.plugin.getConfig().getInt(player.getName() + ".booster");

        fillGuiWithGlass(inv);
        Utils.getInstance().addItemStack(inv, 13, Material.COOKIE, 0, "§eCookie§6Clicker", Utils.getInstance().getString("current_cookies").replace("%cookies%", String.valueOf(numCookies)), Utils.getInstance().getString("current_booster").replace("%booster%", String.valueOf(numBooster)), "§7", Utils.getInstance().getString("item_info"));

        return inv;
    }

    public void openInventory(Player player) {
        player.openInventory(getClickerInventory(player));
    }

    private void fillGuiWithGlass(Inventory inv) {
        for (int i = 0; i <= 9; i++)
            Utils.getInstance().addGlassPane(inv, i);
        for (int i = 17; i <= 26; i++)
            Utils.getInstance().addGlassPane(inv, i);
    }
}
