package de.kyleonaut.ticketsystem.util;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryHolder {
    static int id = 15;
    static Material empty = Material.STAINED_GLASS_PANE;

    public static void openKategorienGui(Player p) {
        Builder builder = new Builder();
        Inventory ticketinv = Bukkit.createInventory(null, InventoryType.HOPPER, "Kategorien");
        ticketinv.addItem(builder.createGlassPane(Builder.Color.RED, "§ePlot verschieben", 1, "§7➥ Beantrage eine Plotverschiebung"));
        ticketinv.addItem(builder.createGlassPane(Builder.Color.WHITE, "§ePlot melden", 1, "§7➥ Melde ein Plot"));
        ticketinv.addItem(builder.createGlassPane(Builder.Color.YELLOW, "§ePlot mergen", 1, "§7➥ Beantrage ein Plotmerge"));
        ticketinv.addItem(builder.createGlassPane(Builder.Color.BLUE, "§ePlot beantragen", 1, "§7➥ Beantrage ein Plot"));
        ticketinv.addItem(builder.createGlassPane(Builder.Color.LIME_GREEN,"§eEigenes Ticket",1,"§7➥ Erstelle dein eigenes Ticket"));
        p.openInventory(ticketinv);
    }


}