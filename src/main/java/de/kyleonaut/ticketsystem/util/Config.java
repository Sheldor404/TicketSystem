package de.kyleonaut.ticketsystem.util;


import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

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

        /*
         * Settings
         * */



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


}

