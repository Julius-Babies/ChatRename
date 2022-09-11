package com.babiesjulius.chatrename.listeners;

import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.w3c.dom.Text;

import java.util.Objects;

import static com.babiesjulius.chatrename.Main.getInstance;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String name = getInstance().getConfig().get(player.getUniqueId().toString()) == null ? null : Objects.requireNonNull(getInstance().getConfig().get(player.getUniqueId().toString())).toString();
        if (name == null || name.equals("")) {
            event.setFormat(ChatColor.WHITE + "[" + player.getName() + "] " + event.getMessage());
        } else {
            TextComponent main = new TextComponent("[");
            main.setColor(net.md_5.bungee.api.ChatColor.WHITE);
            TextComponent nameComponent = new TextComponent(name);
            nameComponent.setColor(net.md_5.bungee.api.ChatColor.GOLD);
            nameComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(player.getName() + " (" + player.getUniqueId() + ")").create()));
            main.addExtra(nameComponent);

            TextComponent message = new TextComponent("] " + event.getMessage());
            main.addExtra(message);
            event.setCancelled(true);
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                onlinePlayer.spigot().sendMessage(main);
            }
        }
    }
}
