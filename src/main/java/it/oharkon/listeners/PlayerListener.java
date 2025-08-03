package it.oharkon.listeners;

import it.oharkon.ohFirstSpawn;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class PlayerListener implements Listener {

    private final ohFirstSpawn plugin;

    public PlayerListener(ohFirstSpawn plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();

        if (plugin.getDataManager().isFirstTime(playerUUID)) {

            plugin.getDataManager().markAsProcessed(playerUUID);

            int delay = plugin.getConfigManager().getTeleportDelay();
            new BukkitRunnable() {
                @Override
                public void run() {
                    teleportToSpawn(player);
                }
            }.runTaskLater(plugin, delay);
        }
    }

    private void teleportToSpawn(Player player) {
        if (!player.isOnline()) {
            return;
        }

        if (!plugin.getConfigManager().hasSpawnConfigured()) {
            return;
        }

        Location spawnLocation = plugin.getConfigManager().getSpawnLocation();

        if (spawnLocation == null) {
            return;
        }

        try {
            player.teleport(spawnLocation);

        } catch (Exception ignored) {
        }
    }
}
