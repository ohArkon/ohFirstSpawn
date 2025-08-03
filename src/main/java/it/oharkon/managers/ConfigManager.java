package it.oharkon.managers;

import it.oharkon.ohFirstSpawn;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Objects;

public class ConfigManager {

    private final ohFirstSpawn plugin;
    private FileConfiguration config;

    public ConfigManager(ohFirstSpawn plugin) {
        this.plugin = plugin;
        plugin.saveDefaultConfig();
        this.config = plugin.getConfig();
    }

    public void reloadConfig() {
        plugin.reloadConfig();
        this.config = plugin.getConfig();
    }

    public void setSpawnLocation(Location location) {
        config.set("spawn.world", Objects.requireNonNull(location.getWorld()).getName());
        config.set("spawn.x", location.getX());
        config.set("spawn.y", location.getY());
        config.set("spawn.z", location.getZ());
        config.set("spawn.yaw", location.getYaw());
        config.set("spawn.pitch", location.getPitch());

        plugin.saveConfig();

        if (isDebugEnabled()) {
            plugin.getLogger().info("Spawn salvato: " + formatLocation(location));
        }
    }

    public Location getSpawnLocation() {
        if (!config.contains("spawn.world") || Objects.requireNonNull(config.getString("spawn.world")).isEmpty()) {
            return null;
        }

        try {
            String worldName = config.getString("spawn.world");
            assert worldName != null;
            World world = Bukkit.getWorld(worldName);

            if (world == null) {
                if (isDebugEnabled()) {
                    plugin.getLogger().warning("Mondo spawn non trovato: " + worldName);
                }
                return null;
            }

            return new Location(
                    world,
                    config.getDouble("spawn.x"),
                    config.getDouble("spawn.y"),
                    config.getDouble("spawn.z"),
                    (float) config.getDouble("spawn.yaw"),
                    (float) config.getDouble("spawn.pitch")
            );
        } catch (Exception e) {
            plugin.getLogger().warning("Errore nel caricamento spawn: " + e.getMessage());
            return null;
        }
    }

    public boolean hasSpawnConfigured() {
        return config.contains("spawn.world") && !Objects.requireNonNull(config.getString("spawn.world")).isEmpty();
    }
    public String getMessage(String key) {
        String message = config.getString("messages." + key, "&cMessaggio non trovato: " + key);
        return message.replace('&', '§');
    }

    public String getMessage(String key, String... placeholders) {
        String message = getMessage(key);

        for (int i = 0; i < placeholders.length; i += 2) {
            if (i + 1 < placeholders.length) {
                message = message.replace("{" + placeholders[i] + "}", placeholders[i + 1]);
            }
        }

        return message;
    }

    public void sendMessage(Player player, String key, String... placeholders) {
        player.sendMessage(getMessage(key, placeholders));
    }

    public int getTeleportDelay() {
        return config.getInt("settings.teleport-delay", 20);
    }

    public boolean isDebugEnabled() {
        return config.getBoolean("settings.debug", false);
    }

    public boolean isPlaySoundsEnabled() {
        return config.getBoolean("settings.play-sounds", true);
    }

    public Sound getTeleportSound() {
        try {
            String soundName = config.getString("settings.teleport-sound", "ENTITY_ENDERMAN_TELEPORT");
            return Sound.valueOf(soundName);
        } catch (IllegalArgumentException e) {
            plugin.getLogger().warning("Suono non valido nel config: " + config.getString("settings.teleport-sound"));
            return Sound.ENTITY_ENDERMAN_TELEPORT;
        }
    }

    public void playTeleportSound(Player player) {
        if (isPlaySoundsEnabled()) {
            try {
                player.playSound(player.getLocation(), getTeleportSound(), 1.0f, 1.0f);
            } catch (Exception e) {
                if (isDebugEnabled()) {
                    plugin.getLogger().warning("Errore riproduzione suono per " + player.getName() + ": " + e.getMessage());
                }
            }
        }
    }
    private String formatLocation(Location loc) {
        return String.format("%.1f, %.1f, %.1f in %s (yaw: %.1f, pitch: %.1f)",
                loc.getX(), loc.getY(), loc.getZ(), Objects.requireNonNull(loc.getWorld()).getName(), loc.getYaw(), loc.getPitch());
    }
}