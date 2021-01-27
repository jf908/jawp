package net.xyfe.spigot.jawp.command;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import net.xyfe.spigot.jawp.JAWP;

/**
 * CommandWarps
 */
public class CommandWarps implements CommandExecutor, TabCompleter {
  public static final String name = "warps";
  public static final String permission = "jawp.command.warps";

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (!cmd.getName().equalsIgnoreCase(name))
      return false;

    if (!CmdUtil.hasPermission(sender, permission)) {
      return true;
    }

    sender.sendMessage(JAWP.instance.manager.getWarps().toString());
    return true;
  }

  @Override
  public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
    return null;
  }
}