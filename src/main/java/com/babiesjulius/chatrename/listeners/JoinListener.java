package com.babiesjulius.chatrename.listeners;

import com.babiesjulius.chatrename.Strings;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static com.babiesjulius.chatrename.Main.getInstance;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String customName = getInstance().getConfig().getString(player.getUniqueId().toString());
        player.setDisplayName(customName);
        player.setCustomName(customName);
        player.setPlayerListName(customName);

        event.setJoinMessage("");

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.sendMessage(String.format(new Strings(onlinePlayer.getLocale()).joined(), customName));
        }
    }
}
