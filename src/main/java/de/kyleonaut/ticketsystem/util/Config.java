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
        cfg.addDefault("Messages.prefix", "§7[§eBP§7]§7§7");
        cfg.addDefault("Messages.no_permissions", " Du hast keine Rechte diesen Befehl auszuführen");
        cfg.addDefault("Messages.created_group", " Du hast die Gruppe §e%group_name% §7erstellt");
        cfg.addDefault("Messages.syntax_error", " Bist du dir sicher, dass du alles richtig eingegeben hast?");
        cfg.addDefault("Messages.group_exists", " Die Gruppe §e%group_name% §7existiert bereits");
        cfg.addDefault("Messages.group_does_not_exists", " Die Gruppe §e%group_name% §7existiert nicht ");
        cfg.addDefault("Messages.member_succesfully_added_to_group", " Du hast §e%player% §7zu §e%group_name% §7hinzugefügt");
        cfg.addDefault("Messages.group_edit_succesfull", " Du hast die Gruppe §e%group_name%§7 erfolgreich editiert");
        /*
         * Settings
         * */
        cfg.addDefault("Settings.displayStyle", "§7[%prefix%§7] %player% : %message%");


        try {
            cfg.save(getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static File getFile() {
        return new File("plugins/BetterChatPrefix", "config.yml");
    }

    private static FileConfiguration getFileConfiguration() {
        return YamlConfiguration.loadConfiguration(getFile());
    }


    public static FileConfiguration getCfg() {
        return cfg;
    }


}

