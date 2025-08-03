package it.oharkon;

import it.oharkon.commands.SpawnCommand;
import it.oharkon.commands.SpawnTabCompleter;
import it.oharkon.listeners.PlayerListener;
import it.oharkon.managers.ConfigManager;
import it.oharkon.managers.DataManager;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class ohFirstSpawn extends JavaPlugin {

    private ConfigManager configManager;
    private DataManager dataManager;

    @Override
    public void onEnable() {
        this.configManager = new ConfigManager(this);
        this.dataManager = new DataManager(this);

        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);

        PluginCommand spawnCommand = getCommand("ofs");
        if (spawnCommand != null) {
            SpawnCommand commandExecutor = new SpawnCommand(this);
            spawnCommand.setExecutor(commandExecutor);
            spawnCommand.setTabCompleter(new SpawnTabCompleter());
            }

        getLogger().info("ohFirstSpawn enabled plugin!");
    }

    @Override
    public void onDisable() {
        if (dataManager != null) {
            dataManager.saveData();
        }
        getLogger().info("ohFirstSpawn disabled plugin!");
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public DataManager getDataManager() {
        return dataManager;
    }
}