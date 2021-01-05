package de.kyleonaut.ticketsystem.util;


import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;


public class Config {

    private static final FileConfiguration cfg = getFileConfiguration();

    public static void setConfig() {

        cfg.options().copyDefaults(true);

        /*
         * MySQL Config Data
         * */

        cfg.addDefault("Mysql.host", "");
        cfg.addDefault("Mysql.port", "");
        cfg.addDefault("Mysql.database", "");
        cfg.addDefault("Mysql.username", "");
        cfg.addDefault("Mysql.password", "");

        /*
         * Chat Messages
         * */
        cfg.addDefault("Messages.PlayerInTicketCooldown", " §7Du musst 2 Minuten warten, bevor du ein neues Ticket erstellen kannst.");
        cfg.addDefault("Messages.PlayerOutOfTicketCooldown", " §7Du kannst jetzt dein nächstes Ticket stellen.");
        cfg.addDefault("Messages.PlayerHasOpenTicket", " §7Du musst das Ticket ausfüllen, bevor du ein neues erstellst.");

        cfg.addDefault("Messages.PlotVerschieben", " §7Schreibe die §eaktuelle §7Plot-Id in den Chat. Schreibe §edanach §7die neue Plot-Id in den Chat.");
        cfg.addDefault("Messages.PlotMelden", " §7Schreibe §eals erstes die Plot-Id §7in den Chat. Schreibe §edanach §7kurz was du melden willst. ");
        cfg.addDefault("Messages.PlotMergen", " §7Schreibe die §ePlot-Ids §7in den Chat.");
        cfg.addDefault("Messages.PlotBeantragen", " §7Schreibe §eals erstes §7die Plot-Id in den Chat. Schreibe §edanach §7einen Grund warum du das Plot haben willst.");
        cfg.addDefault("Messages.EigenesTicket", " §7Schreibe dein Ticket in den Chat.");
        /*
         * Settings
         * */

        cfg.addDefault("Settings.Prefix", "§7[§eTicketSystem§7]");


        try {
            cfg.save(getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static File getFile() {
        return new File("plugins/TicketSystem", "config.yml");
    }

    private static FileConfiguration getFileConfiguration() {
        return YamlConfiguration.loadConfiguration(getFile());
    }


    public static FileConfiguration getCfg() {
        return cfg;
    }

    public static void sendMessage(Player player,String messageKey){
        player.sendMessage(cfg.getString("Settings.Prefix")+cfg.getString(messageKey));
    }


}

