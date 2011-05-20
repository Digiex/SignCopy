package signcopy;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.ChatColor;

public class SCCommands {
    private final SignCopy plugin;
    int SCmode = 0;
    String[] lines;
    Player player;
    Plugin permissionsPlugin;
    
    public SCCommands(SignCopy instance) {
        this.plugin = instance;
    }
    
    public void setupCommands() {
        PluginCommand sc = plugin.getCommand("sc");
        permissionsPlugin = plugin.getServer().getPluginManager().getPlugin("Permissions");
        CommandExecutor commandExecutor = new CommandExecutor() {
            public boolean onCommand( CommandSender sender, Command command, String label, String[] args ) {
                if (sender instanceof Player) {
                    player = (Player)sender;
                }
                if (permissionsPlugin != null) {
                 if ((plugin).permissionHandler.has(player, "sc.signcopy")) {
                        if (label.equals("sc")) {
                            sc(sender);
                        }
                 }
                    } else {
                     if (player.isOp()) {
                         if (label.equals("sc")) {
                            sc(sender);
                        }
                     }
                 }
                  
                return true;
                    }
            };
        if (sc != null) {
            sc.setExecutor(commandExecutor);
        }
        }
    
    public void sc(CommandSender event) {
        if (SCmode == 0) {
            SCmode = 1;
            event.sendMessage(ChatColor.YELLOW + "[SignCopy] SignCopy mode actived! Right click on a sign to select it for copy, then right click on a block to place the sign. Make sure you have atleast one Sign in your inventory.");
        } else {
            SCmode = 0;
            SignCopy.selected.put(event+"_mode",2);
            event.sendMessage(ChatColor.YELLOW + "[SignCopy] SignCopy mode deactived!");
        }
    }
}
