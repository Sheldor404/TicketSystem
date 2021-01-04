package de.kyleonaut.ticketsystem.commands;

import de.kyleonaut.ticketsystem.TicketSystem;
import de.kyleonaut.ticketsystem.util.Builder;
import de.kyleonaut.ticketsystem.util.Config;
import de.kyleonaut.ticketsystem.util.inventory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;


import java.util.ArrayList;

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
