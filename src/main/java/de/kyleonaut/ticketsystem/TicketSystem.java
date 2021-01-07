package de.kyleonaut.ticketsystem;

import de.kyleonaut.ticketsystem.commands.TicketCommand;
import de.kyleonaut.ticketsystem.commands.TicketHistoryCommand;
import de.kyleonaut.ticketsystem.commands.TicketModerationCommand;
import de.kyleonaut.ticketsystem.commands.TicketsCommand;
import de.kyleonaut.ticketsystem.events.ChatHandler;
import de.kyleonaut.ticketsystem.events.HandlePlayerJoin;
import de.kyleonaut.ticketsystem.events.InventoryClickHandler;
import de.kyleonaut.ticketsystem.util.Config;
import de.kyleonaut.ticketsystem.util.MySqlClass;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public class TicketSystem extends JavaPlugin {

    private static MySqlClass con;
    private static TicketSystem plugin;

    @Override
    public void onEnable() {
        plugin = this;
        //Config
        Config.setConfig();

        //Commands
        this.getCommand("ticket").setExecutor(new TicketCommand());
        this.getCommand("ticketmod").setExecutor(new TicketModerationCommand());
        this.getCommand("tickethistory").setExecutor(new TicketHistoryCommand());
        this.getCommand("tickets").setExecutor(new TicketsCommand());


        //Events
        Bukkit.getPluginManager().registerEvents(new ChatHandler(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickHandler(), this);
        Bukkit.getPluginManager().registerEvents(new HandlePlayerJoin(), this);
        //MySQL
        con = new MySqlClass();
        try {

            con.execute("CREATE TABLE IF NOT EXISTS ticketsystem_tickets(id INT NOT NULL AUTO_INCREMENT, uuid_player VARCHAR(60),player_name VARCHAR(50), ticket_type VARCHAR(25), ticket_args VARCHAR(200),eingangs_datum VARCHAR(70),ticket_status VARCHAR(30),moderator_uuid VARCHAR(60), datum_abgabe VARCHAR(70), PRIMARY KEY(id))");
            System.out.println("[TicketSystem] MySQL Setup complete");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onDisable() {
        con.disconnect();
    }
    public static MySqlClass getCon() {
        return con;
    }

    public static TicketSystem getPlugin() {
        return plugin;
    }
}
