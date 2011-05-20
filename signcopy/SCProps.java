package signcopy;

import java.io.*;
import java.util.*;
import org.bukkit.util.config.Configuration;

public class SCProps {
    private final SignCopy plugin;
    Configuration file;
    int tool;
    
    public SCProps(SignCopy instance) {
        plugin = instance;
        
    }
    public void doConfig() {
        file = new Configuration(new File(plugin.getDataFolder(), "properties.yml"));
        file.load();
        if (new File(plugin.getDataFolder(),"properties.yml").exists()) {
            System.out.println("[SignCopy] Configuration file loaded!");
        } else {
            file.setProperty("toolID", 280);
            file.save();
            System.out.println("[SignCopy] Configuration file created with default values!");
        }
        
        //Get configs
        tool = file.getInt("toolID", tool);
    }
    public void relConfig() {
        file = new Configuration(new File(plugin.getDataFolder(), "properties.yml"));
        file.load();
        if (new File(plugin.getDataFolder(),"properties.yml").exists()) {
            System.out.println("[SignCopy] Configuration file reloaded!");
        }
        //Get configs
        tool = file.getInt("toolID", tool);
    }
}
