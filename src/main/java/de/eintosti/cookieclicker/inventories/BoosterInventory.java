package de.eintosti.cookieclicker.inventories;

import de.eintosti.cookieclicker.CookieClicker;
import de.eintosti.cookieclicker.misc.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;

/**
 * @author einTosti
 */
public class BoosterInventory {
    private static BoosterInventory instance;

    public static synchronized BoosterInventory getInstance() {
        if (instance == null) {
            instance = new BoosterInventory();
        }
        return instance;
    }

    private Inventory getBoosterInventory(Player player) {
        Inventory inv = Bukkit.createInventory(null, 36, "§eCookie§6Clicker §e» §8Boosters");
        fillGuiWithGlass(inv, player);

        // x1
        addBoosterPaper(player, inv, 10, Utils.getInstance().getString("booster_name").replace("%booster%", "x1"), "1,000", 1, 2, 4, 8);
        addBoosterDye(player, inv, 19, 1, 2, 4, 8);

        // x2
        addBoosterPaper(player, inv, 12, Utils.getInstance().getString("booster_name").replace("%booster%", "x2"), "5,000", 2, 4, 8);
        addBoosterDye(player, inv, 21, 2, 4, 8);

        // x4
        addBoosterPaper(player, inv, 14, Utils.getInstance().getString("booster_name").replace("%booster%", "x4"), "10,000", 4, 8);
        addBoosterDye(player, inv, 23, 4, 8);

        // x8
        addBoosterPaper(player, inv, 16, Utils.getInstance().getString("booster_name").replace("%booster%", "x8"), "100,000", 8);
        addBoosterDye(player, inv, 25, 8);

        return inv;
    }

    public void openInventory(Player player) {
        player.openInventory(getBoosterInventory(player));
    }

    private void addBoosterPaper(Player player, Inventory inv, int position, String displayName, String cost, Integer... boosterValues) {
        int booster = CookieClicker.plugin.getConfig().getInt(player.getName() + ".booster");
        Material material = Material.MAP;
        String lore = Utils.getInstance().getString("booster_cost").replace("%cost%", cost);

        if (Arrays.asList(boosterValues).contains(booster)) {
            material = Material.FILLED_MAP;
            lore = Utils.getInstance().getString("booster_already_purchased");
        }
        Utils.getInstance().addItemStack(inv, position, material, 0, displayName, lore);
    }

    private void addBoosterDye(Player player, Inventory inv, int position, Integer... boosterValues) {
        int booster = CookieClicker.plugin.getConfig().getInt(player.getName() + ".booster");
        int id = 1;
        String displayName = "§c✘";
        Material material = Material.ROSE_RED;

        if (Arrays.asList(boosterValues).contains(booster)) {
            id = 10;
            displayName = "§a✔";
            material = Material.LIME_DYE;
        }
        Utils.getInstance().addItemStack(inv, position, material, id, displayName);
    }

    private void fillGuiWithGlass(Inventory inv, Player player) {
        int numCookies = CookieClicker.plugin.getConfig().getInt(player.getName() + ".cookies", 0);
        Utils.getInstance().addSkull(inv, 4, Utils.getInstance().getString("booster_cookies_title"), player.getName(), Utils.getInstance().getString("booster_cookies_amount").replace("%cookies%", String.valueOf(numCookies)));

        for (int i = 0; i <= 3; i++)
            Utils.getInstance().addGlassPane(inv, i);
        for (int i = 5; i <= 9; i++)
            Utils.getInstance().addGlassPane(inv, i);
        for (int i = 17; i <= 18; i++)
            Utils.getInstance().addGlassPane(inv, i);
        for (int i = 26; i <= 35; i++)
            Utils.getInstance().addGlassPane(inv, i);
    }
}
