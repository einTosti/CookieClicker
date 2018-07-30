package de.eintosti.cookieclicker.commands;

import de.eintosti.cookieclicker.inventories.ClickerInventory;
import de.eintosti.cookieclicker.messages.Messages;
import de.eintosti.cookieclicker.misc.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author einTosti
 */
public class Clicker implements CommandExecutor {
    private String mPrefix = Utils.getInstance().mPrefix;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        switch (args.length) {
            case 0:
                ClickerInventory.getInstance().openInventory(player);
                break;
            default:
                Messages.getInstance().sendMessage(player, mPrefix + Utils.getInstance().getString("usage_clicker"));
                break;
        }
        return true;
    }
}