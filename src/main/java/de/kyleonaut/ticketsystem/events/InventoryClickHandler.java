package de.kyleonaut.ticketsystem.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickHandler implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if (event.getInventory().getTitle().equals("Kategorien")){
            event.setCancelled(true);
        }
    }
}
