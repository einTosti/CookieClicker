package de.eintosti.cookieclicker.listeners;

import de.eintosti.cookieclicker.CookieClicker;
import de.eintosti.cookieclicker.misc.Material;
import de.eintosti.cookieclicker.misc.Sounds;
import de.eintosti.cookieclicker.misc.Utils;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Random;

import static org.bukkit.Bukkit.getServer;


/**
 * @author einTosti
 */
@SuppressWarnings("deprecation")
public class PlayerInteract implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        String name = player.getName();
        FileConfiguration config = CookieClicker.plugin.getConfig();

        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (isWithOffHand()) {
                if (event.getHand() != org.bukkit.inventory.EquipmentSlot.HAND) return;
            }

            Block block = event.getClickedBlock();
            if (block.getType() == Material.PLAYER_HEAD_BLOCK.getMaterial()) {
                Skull skull = (Skull) block.getState();

                if (skull.getOwner() == null) return;
                if (skull.getOwner().equals("QuadratCookie")) {
                    int randomNumber = new Random().nextInt(1000000000);
                    int numCookies = config.getInt(name + ".cookies", 0);
                    int booster = config.getInt(name + ".booster");
                    int newCookies = numCookies + booster;
                    config.set(name + ".cookies", newCookies);

                    final Item cookie = skull.getWorld().dropItem(skull.getLocation().add(0.5, 0.5, 0.5), cookieItem(randomNumber));
                    cookie.setPickupDelay(10000);

                    String string = "skull_cookies_plural";
                    if (newCookies == 1)
                        string = "skull_cookies_singular";
                    player.sendTitle("", Utils.getInstance().getString(string).replace("%cookies%", String.valueOf(newCookies)));

                    Sounds.playSound(player, Sounds.CHICKEN_EGG_POP);
                    getServer().getScheduler().scheduleSyncRepeatingTask(CookieClicker.plugin, () -> cookie.remove(), 5, 5);
                    switch (newCookies) {
                        case 100:
                        case 500:
                        case 1000:
                        case 2500:
                        case 5000:
                        case 10000:
                        case 25000:
                        case 50000:
                        case 100000:
                            Firework firework = (Firework) player.getWorld().spawnEntity(skull.getLocation().add(0.5, 0, 0.5), EntityType.FIREWORK);
                            FireworkMeta fireworkMeta = firework.getFireworkMeta();
                            firework.setFireworkMeta(fireworkMeta);
                            break;
                    }
                }
            }
        }
    }

    private boolean isWithOffHand() {
        String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        return Integer.parseInt(version.replaceAll("[^0-9]", "")) >= 190;
    }

    private ItemStack cookieItem(int random) {
        ItemStack itemStack = new ItemStack(Material.COOKIE.getMaterial());
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName("§eCookie §6" + random);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}

