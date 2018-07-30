package de.eintosti.cookieclicker.misc;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

/**
 * @author einTosti
 */
public enum Sounds {
    ORB_PICKUP("ORB_PICKUP", "ENTITY_EXPERIENCE_ORB_PICKUP"),
    CHICKEN_EGG_POP("CHICKEN_EGG_POP", "ENTITY_CHICKEN_EGG"),
    ZOMBIE_WOODBREAK("ZOMBIE_WOODBREAK", "ENTITY_ZOMBIE_BREAK_DOOR_WOOD", "ENTITY_ZOMBIE_BREAK_WOODEN_DOOR");

    private String[] mVersionDependentNames;
    private Sound mCached = null;

    Sounds(String... versionDependentNames) {
        mVersionDependentNames = versionDependentNames;
    }

    public static void playSound(Player player, Sounds sounds) {
        player.playSound(player.getLocation(), sounds.getSound(), 1f, 1f);
    }

    private Sound getSound() {
        if (mCached != null) return mCached;
        for (String name : mVersionDependentNames) {
            try {
                return mCached = Sound.valueOf(name);
            } catch (IllegalArgumentException ignore2) {
                // try next
            }
        }
        throw new IllegalArgumentException("Found no valid sound name for " + name());
    }
}
