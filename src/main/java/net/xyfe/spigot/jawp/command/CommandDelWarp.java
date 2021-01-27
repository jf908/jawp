package net.xyfe.spigot.jawp.command;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import net.xyfe.spigot.jawp.JAWP;

/**
 * CommandDelWarp
 */
public class CommandDelWarp implements CommandExecutor, TabCompleter {
  public static final String name = "delwarp";
  public static final String permission = "jawp.command.delwarp";

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (!cmd.getName().equalsIgnoreCase(name))
      return false;
    if (args.length < 1) {
      sender.sendMessage("There must be at least 1 argument.");
      return true;
    } else {
      if (!CmdUtil.hasPermission(sender, permission)) {
        return true;
      }

      boolean success = JAWP.instance.manager.deleteWarp(args[0]);
      if (!success) {
        sender.sendMessage("Warp doesn't exist.");
      }
    }
    return true;
  }

  @Override
  public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
    if (!cmd.getName().equalsIgnoreCase(name))
      return null;
    if (args.length == 1) {
      return CmdUtil.filterCompletions(JAWP.instance.manager.getWarps(), args[0]);
    }
    return null;
  }
}