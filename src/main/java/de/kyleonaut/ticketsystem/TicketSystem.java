package de.kyleonaut.ticketsystem;

import de.kyleonaut.ticketsystem.util.Config;
import org.bukkit.plugin.java.JavaPlugin;

public class TicketSystem extends JavaPlugin {

    @Override
    public void onEnable() {
        Config.setConfig();
    }
}
