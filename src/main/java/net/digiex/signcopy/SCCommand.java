package net.digiex.signcopy;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
public class SCCommand implements CommandExecutor{
    
    private final SignCopy plugin;
    
    public SCCommand(SignCopy plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
        if (cs instanceof Player) {
            Player player = (Player) cs;
            if (player.hasPermission("signcopy.sc")) {
                if (!plugin.signs.containsKey(player)) {
                    plugin.signs.put(player, null);
                    player.sendMessage("Sign copy mode activated!");
                } else {
                    plugin.signs.remove(player);
                    player.sendMessage("Sign copy mode deactivated.");
                }
                return true;
            }
        } else {
            plugin.log.info("Sorry, in-game only command.");
        }
        return false;
    }
    
}
