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
     * Check how many cookies a player has
     *
     * @param player The player to check the cookie balance of
     * @return the amount of cookies the player has
     */
    public static int getPlayerCookieCount(Player player) {
        return mConfig.getInt(player.getName() + ".cookies");
    }

    /**
     * Add cookies to a players balence
     *
     * @param player The player who will get more cookies
     * @return the new amount of cookies the player has
     */
    public static void addPlayerCookies(Player player, int amount) {
        int numCookies = mConfig.getInt(player.getName() + ".cookies", 0);
        int newCookies = numCookies + amount;

        mConfig.set(player.getName() + ".cookies", newCookies);
        CookieClicker.plugin.saveConfig();
    }

    /**
     * Remove cookies from a players balence
     *
     * @param player The player who will get less cookies
     * @return the new amount of cookies the player has
     */
    public static void removePlayerCookies(Player player, int amount) {
        int numCookies = mConfig.getInt(player.getName() + ".cookies", 0);
        int newCookies = numCookies - amount;

        mConfig.set(player.getName() + ".cookies", newCookies);
        CookieClicker.plugin.saveConfig();
    }

    /**
     * Remove cookies from a players balence
     *
     * @param player The player who will get new cookies
     * @return the new amount of cookies the player has
     */
    public static void setPlayerCookies(Player player, int amount) {
        mConfig.set(player.getName() + ".cookies", amount);
        CookieClicker.plugin.saveConfig();
    }
}
