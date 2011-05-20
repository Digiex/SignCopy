package signcopy;

import org.bukkit.entity.Player;
import org.bukkit.event.Event.*;
import org.bukkit.event.*;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;
import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;
import org.bukkit.plugin.Plugin;
import java.util.HashMap;

public class SignCopy extends JavaPlugin {
    private final SCProps props = new SCProps(this);
    private final SCCommands cmdHandle = new SCCommands(this);
    private final SCPlayerListener playerListener = new SCPlayerListener(this, props, cmdHandle);
    public static PermissionHandler permissionHandler;
    public static HashMap<String, Integer> selected = new HashMap<String, Integer>();
    Plugin permissionsPlugin;
    
    private void setupPermissions() {
      permissionsPlugin = this.getServer().getPluginManager().getPlugin("Permissions");

      if (this.permissionHandler == null) {
          if (permissionsPlugin != null) {
              this.permissionHandler = ((Permissions) permissionsPlugin).getHandler();
              System.out.println("[SignCopy] hooked into Permissions.");
          } else {
              System.out.println("[SignCopy] Permissions not found! Using ops.txt file.");
          }
      }
    }
    
    @Override
    public void onDisable() {
        System.out.println("[SignCopy] disabled.");
    }
    
    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Priority.Normal, this);
        PluginDescriptionFile pdfFile = this.getDescription();
        setupPermissions();
        props.doConfig();
        cmdHandle.setupCommands();
        System.out.println( "[SignCopy] version v" + pdfFile.getVersion() + " is enabled." );
    }
    
}
