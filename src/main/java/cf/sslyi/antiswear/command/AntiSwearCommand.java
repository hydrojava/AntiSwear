package cf.sslyi.antiswear.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import cf.sslyi.antiswear.Utils;
import cf.sslyi.antiswear.AntiSwear;
import java.util.List;

public class AntiSwearCommand implements CommandExecutor {
    private AntiSwear plugin;

    public AntiSwearCommand(AntiSwear plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        if(args.length >= 1) {
             if(args[0].equalsIgnoreCase("add")) {
                if(sender.hasPermission("asw.add")) {
                    if(args.length >= 2) {
                        List<String> swearWords = plugin.getConfig().getStringList("words");
                        swearWords.add(args[1]);
                        plugin.getConfig().set("words", swearWords);
                        sender.sendMessage(Utils.parseColorCodes("&6&l[Anti Swear]&f Added " + args[1] + " to the list of swear words."));
                        plugin.saveConfig();
                    } else {
                        sender.sendMessage(Utils.parseColorCodes("&6&l[Anti Swear]&f&c Wrong usage! Correct usage: /asw add <word> "));
                    }
                } else {
                    sender.sendMessage(Utils.parseColorCodes("&6&l[Anti Swear]&f&c Nope! You do not have permissions to use this command."));
                }
            } else if(args[0].equalsIgnoreCase("remove")) {
                 if(sender.hasPermission("asw.remove")) {
                     if(args.length >= 2) {
                         if(listContains(args[1])) {
                             List<String> swearWords = plugin.getConfig().getStringList("words");
                             swearWords.remove(args[1]);
                             plugin.getConfig().set("words", swearWords);
                             sender.sendMessage(Utils.parseColorCodes("&6&l[Anti Swear]&f Removed " + args[1] + " from the list of swear words."));
                             plugin.saveConfig();
                         } else {
                             sender.sendMessage(Utils.parseColorCodes("&6&l[Anti Swear]&f &cAre you sure that " + args[1] + " is in the list of swear words?"));
                         }
                     } else {
                         sender.sendMessage(Utils.parseColorCodes("&6&l[Anti Swear]&f&c Wrong usage! Correct usage: /asw remove <word> "));
                     }
                 } else {
                     sender.sendMessage(Utils.parseColorCodes("&6&l[Anti Swear]&f&c Nope! You do not have permissions to use this command."));
                 }
             } else if(args[0].equalsIgnoreCase("list")) {
                 if(sender.hasPermission("asw.list")) {
                     sender.sendMessage(Utils.parseColorCodes("&6&l[Anti Swear]&f&c Current swear words : " + plugin.getConfig().getStringList("words").toString()));
                 } else {
                     sender.sendMessage(Utils.parseColorCodes("&6&l[Anti Swear]&f&c Nope! You do not have permissions to use this command."));
                 }
             } else if(args[0].equalsIgnoreCase("help")) {
                 sendHelpMessage(player);
             } else {
                 sendHelpMessage(player);
             }
        } else {
            sendHelpMessage(player);
        }




        return true;
    }
    private String getArgs(String[] args) {
        String toReturn = "";
        for(String s : args) {
            toReturn = toReturn + " " + s;
        }
        toReturn = toReturn.substring(1);
        return toReturn;
    }

    private boolean listContains(String s) {
        return plugin.getConfig().getStringList("words").contains(s);
    }

    public void sendHelpMessage(Player p) {
        p.sendMessage(Utils.parseColorCodes("&6------------Anti Swear--------&f"));
        p.sendMessage(Utils.parseColorCodes("- /as help - shows this page."));
        p.sendMessage(Utils.parseColorCodes("- /asw add <word> - removes a word from the list"));
        p.sendMessage(Utils.parseColorCodes("- /asw remove <word> - removes a word from the list"));
        p.sendMessage(Utils.parseColorCodes("- /asw list - lists the swear word list"));
    }
}
