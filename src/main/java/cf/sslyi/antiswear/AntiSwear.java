package cf.sslyi.antiswear;

import cf.sslyi.antiswear.command.AntiSwearCommand;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class AntiSwear extends JavaPlugin {
    private FileConfiguration config;
    private List<String> swearWords;
    private String playerWarningMessage;
    private MessageEventListener messageEventListener;



    @Override
    public void onEnable() {
        Metrics metrics = new Metrics(this, 18472);
        printConsoleMessage();
        config = getConfig();
        config.options().copyDefaults(true);
        saveDefaultConfig();
        swearWords = config.getStringList("words");
        playerWarningMessage = config.getString("playerwarning");
        getCommand("asw").setExecutor(new AntiSwearCommand(this));
        messageEventListener = new MessageEventListener(this);
        Bukkit.getPluginManager().registerEvents(messageEventListener, this);




    }

    public void printConsoleMessage() {
        Bukkit.getConsoleSender().sendMessage(Utils.parseColorCodes("&3&l>&7&m----------&3&l[ &b&oAntiSwear &3&l]&7&m--------&3&l<"));
        Bukkit.getConsoleSender().sendMessage(Utils.parseColorCodes("&3&l>                                    &3&l<"));
        Bukkit.getConsoleSender().sendMessage(Utils.parseColorCodes("&3&l>         &bAntiSwear V" + this.getDescription().getVersion() + "        &3&l<"));
        Bukkit.getConsoleSender().sendMessage(Utils.parseColorCodes("&3&l>              &9&oBy Hydro           &3&l<"));
        Bukkit.getConsoleSender().sendMessage(Utils.parseColorCodes("&3&l>                                    &3&l<"));
        Bukkit.getConsoleSender().sendMessage(Utils.parseColorCodes("&3&l>              &aEnabling ...          &3&l<"));
        Bukkit.getConsoleSender().sendMessage(Utils.parseColorCodes("&3&l>                                    &3&l<"));
        Bukkit.getConsoleSender().sendMessage(Utils.parseColorCodes("&3&l>&7&m------------------------------------&3&l<"));
    }


    @Override
    public void onDisable() {
        Bukkit.getLogger().info("I have been disabled.");
    }
}
