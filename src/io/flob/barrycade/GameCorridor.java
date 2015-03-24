/*
 *       _           _              ___                 ___  __ 
 *      | |  _  _ __| |_  _ _ __   |   \ __ _ _ _ ___  |_  )/ / 
 *      | |_| || / _` | || | '  \  | |) / _` | '_/ -_)  / // _ \
 *      |____\_,_\__,_|\_,_|_|_|_| |___/\__,_|_| \___| /___\___/ 
 *      (April 26th - 29th 2013) 
 *      <http://ludumdare.calvert.io>
 * 
 *      GameCorridor.java
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

/**
 *
 * @author rob
 */
public final class GameCorridor {

    private final StateGame _game;
    public final int size = 6;
    public final int deep = 500;

    public GameCorridor(StateGame game) {
        _game = game;
    }

    public void tick() {
        _game._core._display.mode_3D();
        _game._core._texture.white.bind();
        GL11.glBegin(GL11.GL_QUADS);
        // Top Face
        GL11.glNormal3f(0, 1, 0);
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex3f(-size, size, -deep);   // TL
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex3f(-size, size, size);   // BL
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex3f(size, size, size);   // BR
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex3f(size, size, -deep);   // TR
        // Bottom Face
        GL11.glNormal3f(0, -1, 0);
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex3f(-size, -size, -deep);   // TR
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex3f(size, -size, -deep);   // LT
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex3f(size, -size, size);   // BL
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex3f(-size, -size, size);   // BR
        // Right face
        GL11.glNormal3f(1, 0, 0);
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex3f(size, -size, -deep);   // BR
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex3f(size, size, -deep);   // TR
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex3f(size, size, size);   // TL
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex3f(size, -size, size);   // BL
        // Left Face
        GL11.glNormal3f(-1, 0, 0);
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex3f(-size, -size, -deep);   // BL
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex3f(-size, -size, size);   // BR
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex3f(-size, size, size);   // TR
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex3f(-size, size, -deep);   // TL
        // Back
        GL11.glNormal3f(0f, 0f, -1f);
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex3f(-size, -size, -deep);   // BR
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex3f(-size, size, -deep);   // TR
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex3f(size, size, -deep);   // TL
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex3f(size, -size, -deep);   // BL
        GL11.glEnd();
    }
}
