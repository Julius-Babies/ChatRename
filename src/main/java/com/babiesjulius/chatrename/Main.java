package com.babiesjulius.chatrename;

import com.babiesjulius.chatrename.commands.RenameCommand;
import com.babiesjulius.chatrename.listeners.ChatListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static JavaPlugin instance;

    public static JavaPlugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        saveDefaultConfig();

        getCommand("rename").setExecutor(new RenameCommand());
        getCommand("rename").setTabCompleter(new RenameCommand());

        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static String error(String msg) {
        return ChatColor.WHITE + "[" + ChatColor.GREEN + "ChatRename" + ChatColor.WHITE + "] " + ChatColor.RED + msg;
    }

    public static String normal(String msg) {
        return ChatColor.WHITE + "[" + ChatColor.GREEN + "ChatRename" + ChatColor.WHITE + "] " + ChatColor.WHITE + msg;
    }
}
