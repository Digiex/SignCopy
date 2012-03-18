package net.digiex.signcopy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * SignCopy 0.2 Copyright (C) 2012 ChrizC, xzKinGzxBuRnzx
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
public class SignCopy extends JavaPlugin {

    public Logger log;
    public int tool = 280;
    public HashMap<Player, Sign> signs = new HashMap<Player, Sign>();
    
    private File configFile;
    private FileConfiguration config;
    private final SCListener listener = new SCListener(this);

    @Override
    public void onDisable() {
        log.info("is now disabled.");
    }

    @Override
    public void onEnable() {
        log = getLogger();
        config = getConfig();
        configFile = new File(getDataFolder(), "config.yml");
        if (configFile.exists()) {
            loadConfig();
        } else {
            saveConfig();
        }
        registerEvents();
        registerCommands();
        log.info("is now enabled!");
    }

    public void loadConfig() {
        try {
            config.load(configFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        tool = config.getInt("toolID", 280);
    }

    @Override
    public void saveConfig() {
        config.set("toolID", 280);
        config.options().header("Be sure to use /sr if you change any settings here while the server is running.");
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(listener, this);
    }

    private void registerCommands() {
        getCommand("signreload").setExecutor(new SRCommand(this));
        getCommand("signcopy").setExecutor(new SCCommand(this));
    }
}
