package de.eintosti.cookieclicker.listeners;

import de.eintosti.cookieclicker.inventories.ClickerInventory;
import de.eintosti.cookieclicker.misc.Sounds;
import de.eintosti.cookieclicker.misc.Utils;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import static de.eintosti.cookieclicker.CookieClicker.plugin;

/**
 * @author einTosti
 */
@SuppressWarnings("deprecation")
public class InventoryClick implements Listener {

    @EventHandler
    public void onInventoryClickClicker(InventoryClickEvent event) {
        if (!(event.getInventory().getName()).equals("§eCookie§6Clicker §e» §8Clicker")) return;
        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();
        FileConfiguration config = plugin.getConfig();
        int numCookies = config.getInt(player.getName() + ".cookies", 0);
        int booster = config.getInt(player.getName() + ".booster");
        int newCookies = numCookies + booster;

        ItemStack itemStack = event.getCurrentItem();
        if (itemStack == null || itemStack.getType() == Material.AIR || !itemStack.hasItemMeta()) return;
        Material itemType = itemStack.getType();

        if (itemType == Material.COOKIE) {
            Sounds.playSound(player, Sounds.CHICKEN_EGG_POP);
            config.set(player.getName() + ".cookies", newCookies);
            ClickerInventory.getInstance().openInventory(player);
        }
    }

    @EventHandler
    public void onInventoryClickBooster(InventoryClickEvent event) {
        if (!(event.getInventory().getName()).equals("§eCookie§6Clicker §e» §8Boosters")) return;
        event.setCancelled(true);

        ItemStack itemStack = event.getCurrentItem();
        if (itemStack == null || itemStack.getType() == Material.AIR || !itemStack.hasItemMeta()) return;
        Material itemType = itemStack.getType();

        Player player = (Player) event.getWhoClicked();
        FileConfiguration config = plugin.getConfig();
        int numCookies = config.getInt(player.getName() + ".cookies", 0);

        //x2
        if (event.getSlot() == 12 && itemType == Material.MAP || event.getSlot() == 21 && itemStack.getDurability() == 1)
            purchaseBooster(itemType, player, numCookies, 5000, 2);
        //x4
        if (event.getSlot() == 14 && itemType == Material.MAP || event.getSlot() == 23 && itemStack.getDurability() == 1)
            purchaseBooster(itemType, player, numCookies, 10000, 4);
        //x8
        if (event.getSlot() == 16 && itemType == Material.MAP || event.getSlot() == 25 && itemStack.getDurability() == 1)
            purchaseBooster(itemType, player, numCookies, 100000, 8);
    }

    private void purchaseBooster(Material itemType, Player player, int numCookies, int cookiesCost, int boosterValue) {
        if (itemType == Material.MAP) {
            String name = player.getName();
            FileConfiguration config = plugin.getConfig();

            if (numCookies >= cookiesCost) {
                int newCookies = numCookies - cookiesCost;

                config.set(name + ".cookies", newCookies);
                config.set(name + ".booster", boosterValue);
                plugin.saveConfig();

                player.closeInventory();
                Sounds.playSound(player, Sounds.ORB_PICKUP);
                player.sendTitle("§e x" + boosterValue, Utils.getInstance().getString("booster_purchased_success"));
            } else {
                player.closeInventory();
                Sounds.playSound(player, Sounds.ZOMBIE_WOODBREAK);
                player.sendTitle("", Utils.getInstance().getString("booster_purchased_failed"));
            }
        }
    }
}
