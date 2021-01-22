package net.xyfe.spigot.jawp;

import org.bukkit.plugin.java.JavaPlugin;

import net.xyfe.spigot.jawp.command.CmdUtil;
import net.xyfe.spigot.jawp.command.CommandDelWarp;
import net.xyfe.spigot.jawp.command.CommandSetWarp;
import net.xyfe.spigot.jawp.command.CommandWarp;
import net.xyfe.spigot.jawp.command.CommandWarps;

public class JAWP extends JavaPlugin {
    public static JAWP instance;
    public WarpManager manager;

    private ConfigFile warpsConfig;

    private static final String configName = "warps.yml";

    public JAWP() {
        instance = this;
    }

    @Override
    public void onEnable() {
        warpsConfig = new ConfigFile();

        // Set path separator to something it can never be, a space
        warpsConfig.options().pathSeparator(' ');

        warpsConfig.createOrLoadFile(this, configName);

        manager = new WarpManager(warpsConfig);

        CmdUtil.registerCommand(CommandWarp.name, new CommandWarp());
        CmdUtil.registerCommand(CommandSetWarp.name, new CommandSetWarp());
        CmdUtil.registerCommand(CommandDelWarp.name, new CommandDelWarp());
        CmdUtil.registerCommand(CommandWarps.name, new CommandWarps());
    }
}