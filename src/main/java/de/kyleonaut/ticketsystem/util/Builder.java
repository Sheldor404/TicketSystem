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

    public ItemStack createGlassPane(String color, String itemName, int itemAmount, ArrayList itemLore) {
        Material itemMaterial = Material.STAINED_GLASS_PANE;
        ItemStack itemStack = null;
        color = color.toLowerCase();
        switch (color){
            case "white":
                itemStack = new ItemStack(itemMaterial, itemAmount,(short) 0);
                break;
            case "orange":
                itemStack = new ItemStack(itemMaterial, itemAmount,(short) 1);
                break;
            case "magenta":
                itemStack = new ItemStack(itemMaterial, itemAmount,(short) 2);
                break;
            case "lightblue":
                itemStack = new ItemStack(itemMaterial, itemAmount,(short) 3);
                break;
            case "yellow":
                itemStack = new ItemStack(itemMaterial, itemAmount,(short) 4);
                break;
            case "lime":
                itemStack = new ItemStack(itemMaterial, itemAmount,(short) 5);
                break;
            case "pink":
                itemStack = new ItemStack(itemMaterial, itemAmount,(short) 6);
                break;
            case "gray":
                itemStack = new ItemStack(itemMaterial, itemAmount,(short) 7);
                break;
            case "lightgray":
                itemStack = new ItemStack(itemMaterial, itemAmount,(short) 8);
                break;
            case "cyan":
                itemStack = new ItemStack(itemMaterial, itemAmount,(short) 9);
                break;
            case "purple":
                itemStack = new ItemStack(itemMaterial, itemAmount,(short) 10);
                break;
            case "blue":
                itemStack = new ItemStack(itemMaterial, itemAmount,(short) 11);
                break;
            case "brown":
                itemStack = new ItemStack(itemMaterial, itemAmount,(short) 12);
                break;
            case "green":
                itemStack = new ItemStack(itemMaterial, itemAmount,(short) 13);
                break;
            case "red":
                itemStack = new ItemStack(itemMaterial, itemAmount,(short) 14);
                break;
            case "black":
                itemStack = new ItemStack(itemMaterial, itemAmount,(short) 15);
                break;
            default:
                itemStack = new ItemStack(itemMaterial, itemAmount);
        }

        ItemMeta meta = itemStack.getItemMeta();
        meta.setLore(itemLore);
        meta.setDisplayName(itemName);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack createGlassPane(String color, String itemName, int itemAmount, String itemLore) {
        Material itemMaterial = Material.STAINED_GLASS_PANE;
        ItemStack itemStack = null;
        color = color.toLowerCase();
        switch (color){
            case "white":
                itemStack = new ItemStack(itemMaterial, itemAmount,(short) 0);
                break;
            case "orange":
                itemStack = new ItemStack(itemMaterial, itemAmount,(short) 1);
                break;
            case "magenta":
                itemStack = new ItemStack(itemMaterial, itemAmount,(short) 2);
                break;
            case "lightblue":
                itemStack = new ItemStack(itemMaterial, itemAmount,(short) 3);
                break;
            case "yellow":
                itemStack = new ItemStack(itemMaterial, itemAmount,(short) 4);
                break;
            case "lime":
                itemStack = new ItemStack(itemMaterial, itemAmount,(short) 5);
                break;
            case "pink":
                itemStack = new ItemStack(itemMaterial, itemAmount,(short) 6);
                break;
            case "gray":
                itemStack = new ItemStack(itemMaterial, itemAmount,(short) 7);
                break;
            case "lightgray":
                itemStack = new ItemStack(itemMaterial, itemAmount,(short) 8);
                break;
            case "cyan":
                itemStack = new ItemStack(itemMaterial, itemAmount,(short) 9);
                break;
            case "purple":
                itemStack = new ItemStack(itemMaterial, itemAmount,(short) 10);
                break;
            case "blue":
                itemStack = new ItemStack(itemMaterial, itemAmount,(short) 11);
                break;
            case "brown":
                itemStack = new ItemStack(itemMaterial, itemAmount,(short) 12);
                break;
            case "green":
                itemStack = new ItemStack(itemMaterial, itemAmount,(short) 13);
                break;
            case "red":
                itemStack = new ItemStack(itemMaterial, itemAmount,(short) 14);
                break;
            case "black":
                itemStack = new ItemStack(itemMaterial, itemAmount,(short) 15);
                break;
            default:
                itemStack = new ItemStack(itemMaterial, itemAmount);
        }
        ItemMeta meta = itemStack.getItemMeta();
        meta.setLore(Collections.singletonList(itemLore));
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

