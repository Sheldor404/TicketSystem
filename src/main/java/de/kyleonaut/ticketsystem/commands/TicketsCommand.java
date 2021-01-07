package de.kyleonaut.ticketsystem.commands;


import de.kyleonaut.ticketsystem.util.InventoryHolder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class TicketsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            try {
                InventoryHolder.openTicketOverview(player);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
