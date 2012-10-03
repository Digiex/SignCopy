package net.digiex.signcopy;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

/*
 * SignCopy 0.2 Copyright (C) 2012 ChrizC, xzKinGzxBuRnzx
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
public class SCListener implements Listener {

    private final SignCopy plugin;

    public SCListener(SignCopy plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        if (event.isCancelled()) {
            return;
        }
        if (event.getClickedBlock().getType() == Material.AIR) {
            return;
        }
        if (player.getItemInHand().getTypeId() != plugin.tool) {
            return;
        }
        if (!plugin.signs.containsKey(player)) {
            return;
        }
        Sign sign = plugin.signs.get(player);
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (sign == null) {
                return;
            }
            if (block.getState() instanceof org.bukkit.block.Sign) {
                org.bukkit.block.Sign s = (org.bukkit.block.Sign) block.getState();
                for (int i = 0; i < 4; i++) {
                    s.setLine(i, sign.getText()[i]);
                }
                s.update(true);
                player.sendMessage("Sign Pasted!");
                return;
            }
            if (player.getGameMode() == GameMode.SURVIVAL) {
                ItemStack stack = new ItemStack(323);
                if (!player.getInventory().contains(stack)) {
                    player.sendMessage("You don't have a sign in your inventory.");
                    return;
                } else {
                    if (stack.getAmount() > 1) {
                        player.getInventory().removeItem(stack);
                        player.getInventory().addItem(new ItemStack(323, stack.getAmount() - 1));
                    } else {
                        player.getInventory().removeItem(stack);
                    }
                    player.updateInventory();
                }
            }
            block = block.getRelative(event.getBlockFace());
            if (sign.getType() == Material.WALL_SIGN) {
                switch (event.getBlockFace()) {
                    case UP:
                        block.setTypeId(63, false);
                        block.getState().setData(new MaterialData(block.getType()));
                        break;
                    case DOWN:
                        block.setTypeId(63, false);
                        block.getState().setData(new MaterialData(block.getType()));
                        break;
                    case NORTH:
                        block.setTypeId(68, false);
                        block.setData((byte) 0x04, false);
                        block.getState().setData(new MaterialData(block.getType()));
                        break;
                    case SOUTH:
                        block.setTypeId(68, false);
                        block.setData((byte) 0x05, false);
                        block.getState().setData(new MaterialData(block.getType()));
                        break;
                    case EAST:
                        block.setTypeId(68, false);
                        block.setData((byte) 0x02, false);
                        block.getState().setData(new MaterialData(block.getType()));
                        break;
                    case WEST:
                        block.setTypeId(68, false);
                        block.setData((byte) 0x03, false);
                        block.getState().setData(new MaterialData(block.getType()));
                        break;
                }
                org.bukkit.block.Sign s = (org.bukkit.block.Sign) block.getState();
                for (int i = 0; i < 4; i++) {
                    s.setLine(i, sign.getText()[i]);
                }
                s.update(true);
                player.sendMessage("Sign pasted!");
            } else {
                block.setType(sign.getType());
                block.setData(sign.getData(), false);
                block.getState().setData(new MaterialData(block.getType()));
                org.bukkit.block.Sign s = (org.bukkit.block.Sign) block.getState();
                for (int i = 0; i < 4; i++) {
                    s.setLine(i, sign.getText()[i]);
                }
                s.update(true);
                player.sendMessage("Sign pasted!");
            }
        } else if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            if (block.getState() instanceof org.bukkit.block.Sign) {
                org.bukkit.block.Sign s = (org.bukkit.block.Sign) block.getState();
                sign = new Sign(block, s.getLines());
                plugin.signs.put(player, sign);
                player.sendMessage("Sign copied");
            }
        }
    }
}
