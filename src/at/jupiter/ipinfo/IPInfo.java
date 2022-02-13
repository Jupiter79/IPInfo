package at.jupiter.ipinfo;

import at.jupiter.ipinfo.commands.CmdIP;
import org.bukkit.plugin.java.JavaPlugin;

public class IPInfo extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage("§aORanks v" + getDescription().getVersion() + " has been successfully enabled!");

        getCommand("ip").setExecutor(new CmdIP());
    }
}
