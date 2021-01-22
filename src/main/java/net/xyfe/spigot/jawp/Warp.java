package net.xyfe.spigot.jawp;

import java.util.LinkedHashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

public class Warp implements ConfigurationSerializable {
  private Location location;

  public Warp(Location location) {
    this.location = location;
  }

  public Warp(Map<String, Object> map) {
  }

  public Location getLocation() {
    return location;
  }

  @Override
  public Map<String, Object> serialize() {
    Map<String, Object> map = new LinkedHashMap<>();

    map.put("world", location.getWorld());
    map.put("x", location.getX());
    map.put("y", location.getY());
    map.put("z", location.getZ());
    map.put("yaw", location.getYaw());
    map.put("pitch", location.getPitch());

    return map;
  }

}
