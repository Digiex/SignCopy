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
import org.bukkit.material.MaterialData;
import org.bukkit.material.*;
import java.lang.*;

public class SCPlayerListener extends PlayerListener {
    Player player;
    private SCProps props;
    private SCCommands cmdHandle;
    private final SignCopy plugin;
    String[] directions;
    
    public SCPlayerListener(SignCopy instance, SCProps props, SCCommands cmdHandle) {
        plugin = instance;
        this.props = props;
        this.cmdHandle = cmdHandle;
    }
    
    Plugin permissionsPlugin;

        MaterialData md;
    public void onPlayerInteract(PlayerInteractEvent event) {
        Location target = event.getPlayer().getLocation();
        World world = target.getWorld();
        directions = new String[16];
        directions[0] = "West";
        directions[1] = "West_North_West";
        directions[2] = "North_West";
        directions[3] = "North_North_West";
        directions[4] = "North";
        directions[5] = "North_North_East";
        directions[6] = "North_East";
        directions[7] = "East_North_East";
        directions[8] = "East";
        directions[9] = "East_South_East";
        directions[10] = "South_East";
        directions[11] = "South_South_East";
        directions[12] = "South";
        directions[13] = "South_South_West";
        directions[14] = "South_West";
        directions[15] = "West_South_West";
        Sign sign;
        if (cmdHandle.SCmode == 1) {
            permissionsPlugin = plugin.getServer().getPluginManager().getPlugin("Permissions");
            if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if (event.getPlayer().getItemInHand().getTypeId() == props.tool) {
                    // TODO: Fix fucking permissions.
                    //if (plugin.permissionHandler.has(player, "sc.signcopy")) {
                        if (SignCopy.selected.get(event.getPlayer().getName()+"_mode") == null) {
                            final BlockState state = world.getBlockAt(new Location(world, event.getClickedBlock().getX(), event.getClickedBlock().getY(), event.getClickedBlock().getZ())).getState();
                            if (state instanceof Sign) {
                                sign = (Sign)state;
                                SignCopy.selected.put(event.getPlayer().getName()+"_mode",1);
                                //for (int i=1; i<4; i++) {
                                //    lines = sign.getLine(i);
                                // }
                                cmdHandle.lines = sign.getLines();
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
                                final BlockState state = world.getBlockAt(new Location(world, event.getClickedBlock().getX(), event.getClickedBlock().getY() + 1, event.getClickedBlock().getZ())).getState();
                                if (state instanceof Sign) {
                                    sign = (Sign)state;
                                    for (int i=0; i<4; i++) {
                                        sign.setLine(i, cmdHandle.lines[i]);
                                    }
                                    String pl = sign.getData().toString();
                                    String[] dir = pl.split("[()]");
                                    System.out.println(dir[1]);
                                    String directio = dir[1];
                                    int direct = Integer.parseInt(directio);
                                    if (direct >= 8) {
                                        direct = direct - 8;
                                    } else if (direct <= 7) {
                                        direct = direct + 8;
                                    }
                                md = new MaterialData(Material.SIGN_POST);
                                sign.setData(md);
                                // TODO: Get sign facing correct fucking direction.
                                // sign.setFacingDirection();
                                sign.update(true);
                                
                                event.getPlayer().sendMessage(ChatColor.YELLOW + "[SignCopy] Sign copied!");
                            }
                        }
                    }
                    else if (SignCopy.selected.get(event.getPlayer().getName()+"_mode") == 2) {
                        final BlockState state = world.getBlockAt(new Location(world, event.getClickedBlock().getX(), event.getClickedBlock().getY(), event.getClickedBlock().getZ())).getState();
                        if (state instanceof Sign) {
                                sign = (Sign)state;
                                SignCopy.selected.put(event.getPlayer().getName()+"_mode",1);
                                //for (int i=1; i<4; i++) {
                                //    lines = sign.getLine(i);
                                // }
                                cmdHandle.lines = sign.getLines();
                                event.getPlayer().sendMessage(ChatColor.YELLOW + "[SignCopy] Sign selected.");
                            } else {
                                event.getPlayer().sendMessage(ChatColor.YELLOW + "[SignCopy] You need to select a sign!");
                            }
                    }
                //}
            }
        }
    }
}
}
