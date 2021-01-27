package net.xyfe.spigot.jawp.command;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import net.xyfe.spigot.jawp.JAWP;

/**
 * CommandUtil
 */
public class CmdUtil {
  public static void registerCommand(String name, CommandExecutor command) {
    PluginCommand cmd = JAWP.instance.getCommand(name);
    if (command instanceof TabCompleter) {
      cmd.setTabCompleter((TabCompleter) command);
    }
    cmd.setExecutor(command);
  }

  public static List<String> filterCompletions(List<String> list, String arg) {
    return filterCompletions(list.stream(), arg);
  }

  public static List<String> filterCompletions(Stream<String> stream, String arg) {
    return stream.filter(x -> x.startsWith(arg)).collect(Collectors.toList());
  }

  /**
   * Checks if the sender is a player with permission, if not sends them an error
   * message
   * 
   * @param sender
   * @param permission
   * @return
   */
  public static boolean hasPermission(CommandSender sender, String permission) {
    if (sender instanceof Player) {
      Player player = (Player) sender;
      boolean allowed = player.hasPermission(permission);
      if (!allowed) {
        sender.sendMessage("You do not have permission.");
      }
      return allowed;
    }
    return true;
  }
}