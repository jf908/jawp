package net.xyfe.spigot.jawp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.util.NumberConversions;

public class WarpManager {
  private Map<String, Location> warps;
  private ConfigFile config;

  public WarpManager(ConfigFile config) {
    this.config = config;
    loadConfig();
  }

  public List<String> getWarps() {
    return warps.keySet().stream().sorted().collect(Collectors.toList());
  }

  /**
   * Gets an existing warp
   * 
   * @param name
   * @return location of warp or null if it doesn't exist
   */
  public Location getWarp(String name) {
    return warps.get(name);
  }

  /**
   * Creates a new warp
   * 
   * @param name
   * @param location
   * @return false is warp with same name already exists
   */
  public boolean createWarp(String name, Location location) {
    if (warps.containsKey(name)) {
      return false;
    }
    warps.put(name, location);
    saveConfig();
    return true;
  }

  /**
   * Deletes an existing warp
   * 
   * @param name
   * @return false if warp didn't exist
   */
  public boolean deleteWarp(String name) {
    if (!warps.containsKey(name)) {
      return false;
    }
    warps.remove(name);
    saveConfig();
    return true;
  }

  private void loadConfig() {
    warps = new HashMap<>();
    for (String key : config.getKeys(false)) {
      ConfigurationSection section = config.getConfigurationSection(key);
      warps.put(key,
          new Location(Bukkit.getWorld(section.getString("world")), section.getDouble("x"), section.getDouble("y"),
              section.getDouble("z"), NumberConversions.toFloat(section.getString("yaw")),
              NumberConversions.toFloat(section.getString("pitch"))));
    }
  }

  private void saveConfig() {
    for (String key : config.getKeys(false)) {
      config.set(key, null);
    }

    for (String key : warps.keySet()) {
      Location loc = warps.get(key);
      ConfigurationSection section = config.createSection(key);

      section.set("world", loc.getWorld().getName());
      section.set("x", loc.getX());
      section.set("y", loc.getY());
      section.set("z", loc.getZ());
      section.set("yaw", loc.getYaw());
      section.set("pitch", loc.getPitch());
    }

    config.saveFile();
  }
}
