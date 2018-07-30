package de.eintosti.cookieclicker.misc;

/**
 * @author einTosti
 */
public enum Material {
    AIR("AIR"),
    BLACK_STAINED_GLASS_PANE("STAINED_GLASS_PANE", "BLACK_STAINED_GLASS_PANE"),
    COOKIE("COOKIE"),
    LIME_DYE("INK_SACK", "LIME_DYE"),
    FILLED_MAP("MAP", "FILLED_MAP"),
    EMPTY_MAP("EMPTY_MAP", "LEGACY_EMPTY_MAP"),
    PLAYER_HEAD_BLOCK("SKULL", "PLAYER_HEAD"),
    PLAYER_HEAD_ITEM("SKULL_ITEM", "PLAYER_HEAD"),
    RED_DYE("INK_SACK", "ROSE_RED");

    private String[] mVersionDependentNames;
    private org.bukkit.Material mCached = null;

    Material(String... versionDependentNames) {
        mVersionDependentNames = versionDependentNames;
    }

    public org.bukkit.Material getMaterial() {
        if (mCached != null) return mCached;
        for (String name : mVersionDependentNames) {
            try {
                return mCached = org.bukkit.Material.valueOf(name);
            } catch (IllegalArgumentException ignore2) {
                // try next
            }
        }
        throw new IllegalArgumentException("Found no valid material name for " + name());
    }
}
