package signcopy;

import org.bukkit.event.block.BlockListener;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class SCBlockListener extends BlockListener {
    private final SignCopy plugin;
    
    public SCBlockListener(SignCopy instance) {
        this.plugin = instance;
    }
}
