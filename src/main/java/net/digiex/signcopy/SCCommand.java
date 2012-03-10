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
public class SCCommand implements CommandExecutor {

    private final SignCopy plugin;

    public SCCommand(SignCopy plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Sorry, only players can use SignCopy!");
            return true;
        }
        Player player = (Player) sender;
        if (player.hasPermission("signcopy.sc")) {
            if (!plugin.copying.containsKey(player)) {
                SignEntry entry = new SignEntry(1);
                plugin.copying.put(player, entry);
                player.sendMessage("SignCopy mode activated! Right click on a sign to select it for copy, then right click on a block to place the sign. Make sure you have atleast one Sign in your inventory.");
                return true;
            }
            plugin.copying.remove(player);
            player.sendMessage("SignCopy mode deactived.");
            return true;
        } else {
            player.sendMessage("You do not have permissions to use SignCopy.");
            return true;
        }
    }
}
