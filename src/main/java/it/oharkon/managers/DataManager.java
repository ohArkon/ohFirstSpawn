package it.oharkon.managers;

import it.oharkon.ohFirstSpawn;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class DataManager {

    private final ohFirstSpawn plugin;
    private final Set<UUID> processedPlayers;

    public DataManager(ohFirstSpawn plugin) {
        this.plugin = plugin;
        this.processedPlayers = new HashSet<>();
        loadData();
    }

    private void loadData() {
        FileConfiguration config = plugin.getConfig();

        if (config.contains("players")) {
            List<String> playersList = config.getStringList("players");
            for (String uuidString : playersList) {
                try {
                    processedPlayers.add(UUID.fromString(uuidString));
                } catch (IllegalArgumentException ignored) {
                }
            }
        }

    }

    public void saveData() {
        String[] playersArray = processedPlayers.stream()
                .map(UUID::toString)
                .toArray(String[]::new);

        plugin.getConfig().set("players", playersArray);
        plugin.saveConfig();
    }

    public boolean isFirstTime(UUID playerUUID) {
        return !processedPlayers.contains(playerUUID);
    }

    public void markAsProcessed(UUID playerUUID) {
        processedPlayers.add(playerUUID);
        saveData();
    }

    public int getTotalProcessedPlayers() {
        return processedPlayers.size();
    }
}