package it.oharkon.commands;

import it.oharkon.ohFirstSpawn;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SpawnCommand implements CommandExecutor {

    private final ohFirstSpawn plugin;

    public SpawnCommand(ohFirstSpawn plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(plugin.getConfigManager().getMessage("only-player"));
            return true;
        }

        if (!player.hasPermission("ohfirstspawn.set")) {
            plugin.getConfigManager().sendMessage(player, "no-permission");
            return true;
        }

        if (args.length == 0) {
            plugin.getConfigManager().sendMessage(player, "usage");
            return true;
        }

        if (args[0].equalsIgnoreCase("set")) {
            handleSetCommand(player);
            return true;
        }

        if (args[0].equalsIgnoreCase("info")) {
            handleInfoCommand(player);
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (player.hasPermission("ohfirstspawn.reload")) {
                plugin.getConfigManager().reloadConfig();
                player.sendMessage("§aConfig ricaricato con successo!");
            } else {
                plugin.getConfigManager().sendMessage(player, "no-permission");
            }
            return true;
        }

        plugin.getConfigManager().sendMessage(player, "usage");
        return true;
    }

    private void handleSetCommand(Player player) {
        Location location = player.getLocation();
        plugin.getConfigManager().setSpawnLocation(location);

        plugin.getConfigManager().sendMessage(player, "spawn-set");

        plugin.getConfigManager().sendMessage(player, "spawn-position",
                "x", String.format("%.1f", location.getX()),
                "y", String.format("%.1f", location.getY()),
                "z", String.format("%.1f", location.getZ()),
                "world", Objects.requireNonNull(location.getWorld()).getName());

        plugin.getConfigManager().sendMessage(player, "spawn-rotation",
                "yaw", String.format("%.1f", location.getYaw()),
                "pitch", String.format("%.1f", location.getPitch()));

        if (plugin.getConfigManager().isDebugEnabled()) {
            plugin.getLogger().info("Spawn impostato da " + player.getName() + " in: " +
                    location.getWorld().getName() + " " + location.getX() + "," + location.getY() + "," + location.getZ());
        }
    }

    private void handleInfoCommand(Player player) {
        if (plugin.getConfigManager().hasSpawnConfigured()) {
            Location spawn = plugin.getConfigManager().getSpawnLocation();
            if (spawn != null) {
                plugin.getConfigManager().sendMessage(player, "spawn-info");

                plugin.getConfigManager().sendMessage(player, "spawn-world",
                        "world", Objects.requireNonNull(spawn.getWorld()).getName());

                plugin.getConfigManager().sendMessage(player, "spawn-coordinates",
                        "x", String.format("%.1f", spawn.getX()),
                        "y", String.format("%.1f", spawn.getY()),
                        "z", String.format("%.1f", spawn.getZ()));
            } else {
                plugin.getConfigManager().sendMessage(player, "spawn-load-error");
            }
        } else {
            plugin.getConfigManager().sendMessage(player, "spawn-not-configured");
        }

        int totalPlayers = plugin.getDataManager().getTotalProcessedPlayers();
        plugin.getConfigManager().sendMessage(player, "players-processed",
                "count", String.valueOf(totalPlayers));
    }
}