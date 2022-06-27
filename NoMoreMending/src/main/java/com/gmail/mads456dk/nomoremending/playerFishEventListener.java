package com.gmail.mads456dk.nomoremending;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

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
        if (!caughtItemStack.containsEnchantment(Enchantment.MENDING)) {
            return;
        }

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
