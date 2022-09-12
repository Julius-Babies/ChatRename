package com.babiesjulius.chatrename;

import org.bukkit.ChatColor;

public class Strings {

    private final String locale;

    public Strings(String locale) {
        this.locale = locale;
    }

    public String username_reset() {
        switch (locale) {
            case "de_DE":
                return "Dein Chatname wurde auf deinen Spielername zurückgesetzt";
            default:
                return "Your chatname was reset to your playername";
        }
    }

    public String username_set_to() {
        switch (locale) {
            case "de_DE":
                return "Dein Chatname wurde auf '%s' gesetzt";
            default:
                return "Your chatname was set to '%s'";
        }
    }

    public String username_already_in_use() {
        switch (locale) {
            case "de_DE":
                return "Dieser Benutzername wird bereits verwendet";
            default:
                return "This name is already in use";
        }
    }

    public String you_set_username_to() {
        switch (locale) {
            case "de_DE":
                return "Der Chatname von %s wurde auf '%s' gesetzt";
            default:
                return "The chatname from %s were set to '%s'";
        }
    }

    public String username_set_by() {
        switch (locale) {
            case "de_DE":
                return "%s hat deinen Chatnamen auf '%s' gesetzt";
            default:
                return "%s set your chatname to '%s'";
        }
    }

    public String no_permission() {
        switch (locale) {
            case "de_DE":
                return "Du hast nicht die Berechtigung dazu";
            default:
                return "You don't have the permission to do that";
        }
    }

    public String joined() {
        switch (locale) {
            case "de_DE":
                return ChatColor.GREEN + "%s ist beigetreten";
            default:
                return ChatColor.GREEN + "%s joined the game";
        }
    }
}
