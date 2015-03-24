/*
 *       _           _              ___                 ___  __ 
 *      | |  _  _ __| |_  _ _ __   |   \ __ _ _ _ ___  |_  )/ / 
 *      | |_| || / _` | || | '  \  | |) / _` | '_/ -_)  / // _ \
 *      |____\_,_\__,_|\_,_|_|_|_| |___/\__,_|_| \___| /___\___/ 
 *      (April 26th - 29th 2013) 
 *      <http://ludumdare.calvert.io>
 * 
 *      TextureLibrary.java
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

import java.awt.image.BufferedImage;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.BufferedImageUtil;

/**
 *
 * @author rob
 */
public final class TextureLibrary {

    public final Texture white;
    public final Texture yellow;
    public final Texture red;
    public final Texture blue;
    public final Texture ui_background;

    public TextureLibrary() throws Exception {

        white = BufferedImageUtil.getTexture("", new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB), -1985);
        yellow = TextureLoader.getTexture("PNG", getClass().getResourceAsStream("image/tiles/yellow.png"));
        red = TextureLoader.getTexture("PNG", getClass().getResourceAsStream("image/tiles/red.png"));
        blue = TextureLoader.getTexture("PNG", getClass().getResourceAsStream("image/tiles/blue.png"));
        ui_background = TextureLoader.getTexture("PNG", getClass().getResourceAsStream("image/ui/background.png"));
    }
}
