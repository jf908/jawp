package net.xyfe.spigot.jawp;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigFile extends YamlConfiguration {
  private File file;

  public ConfigFile() {
    super();
  }

  public void createOrLoadFile(JavaPlugin plugin, String fileName) {
    file = new File(plugin.getDataFolder(), fileName);
    if (!file.exists()) {
      saveFile();
    }

    try {
      load(file);
    } catch (IOException | InvalidConfigurationException e) {
      e.printStackTrace();
    }
  }

  public void saveFile() {
    try {
      save(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
