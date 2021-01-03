package de.kyleonaut.ticketsystem;

import de.kyleonaut.ticketsystem.util.Config;
import org.bukkit.plugin.java.JavaPlugin;

public final class TicketSystem extends JavaPlugin {

    @Override
    public void onEnable() {
        Config.setConfig();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
