package com.ValksAutoReplanter.planter;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class Planter implements Listener {
	@EventHandler
	public void blockBreakEvent(BlockBreakEvent e) {
		replantCrops(e, Material.WHEAT, Material.WHEAT_SEEDS, 7);
		replantCrops(e, Material.POTATOES, Material.POTATO, 7);
		replantCrops(e, Material.CARROTS, Material.CARROT, 7);
		replantCrops(e, Material.BEETROOTS, Material.BEETROOT_SEEDS, 3);
		replantCrops(e, Material.NETHER_WART, Material.NETHER_WART, 3);
	}
	
	private void replantCrops(BlockBreakEvent e, Material plant, Material drop, int age) {
		Block b = e.getBlock();
		World w = b.getWorld();
		Player p = e.getPlayer();
		
		if (b.getType() != plant) return;
		
		Ageable crop = (Ageable) b.getBlockData();
		
		if (crop.getAge() != age) return;
		
		e.setCancelled(true);
		b.setType(Material.AIR);
		
		int amount = Math.random() < 0.5 ? 1 : 2;	
		for (int i = 0; i < amount; i++) {
			w.dropItem(b.getLocation(), new ItemStack(drop));
		}
		
		if (!removePlayerItem(p, drop)) return;
		
		b.setType(plant);
	}
	
	/*
	 * Removes a player item if it exists in the players inventory. Returns
	 * true if the item exists.
	 */
	private boolean removePlayerItem(Player p, Material item) {
		ItemStack[] inv = p.getInventory().getContents();
		for (ItemStack i : inv) {
			if (i.getType() == item) {
				i.setAmount(i.getAmount() - 1);
				return true;
			}
		}
		return false;
	}
}
