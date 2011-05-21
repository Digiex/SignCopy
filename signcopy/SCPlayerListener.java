package signcopy;

import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.block.*;
import org.bukkit.block.Sign;
import org.bukkit.World;
import org.bukkit.Location;
import org.bukkit.plugin.*;
import org.bukkit.material.*;

public class SCPlayerListener extends PlayerListener {
    Player player;
    private SCProps props;
    private SCCommands cmdHandle;
    private final SignCopy plugin;
    int[] directions;
    byte dir;
    
    public SCPlayerListener(SignCopy instance, SCProps props, SCCommands cmdHandle) {
        plugin = instance;
        this.props = props;
        this.cmdHandle = cmdHandle;
    }
    
    Plugin permissionsPlugin;

        MaterialData md;
    public void onPlayerInteract(PlayerInteractEvent event) {
        Location target = event.getPlayer().getLocation();
        player = event.getPlayer();
        World world = target.getWorld();
        directions = new int[16];
        directions[0] = 0;
        directions[1] = 1;
        directions[2] = 2;
        directions[3] = 3;
        directions[4] = 4;
        directions[5] = 5;
        directions[6] = 6;
        directions[7] = 7;
        directions[8] = 8;
        directions[9] = 9;
        directions[10] = 10;
        directions[11] = 11;
        directions[12] = 12;
        directions[13] = 13;
        directions[14] = 14;
        directions[15] = 15;
        Sign sign;
        if (cmdHandle.SCmode == 1) {
            permissionsPlugin = plugin.getServer().getPluginManager().getPlugin("Permissions");
            if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if (event.getPlayer().getItemInHand().getTypeId() == props.tool) {
                    if (plugin.permissionHandler.has(player, "sc.signcopy")) {
                        if (SignCopy.selected.get(event.getPlayer().getName()+"_mode") == null) {
                            final BlockState state = world.getBlockAt(new Location(world, event.getClickedBlock().getX(), event.getClickedBlock().getY(), event.getClickedBlock().getZ())).getState();
                            final Block firstsign = world.getBlockAt(new Location(world, event.getClickedBlock().getX(), event.getClickedBlock().getY(), event.getClickedBlock().getZ()));
                            if (state instanceof Sign) {
                                sign = (Sign)state;
                                SignCopy.selected.put(event.getPlayer().getName()+"_mode",1);
                                cmdHandle.lines = sign.getLines();
                                dir = firstsign.getData();
                                event.getPlayer().sendMessage(ChatColor.YELLOW + "[SignCopy] Sign selected.");
                            } else {
                                event.getPlayer().sendMessage(ChatColor.YELLOW + "[SignCopy] You need to select a sign!");
                            }
                        }
                        else if (SignCopy.selected.get(event.getPlayer().getName()+"_mode") == 1) {
                            int test = event.getClickedBlock().getY() + 1;
                            final Block block = world.getBlockAt(new Location(world, event.getClickedBlock().getX(), event.getClickedBlock().getY() + 1, event.getClickedBlock().getZ()));
                            if (block.getType() != Material.AIR) {
                                event.getPlayer().sendMessage(ChatColor.YELLOW + "[SignCopy] Error. You can't use that block as your target.");
                            } else {
                                SignCopy.selected.put(event.getPlayer().getName()+"_mode",2);
                                block.setType(Material.SIGN_POST);
                                block.setData(dir);
                                final BlockState state = world.getBlockAt(new Location(world, event.getClickedBlock().getX(), event.getClickedBlock().getY() + 1, event.getClickedBlock().getZ())).getState();
                                if (state instanceof Sign) {
                                    sign = (Sign)state;
                                    for (int i=0; i<4; i++) {
                                        sign.setLine(i, cmdHandle.lines[i]);
                                    }
                                sign.update(true);
                                event.getPlayer().sendMessage(ChatColor.YELLOW + "[SignCopy] Sign copied!");
                            }
                        }
                    }
                    else if (SignCopy.selected.get(event.getPlayer().getName()+"_mode") == 2) {
                        final BlockState state = world.getBlockAt(new Location(world, event.getClickedBlock().getX(), event.getClickedBlock().getY(), event.getClickedBlock().getZ())).getState();
                        final Block firstsign = world.getBlockAt(new Location(world, event.getClickedBlock().getX(), event.getClickedBlock().getY(), event.getClickedBlock().getZ()));
                        if (state instanceof Sign) {
                                sign = (Sign)state;
                                SignCopy.selected.put(event.getPlayer().getName()+"_mode",1);
                                cmdHandle.lines = sign.getLines();
                                dir = firstsign.getData();
                                event.getPlayer().sendMessage(ChatColor.YELLOW + "[SignCopy] Sign selected.");
                            } else {
                                event.getPlayer().sendMessage(ChatColor.YELLOW + "[SignCopy] You need to select a sign!");
                            }
                    }
                }
            }
        }
    }
}
}
