package de.eintosti.cookieclicker.api;

import de.eintosti.cookieclicker.misc.CookieManager;
import org.bukkit.entity.Player;

/**
 * @author einTosti
 */
public class CookieAPI {
    private CookieAPI() {
    }

    /**
     * @param player Player to be checked
     * @return Cookie Amount of cookies the player has
     */
    public static int getCookies(Player player) {
        return CookieManager.getPlayerCookieCount(player);
    }

    /**
     * @param player Player to receive cookies
     * @param amount Amount to be added
     */
    public static void addCookies(Player player, int amount) {
        CookieManager.addPlayerCookies(player, amount);
    }

    /**
     * @param player Player to lose cookies
     * @param amount Amount to be remove
     */
    public static void removeCookies(Player player, int amount) {
        CookieManager.removePlayerCookies(player, amount);
    }

    /**
     * @param player Player to receive/loose cookies
     * @param amount Amount to be set to
     */
    public static void setCookies(Player player, int amount) {
        CookieManager.setPlayerCookies(player, amount);
    }
}
