package de.kyleonaut.ticketsystem;

import de.kyleonaut.ticketsystem.commands.TicketCommand;
import de.kyleonaut.ticketsystem.commands.TicketHistoryCommand;
import de.kyleonaut.ticketsystem.commands.TicketModerationCommand;
import de.kyleonaut.ticketsystem.commands.TicketStatsCommand;
import de.kyleonaut.ticketsystem.events.ChatHandler;
import de.kyleonaut.ticketsystem.util.Config;
import de.kyleonaut.ticketsystem.util.MySqlClass;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public class TicketSystem extends JavaPlugin {

    private static MySqlClass con;

    @Override
    public void onEnable() {
        //Config
        Config.setConfig();

        //Commands
        this.getCommand("ticket").setExecutor(new TicketCommand());
        this.getCommand("ticketmod").setExecutor(new TicketModerationCommand());
        this.getCommand("tickethistory").setExecutor(new TicketHistoryCommand());
        this.getCommand("ticketstats").setExecutor(new TicketStatsCommand());

        //Events
        Bukkit.getPluginManager().registerEvents(new ChatHandler(),this);

        //MySQL
        con = new MySqlClass();
        try {
            con.execute("CREATE TABLE IF NOT EXISTS ticketsystem(index INT(10) PRIMARY KEY,uuid VARCHAR(60), tpye VARCHAR(25), args VARCHAR(255),datum VARCHAR(70))");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDisable() {
        con.disconnect();
    }
}
