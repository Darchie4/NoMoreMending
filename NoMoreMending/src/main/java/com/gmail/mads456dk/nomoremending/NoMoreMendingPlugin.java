package com.gmail.mads456dk.nomoremending;

import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class NoMoreMendingPlugin extends JavaPlugin {
    private final ConfigManager configManager = new ConfigManager(this);

    @Override
    public void onEnable() {

        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new playerFishEventListener(this), this);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }
}
