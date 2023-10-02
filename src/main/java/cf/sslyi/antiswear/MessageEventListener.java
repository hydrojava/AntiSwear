package cf.sslyi.antiswear;

import cf.sslyi.antiswear.AntiSwear;
import cf.sslyi.antiswear.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;


public class MessageEventListener implements Listener {
    private AntiSwear plugin;
    public MessageEventListener(AntiSwear plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        if(!player.hasPermission("asw.bypass")) {
            String playerUUID;
            if(Bukkit.getServer().getOnlineMode()) {
                playerUUID = player.getUniqueId().toString();
            } else {
                playerUUID = player.getName().toLowerCase();
            }
            List<String> swearWords = plugin.getConfig().getStringList("words");
            String message = e.getMessage();
            boolean didPlayerSwear = false; // default
            for(String s : swearWords) {
                if(message.contains(s)) {
                    player.sendMessage(Utils.parseColorCodes("&6&l[Anti Swear]&f&c " + plugin.getConfig().getString("playerwarning").replace("%player%", player.getName())));
                    for(Player staff : Bukkit.getOnlinePlayers()) {
                        if(staff.hasPermission("asw.notify")) {
                            player.sendMessage(Utils.parseColorCodes("&6&l[Anti Swear]&f&c " + plugin.getConfig().getString("staffwarning").replace("%player%", player.getName())));
                        }
                    }
                    e.setCancelled(true);
                    break;

            }

        }



    }
}}
