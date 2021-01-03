package xpbankmain;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;


public class Config {

        public static final FileConfiguration cfg = getFileConfiguration();

        public void setConfig(){

            cfg.options().copyDefaults(true);
            cfg.addDefault("Settings.Prefix","§f[§bHOME-CB§f] ");
            cfg.addDefault("Settings.Withdraw","auszahlen");
           cfg.addDefault("Settings.baltop","baltop");
            cfg.addDefault("Settings.Baltop.Message","§f----- §a Baltop §f-----");
            cfg.addDefault("Settings.Cooldown",1);
            cfg.addDefault("Settings.adminpermission","xpd.admin");
             cfg.addDefault("Settings.Deposit","einzahlen");
            cfg.addDefault("Settings.Set","set");
            cfg.addDefault("Settings.balance","balance");
            cfg.addDefault("Settings.help","balance");

            
            try {
                cfg.save(getFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private static File getFile(){
            return new File("plugins/Xpbank","config.yml");
        }

        private static FileConfiguration getFileConfiguration(){
            return YamlConfiguration.loadConfiguration(getFile());
        }

    }

