package net.xyfe.spigot.jawp.command;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import net.xyfe.spigot.jawp.JAWP;

/**
 * CommandWarp
 */
public class CommandWarp implements CommandExecutor, TabCompleter {
  public static final String name = "warp";
  public static final String permission = "jawp.command.warp";

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
        Location warpLocation = JAWP.instance.manager.getWarp(args[0]);
        if (warpLocation == null) {
          sender.sendMessage("Warp doesn't exist.");
        } else {
          ((Player) sender).teleport(warpLocation, TeleportCause.COMMAND);
        }
      } else {
        sender.sendMessage("Only players can teleport.");
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