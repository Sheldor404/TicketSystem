package de.kyleonaut.ticketsystem.util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;

public class Builder {
    public ItemStack createItem(Material itemMaterial) {
        return new ItemStack(itemMaterial);
    }

    public ItemStack createItem(Material itemMaterial, String itemName) {
        ItemStack itemStack = new ItemStack(itemMaterial);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(itemName);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack createItem(Material itemMaterial, String itemName, int itemAmount) {
        ItemStack itemStack = new ItemStack(itemMaterial, itemAmount);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(itemName);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack createItem(Material itemMaterial, String itemName, int itemAmount, String itemLore) {
        ItemStack itemStack = new ItemStack(itemMaterial, itemAmount);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setLore(Collections.singletonList(itemLore));
        meta.setDisplayName(itemName);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack createItem(Material itemMaterial, String itemName, int itemAmount, ArrayList itemLore) {
        ItemStack itemStack = new ItemStack(itemMaterial, itemAmount);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setLore(itemLore);
        meta.setDisplayName(itemName);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack createItem(Material itemMaterial, String itemName, int itemAmount, ArrayList itemLore, Enchantment enchantment, int enchantmentLevel, ItemFlag itemFlag) {
        ItemStack itemStack = new ItemStack(itemMaterial, itemAmount);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setLore(itemLore);
        meta.setDisplayName(itemName);
        meta.addEnchant(enchantment, enchantmentLevel, false);
        meta.addItemFlags(new ItemFlag[]{itemFlag});
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}

