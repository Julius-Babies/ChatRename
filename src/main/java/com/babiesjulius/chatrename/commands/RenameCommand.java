package com.babiesjulius.chatrename.commands;

import com.babiesjulius.chatrename.Strings;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

import static com.babiesjulius.chatrename.Main.*;

public class RenameCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String[] args) {
        Player player;

        if (args.length == 0) {
            player = (Player) sender;
            Strings target = new Strings(player.getLocale());
            getInstance().getConfig().set(player.getUniqueId().toString(), "");
            getInstance().saveConfig();
            sender.sendMessage(normal(target.username_reset()));
            player.setDisplayName(player.getName());
            player.setCustomName(player.getName());
            player.setPlayerListName(player.getName());
        } else if (args.length == 1) {
            player = (Player) sender;
            Strings target = new Strings(player.getLocale());
            if (Bukkit.getPlayer(args[0]) == null) {
                getInstance().getConfig().set(player.getUniqueId().toString(), args[0]);
                getInstance().saveConfig();
                player.sendMessage(normal(String.format(target.username_set_to(), args[0])));
                player.setDisplayName(args[0]);
                player.setCustomName(args[0]);
                player.setPlayerListName(args[0]);
            } else {
                sender.sendMessage(error(target.username_already_in_use()));
            }
        } else if (args.length == 2) {
            Strings senderStrings = sender instanceof Player ? new Strings(((Player) sender).getLocale()) : new Strings("en_US");
            if (sender.isOp()) {
                player = Bukkit.getPlayer(args[1]);
                assert player != null;
                Strings target = new Strings(player.getLocale());
                getInstance().getConfig().set(player.getUniqueId().toString(), args[0]);
                getInstance().saveConfig();
                player.setDisplayName(args[0]);
                player.setCustomName(args[0]);
                player.setPlayerListName(args[0]);
                sender.sendMessage(normal(String.format(senderStrings.you_set_username_to(), args[1], args[0])));
                player.sendMessage(normal(String.format(target.username_set_by(), sender instanceof Player ? ((Player) sender).getDisplayName() : sender.getName(), args[0])));
            } else {
                sender.sendMessage(error(senderStrings.no_permission()));
            }

        }

        return true;
    }

    @Override
    public List<String> onTabComplete(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String[] args) {
        List<String> arguments = new ArrayList<>();
        if (args.length == 2) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.getName().startsWith(args[1])) arguments.add(player.getName());
            }
        }
        return arguments;
    }
}
