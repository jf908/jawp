package net.xyfe.spigot.jawp.command;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import net.xyfe.spigot.jawp.JAWP;

/**
 * CommandSetWarp
 */
public class CommandSetWarp implements CommandExecutor, TabCompleter {
  public static final String name = "setwarp";
  public static final String permission = "jawp.command.setwarp";

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

      if (sender instanceof Player) {
        boolean success = JAWP.instance.manager.createWarp(args[0], ((Player) sender).getLocation());
        if (!success) {
          sender.sendMessage("Warp already exists.");
        }
      } else {
        sender.sendMessage("Only players can make warps.");
      }
    }
    return true;
  }

  @Override
  public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
    if (!cmd.getName().equalsIgnoreCase(name))
      return null;
    if (args.length == 1) {
      return Arrays.asList();
    }
    return null;
  }
}