/*
 *       _           _              ___                 ___  __ 
 *      | |  _  _ __| |_  _ _ __   |   \ __ _ _ _ ___  |_  )/ / 
 *      | |_| || / _` | || | '  \  | |) / _` | '_/ -_)  / // _ \
 *      |____\_,_\__,_|\_,_|_|_|_| |___/\__,_|_| \___| /___\___/ 
 *      (April 26th - 29th 2013) 
 *      <http://ludumdare.calvert.io>
 * 
 *      FontRenderer.java
 *
 *      barrycade
 *      Copyright (c) 2013 Robert Calvert <http://robert.calvert.io>
 *
 *      This program is free software; you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation; either version 2 of the License, or
 *      (at your option) any later version.
 *
 *      This program is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *
 *      You should have received a copy of the GNU General Public License
 *      along with this program.  If not, see <http://www.gnu.org/licenses/>
 *
 */
package io.flob.barrycade;

import java.awt.Font;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

/**
 *
 * @author rob
 */
public final class FontRenderer {
    
    private final TrueTypeFont _font;
    private final Color font_colour = Color.darkGray;

    public FontRenderer(String name, float size) throws Exception {
        Font awtFont;
        awtFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream(name));
        awtFont = awtFont.deriveFont(size);
        _font = new TrueTypeFont(awtFont, true);
    }

    public void render(int x, int y, String text) {
        _font.drawString(x, y, text, font_colour);
        GL11.glColor3d(1, 1, 1);
    }

    public void render_centred(int x, int y, String text) {
        render(x - (_font.getWidth(text) / 2), y - (_font.getHeight(text) / 2), text);
    }

    public void render(int x, int y, String text, Color _font_colour) {
        _font.drawString(x, y, text, _font_colour);
        GL11.glColor3d(1, 1, 1);
    }

    public void render_centred(int x, int y, String text, Color _font_colour) {
        render(x - (_font.getWidth(text) / 2), y - (_font.getHeight(text) / 2), text, _font_colour);
    }
}
