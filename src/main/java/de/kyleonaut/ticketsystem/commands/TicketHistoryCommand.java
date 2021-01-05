package de.kyleonaut.ticketsystem.commands;

import de.kyleonaut.ticketsystem.util.Config;
import de.kyleonaut.ticketsystem.util.InventoryHolder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class TicketHistoryCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("ticketsystem.history")) {
                try {
                    InventoryHolder.openTicketHistory(player);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                Config.sendMessage(player, "Messages.NoPermission");
            }
        }
        return false;
    }
}

