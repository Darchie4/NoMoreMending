package com.gmail.mads456dk.nomoremending;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.logging.Level;

public class playerFishEventListener implements Listener {
    private final NoMoreMendingPlugin plugin;

    public playerFishEventListener(NoMoreMendingPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEvent(PlayerFishEvent event) {
        Item caughtItem = (Item) event.getCaught();
        if (caughtItem == null) {
            return;
        }
        ItemStack caughtItemStack = caughtItem.getItemStack();
        plugin.getLogger().log(Level.INFO, "Du fanget noget");

        if (!caughtItemStack.containsEnchantment(Enchantment.MENDING)) {
            return;
        }

        plugin.getLogger().log(Level.WARNING, "Du har fanget noget med mending");
        plugin.getLogger().log(Level.INFO, caughtItemStack.getType().toString());
        plugin.getLogger().log(Level.INFO, caughtItemStack.getItemMeta().getEnchants().keySet().toString());

        ConfigManager configManager = plugin.getConfigManager();
        if (caughtItemStack.getType().equals(Material.BOOK)) {
            ItemStack replacementItemStack = configManager.getConvertToItemStack();
            caughtItem.setItemStack(replacementItemStack);
            return;
        }

        caughtItemStack.removeEnchantment(Enchantment.MENDING);
        if (configManager.getConvertToEnchantment() == null) {
            return;
        }
        caughtItemStack.addEnchantment(configManager.getConvertToEnchantment(), 1);

    }
}
