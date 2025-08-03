package it.oharkon.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpawnTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            List<String> subcommands = Arrays.asList("set", "info", "reload");

            for (String subcommand : subcommands) {
                if (subcommand.toLowerCase().startsWith(args[0].toLowerCase())) {
                    if (hasPermissionForSubcommand(sender, subcommand)) {
                        completions.add(subcommand);
                    }
                }
            }
        }

        return completions;
    }

    private boolean hasPermissionForSubcommand(CommandSender sender, String subcommand) {
        if (!(sender instanceof Player)) {
            return false;
        }

        return switch (subcommand) {
            case "set", "info", "reload" -> sender.hasPermission("ohfirstspawn.set");
            default -> false;
        };
    }
}
