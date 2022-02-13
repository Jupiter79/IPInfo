package at.jupiter.ipinfo.commands;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdIP implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.hasPermission("ipinfo.ip")) {
            if (strings.length > 0) {
                Player target = Bukkit.getPlayer(strings[0]);

                if (target != null) {
                    commandSender.spigot().sendMessage(createMessage(target.getName(), target.getAddress().getAddress().toString().replace("/", "")));
                } else commandSender.sendMessage("§cThe player §6" + strings[0] + " §ccouldn't be found!");
            } else {
                if (commandSender instanceof Player) {
                    Player p = (Player) commandSender;

                    commandSender.spigot().sendMessage(createMessage(p.getName(), p.getAddress().getAddress().toString().replace("/", "")));
                } else commandSender.sendMessage("§cPlease specify a player!");
            }
        } else commandSender.sendMessage("§cInsufficient permissions!");

        return false;
    }

    TextComponent createMessage(String playerName, String ip) {
        TextComponent message = new TextComponent("§6" + playerName + " §ahas the IP §4" + ip);
        message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://ipinfo.io/" + ip));
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aClick for detailed IP info").create()));

        return message;
    }
}
