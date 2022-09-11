package com.babiesjulius.chatrename.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
            getInstance().getConfig().set(player.getUniqueId().toString(), "");
            getInstance().saveConfig();
            sender.sendMessage(normal("Dein Chatname wurde auf deinen Spielernamen zurückgesetzt"));
            player.setDisplayName(player.getName());
            player.setCustomName(player.getName());
            player.setPlayerListName(player.getName());
        } else if (args.length == 1) {
            player = (Player) sender;
            if (Bukkit.getPlayer(args[0]) == null) {
                getInstance().getConfig().set(player.getUniqueId().toString(), args[0]);
                getInstance().saveConfig();
                player.sendMessage(normal("Dein Chatname wurde auf '" + ChatColor.ITALIC + args[0] + ChatColor.RESET + ChatColor.WHITE + "' gesetzt"));
                player.setDisplayName(args[0]);
                player.setCustomName(args[0]);
                player.setPlayerListName(args[0]);
            } else {
                sender.sendMessage(error("Du darfst dich nicht als andere Spieler ausgeben"));
            }
        } else if (args.length == 2) {
            if (sender.isOp()) {
                player = Bukkit.getPlayer(args[1]);
                assert player != null;
                getInstance().getConfig().set(player.getUniqueId().toString(), args[0]);
                getInstance().saveConfig();
                player.setDisplayName(args[0]);
                player.setCustomName(args[0]);
                player.setPlayerListName(args[0]);
                sender.sendMessage(ChatColor.WHITE + "[" + ChatColor.GREEN + "ChatRename" + ChatColor.WHITE + "] Der Chatname von " + player.getName() + " wurde auf " + args[0] + " gesetzt");
                player.sendMessage(ChatColor.WHITE + "[" + ChatColor.GREEN + "ChatRename" + ChatColor.WHITE + "] Dein Chatname wurde von " + sender.getName() + " auf " + args[0] + " gesetzt");
            } else {
                sender.sendMessage(error("Du hast nicht die Rechte um andere Spieler umzubenennen"));
            }

        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> arguments = new ArrayList<>();
        if (args.length == 2) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.getName().startsWith(args[1])) arguments.add(player.getName());
            }
        }
        return arguments;
    }
}
