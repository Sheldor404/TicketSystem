package de.kyleonaut.ticketsystem.commands;

import de.kyleonaut.ticketsystem.TicketSystem;
import de.kyleonaut.ticketsystem.util.Builder;
import de.kyleonaut.ticketsystem.util.Config;
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

    private static ArrayList<Player> ticketPlayerCooldownArrayList = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
                Builder builder = new Builder();
                Inventory ticketinv = Bukkit.createInventory(null, InventoryType.HOPPER,"Kategorien");
                ticketinv.addItem(builder.createGlassPane("red","§ePlot verschieben",1,"§7➥ Beantrage eine Plotverschiebung"));
                ticketinv.addItem(builder.createGlassPane("white","§ePlot melden",1,"§7➥ Melde ein Plot"));
                ticketinv.addItem(builder.createGlassPane("lightblue","§ePlot mergen",1,"§7➥ Beantrage ein Plotmerge"));
                ticketinv.addItem(builder.createGlassPane("yellow","§ePlot beantragen",1,"§7➥ Beantrage ein Plot"));
                ticketinv.addItem(builder.createGlassPane("lime","§eEigenes Ticket",1,"§7➥ Erstelle dein eigenes Ticket"));
                player.openInventory(ticketinv);
        }
        return false;
    }
}
