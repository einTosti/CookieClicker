package de.eintosti.cookieclicker;

import de.eintosti.cookieclicker.commands.Boosters;
import de.eintosti.cookieclicker.commands.Clicker;
import de.eintosti.cookieclicker.commands.Cookies;
import de.eintosti.cookieclicker.listeners.InventoryClick;
import de.eintosti.cookieclicker.listeners.PlayerInteract;
import de.eintosti.cookieclicker.listeners.PlayerJoin;
import de.eintosti.cookieclicker.messages.Messages;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author einTosti
 */
public class CookieClicker extends JavaPlugin {
    public static CookieClicker plugin = null;

    @Override
    public void onEnable() {
        setInstance();

        registerListeners();
        registerExecutors();

        getConfig().options().copyDefaults(true);
        saveConfig();
        Messages.getInstance().createMessageFile();

        Bukkit.getConsoleSender().sendMessage("[CookieClicker] Plugin enabled");
    }

    @Override
    public void onDisable() {
        saveConfig();
        plugin = null;
        Bukkit.getConsoleSender().sendMessage("[CookieClicker] Plugin disabled");
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new InventoryClick(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteract(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
    }

    private void registerExecutors() {
        Bukkit.getPluginCommand("Boosters").setExecutor(new Boosters());
        Bukkit.getPluginCommand("Clicker").setExecutor(new Clicker());
        Bukkit.getPluginCommand("Cookies").setExecutor(new Cookies());
    }

    private void setInstance() {
        if (plugin == null) plugin = this;
        else getLogger().warning("The plugin was loaded twice: ERROR!");
    }
}
