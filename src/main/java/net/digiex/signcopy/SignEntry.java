package net.digiex.signcopy;

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
public class SignEntry {
    
    private int mode;
    private String[] text;
    
    public SignEntry( int mode) {
        this.mode = mode;
    }
    
    public int getMode() {
        return mode;
    }
    
    public String[] getText() {
        return text;
    }
    
    public void setMode(Integer mode) {
        this.mode = mode;
    }
    
    public void setText(String[] text) {
        this.text = text;
    }
    
}
