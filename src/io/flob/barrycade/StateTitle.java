/*
 *       _           _              ___                 ___  __ 
 *      | |  _  _ __| |_  _ _ __   |   \ __ _ _ _ ___  |_  )/ / 
 *      | |_| || / _` | || | '  \  | |) / _` | '_/ -_)  / // _ \
 *      |____\_,_\__,_|\_,_|_|_|_| |___/\__,_|_| \___| /___\___/ 
 *      (April 26th - 29th 2013) 
 *      <http://ludumdare.calvert.io>
 * 
 *      StateTitle.java
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

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author rob
 */
public final class StateTitle implements IState {

    private final String id = "TITLE";
    private final Core _core;
    private final String start_message = "PRESS X TO PLAY";
    private long background_timer = Misc.get_time();
    private final long background_speed = 30;

    public StateTitle(Core core) {
        _core = core;
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public void tick() throws Exception {
        if (!(_core.state().equals(this))) {
            _core.state_set(this);
        }
        _core._display.mode_2D();
        render_background();
        Color ui_title_colour = Color.lightGray;
        _core._font.ui_big.render_centred((_core._display.display_width / 2),
                50, Constants.title.toUpperCase());
        _core._font.ui_title.render_centred((_core._display.display_width / 2),
                100, Constants.sub_title);
        _core._font.ui_subtitle.render_centred((_core._display.display_width / 2),
                125, Constants.sub_title2);
        
        
        _core._font.ui_title.render_centred((_core._display.display_width / 2),
                225, "Avoid the white obstacles!");
        _core._font.ui_title.render_centred((_core._display.display_width / 2),
                260, "Red speeds you up.", Color.red);
        _core._font.ui_title.render_centred((_core._display.display_width / 2),
                295, "Blue is speed neutral.", Color.blue);
        _core._font.ui_title.render_centred((_core._display.display_width / 2),
                330, "Yellow = more points!", Color.yellow);
        
        if (Misc.get_time() / 400 % 2 == 0) {
            ui_title_colour = Color.darkGray;
        }
        _core._font.ui_title.render_centred((_core._display.display_width / 2),
                (_core._display.display_height - 50), start_message, ui_title_colour);
        _core._font.ui_debug.render(4, _core._display.display_height - 12, Constants.copyrite);
    }
    
    public void render_background() {
        Texture texture = _core._texture.ui_background;
        texture.bind();
        long count = (Misc.get_time() - background_timer) / background_speed;
        if (count > _core._display.display_height) {
            background_timer = Misc.get_time();
        }
        Vector2f position_texture = new Vector2f(0, count);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2f(position_texture.x, position_texture.y);
        GL11.glTexCoord2f((float) texture.getImageWidth() / texture.getTextureWidth(), 0);
        GL11.glVertex2f(position_texture.x + (texture.getImageWidth()), position_texture.y);
        GL11.glTexCoord2f((float) texture.getImageWidth() / texture.getTextureWidth(), (float) texture.getImageHeight() / texture.getTextureHeight());
        GL11.glVertex2f(position_texture.x + (texture.getImageWidth()), position_texture.y + (texture.getImageHeight()));
        GL11.glTexCoord2f(0, (float) texture.getImageHeight() / texture.getTextureHeight());
        GL11.glVertex2f(position_texture.x, position_texture.y + (texture.getImageHeight()));
        GL11.glEnd();
        position_texture = new Vector2f(0, position_texture.getY() - texture.getImageHeight());
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2f(position_texture.x, position_texture.y);
        GL11.glTexCoord2f((float) texture.getImageWidth() / texture.getTextureWidth(), 0);
        GL11.glVertex2f(position_texture.x + (texture.getImageWidth()), position_texture.y);
        GL11.glTexCoord2f((float) texture.getImageWidth() / texture.getTextureWidth(), (float) texture.getImageHeight() / texture.getTextureHeight());
        GL11.glVertex2f(position_texture.x + (texture.getImageWidth()), position_texture.y + (texture.getImageHeight()));
        GL11.glTexCoord2f(0, (float) texture.getImageHeight() / texture.getTextureHeight());
        GL11.glVertex2f(position_texture.x, position_texture.y + (texture.getImageHeight()));
        GL11.glEnd();
        _core._display.flush_texture();
    }
}
