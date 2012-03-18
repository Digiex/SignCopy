package net.digiex.signcopy;

import org.bukkit.Material;
import org.bukkit.block.Block;

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
public class Sign {
    
    private Block block;
    private String[] text;
    private Material type;
    private Byte data;
    
    public Sign(Block block, String[] text) {
        this.block = block;
        this.text = text;
        this.type = block.getType();
        this.data = block.getData();
    }
    
    public Block getBlock() {
        return block;
    }
    
    public Material getType() {
        return type;
    }
    
    public String[] getText() {
        return text;
    }
    
    public Byte getData() {
        return data;
    }
}
