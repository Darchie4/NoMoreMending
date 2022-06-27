package com.gmail.mads456dk.nomoremending;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class ConfigManager {
    private Enchantment convertToEnchantment;
    private ItemStack convertToItemStack;

    public ConfigManager(NoMoreMendingPlugin plugin) {
        FileConfiguration config = plugin.getConfig();

        String convertMendingEnchantToString = config.getString("convertMendingEnchantTo");
        if (convertMendingEnchantToString != null && !convertMendingEnchantToString.equals("")) {
            NamespacedKey enchantKey = NamespacedKey.minecraft(convertMendingEnchantToString.toLowerCase());
            this.convertToEnchantment = Enchantment.getByKey(enchantKey);
        } else {
            this.convertToEnchantment = null;
        }

        String materialString = config.getString("convertMendingBookTo");
        if (materialString == null) {
            plugin.getLogger().log(Level.SEVERE, "No material in \"convertMendingBookTo\" was found");
            return;
        }
        Material material = Material.matchMaterial(materialString);
        if (material == null) {
            plugin.getLogger().log(Level.SEVERE, "The material configured in \"convertMendingBookTo\" does not exist");
            return;
        }

        this.convertToItemStack = new ItemStack(material);
    }

    public Enchantment getConvertToEnchantment() {
        return convertToEnchantment;
    }

    public ItemStack getConvertToItemStack() {
        return convertToItemStack;
    }
}
