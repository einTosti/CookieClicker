package de.eintosti.cookieclicker.misc;

import de.eintosti.cookieclicker.CookieClicker;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

/**
 * @author einTosti
 */
public final class CookieManager {
    private static FileConfiguration mConfig = CookieClicker.plugin.getConfig();

    /**
     * @param player Player to be checked
     * @return Cookie amount of cookies the player has
     */
    public static int getCookieCount(Player player) {
        return mConfig.getInt(player.getName() + ".cookies");
    }

    /**
     * @param player Player to receive cookies
     * @param amount Amount to be added
     */
    public static void addCookies(Player player, int amount) {
        int numCookies = mConfig.getInt(player.getName() + ".cookies", 0);
        int newCookies = numCookies + amount;

        mConfig.set(player.getName() + ".cookies", newCookies);
        CookieClicker.plugin.saveConfig();
    }

    /**
     * @param player Player to lose cookies
     * @param amount Amount to be remove
     */
    public static void removeCookies(Player player, int amount) {
        int numCookies = mConfig.getInt(player.getName() + ".cookies", 0);
        int newCookies = numCookies - amount;

        mConfig.set(player.getName() + ".cookies", newCookies);
        CookieClicker.plugin.saveConfig();
    }

    /**
     * @param player Player to receive/loose cookies
     * @param amount Amount to be set to
     */
    public static void setCookies(Player player, int amount) {
        mConfig.set(player.getName() + ".cookies", amount);
        CookieClicker.plugin.saveConfig();
    }
}
