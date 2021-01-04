package de.kyleonaut.ticketsystem.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class ChatHandler implements Listener {

    @EventHandler
    public void onChatMessage(PlayerChatEvent event){
        //do stuff
    }
}
