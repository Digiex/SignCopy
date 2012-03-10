package net.digiex.signcopy;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

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
        if (event.isCancelled()) {
            return;
        }
        if (plugin.copying.containsKey(player)) {
            if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getClickedBlock().getType() != Material.AIR) {
                if (event.getPlayer().getItemInHand().getTypeId() == plugin.tool) {
                    if (player.hasPermission("signcopy.sc")) {
                        SignEntry entry = plugin.copying.get(player);
                        if (entry.getMode() == 1) {
                            Block block = event.getClickedBlock();
                            BlockState state = block.getState();
                            if (state instanceof Sign) {
                                Sign sign = (Sign) state;
                                entry.setText(sign.getLines());
                                entry.setMode(2);
                                event.getPlayer().sendMessage("Sign selected.");
                            } else {
                                event.getPlayer().sendMessage("You need to select a sign!");
                            }
                        } else if (entry.getMode() == 2) {
                            Block block = event.getClickedBlock();
                            BlockState state = block.getState();
                            if (state instanceof Sign) {
                                Sign sign = (Sign) state;
                                for (int i = 0; i < 4; i++) {
                                    sign.setLine(i, entry.getText()[i]);
                                }
                                sign.update(true);
                                event.getPlayer().sendMessage("Sign copied!");
                            }
                        }
                    }
                }
            }
        }
    }
}
