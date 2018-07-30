package de.eintosti.cookieclicker.messages;

import de.eintosti.cookieclicker.CookieClicker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author einTosti
 */
public class Messages {
    public HashMap<String, String> mMessageData = new HashMap<>();

    private static Messages instance;

    public static synchronized Messages getInstance() {
        if (instance == null) instance = new Messages();
        return instance;
    }

    public void createMessageFile() {
        File file = new File(CookieClicker.plugin.getDataFolder() + File.separator + "messages.yml");
        if (!file.exists()) {
            Bukkit.getConsoleSender().sendMessage("[CookieClicker] messages.yml file not found");
            try {
                Bukkit.getConsoleSender().sendMessage(" » Creating messages.yml file...");
                file.createNewFile();
                Bukkit.getConsoleSender().sendMessage(" » messages.yml file created.");
            } catch (IOException e) {
                Bukkit.getConsoleSender().sendMessage(" » Error while creating file!");
                Bukkit.getConsoleSender().sendMessage(" » Reload the server (?)");
                e.printStackTrace();
            }
        }
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        StringBuilder sb = new StringBuilder();
        addLine(sb, "########################################");
        addLine(sb, "#      Cookie Clicker by einTosti      #");
        addLine(sb, "#                                      #");
        addLine(sb, "#             Messages File             #");
        addLine(sb, "########################################");
        addLine(sb, "");
        addLine(sb, "# Plugin prefix");
        setMessage(sb, config, "prefix", "&8● &eCookie&6Clicker &e» ");
        addLine(sb, "");
        addLine(sb, "# /booster & /clicker - usage Messages");
        setMessage(sb, config, "usage_booster", "&7Usage&8: &e/boosters");
        setMessage(sb, config, "usage_clicker", "&7Usage&8: &e/clicker");
        addLine(sb, "");
        addLine(sb, "# No permissions - message");
        setMessage(sb, config, "no_permissions", "&7No permissions!");
        addLine(sb, "");
        addLine(sb, "# /cookies - Messages");
        setMessage(sb, config, "player_cookies_singular", "&7You have &e%cookies% &6Cookie&7.");
        setMessage(sb, config, "player_cookies_plural", "&7You have &e%cookies% &6Cookies&7.");
        addLine(sb, "");
        addLine(sb, "# /cookies [player] - Messages");
        setMessage(sb, config, "target_cookies_singular", "&7%target% has &e%cookies% &6Cookie&7.");
        setMessage(sb, config, "target_cookies_plural", "&7%target% has &e%cookies% &6Cookies&7.");
        setMessage(sb, config, "target_unknown", "&7That player was not found!");
        addLine(sb, "");
        addLine(sb, "# CookieClicker skull - click message");
        setMessage(sb, config, "skull_cookies_singular", "&e%cookies% &6Cookie");
        setMessage(sb, config, "skull_cookies_plural", "&e%cookies% &6Cookies");
        addLine(sb, "");
        addLine(sb, "# Clicker GUI - item lore");
        setMessage(sb, config, "current_cookies", "&7You have &e%cookies% &6Cookies");
        setMessage(sb, config, "current_booster", "&7Current Booster&8: &ex%booster%");
        setMessage(sb, config, "item_info", "&6» &7Click to get some more!");
        addLine(sb, "");
        addLine(sb, "# Booster GUI");
        setMessage(sb, config, "booster_cookies_title", "&eYour Cookies");
        setMessage(sb, config, "booster_cookies_amount", "&7» &6%cookies% Cookies");
        setMessage(sb, config, "booster_name", "&e%booster% Booster");
        setMessage(sb, config, "booster_already_purchased", "&6Already purchased");
        setMessage(sb, config, "booster_cost", "&7Cost&8: &6%cost% Cookies");
        setMessage(sb, config, "booster_purchased_success", "&6Booster purchased");
        setMessage(sb, config, "booster_purchased_failed", "&cNot enough cookies");

        try {
            FileWriter fw = new FileWriter(file, false);
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String message : config.getConfigurationSection("").getKeys(false)) {
            mMessageData.put(message, config.getString(message));
        }
    }

    private void addLine(StringBuilder sb, String value) {
        sb.append(value).append("\n");
    }

    private void setMessage(StringBuilder sb, FileConfiguration config, String key, String defaultValue) {
        String value = config.getString(key, defaultValue);
        sb.append(key).append(": '").append(value).append("'").append("\n");
    }

    public void sendMessage(Player player, String string) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', string));
    }
}
