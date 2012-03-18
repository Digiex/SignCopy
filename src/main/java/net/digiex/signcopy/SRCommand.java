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
public class SRCommand implements CommandExecutor {
    
    private final SignCopy plugin;
    
    public SRCommand(SignCopy plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            plugin.loadConfig();
            plugin.log.info("SignCopy reloaded!");
        } else {
            Player player = (Player) sender;
            if (player.hasPermission("signcopy.sr")) {
                plugin.loadConfig();
                player.sendMessage("SignCopy reloaded!");
            } else {
                player.sendMessage("You can't reload Sign copy. You don't have permission.");
            }
        }
        return true;
    }
}
