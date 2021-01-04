package de.kyleonaut.ticketsystem.commands;

import de.kyleonaut.ticketsystem.util.inventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TicketCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            inventory.openKategorienGui(player);
        }
        return false;
    }
}
