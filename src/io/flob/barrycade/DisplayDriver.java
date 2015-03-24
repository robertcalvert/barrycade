/*
 *       _           _              ___                 ___  __ 
 *      | |  _  _ __| |_  _ _ __   |   \ __ _ _ _ ___  |_  )/ / 
 *      | |_| || / _` | || | '  \  | |) / _` | '_/ -_)  / // _ \
 *      |____\_,_\__,_|\_,_|_|_|_| |___/\__,_|_| \___| /___\___/ 
 *      (April 26th - 29th 2013) 
 *      <http://ludumdare.calvert.io>
 * 
 *      DisplayDriver.java
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
import java.nio.ByteBuffer;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.opengl.PNGImageData;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.BufferedImageUtil;

/**
 *
 * @author rob
 */
public final class DisplayDriver {

    private final int framerate = 60;
    public final int display_height = 500;
    public final int display_width = 500;
    private int fps_count;
    private int fps;
    private long fps_previous = Misc.get_time();
    private float delta = 0.0f;
    private long frame_time_previous = 0;
    private final Texture texture_flush;

    public DisplayDriver() throws Exception {
        initDisplay();
        initGL();

        System.out.println("GL_VENDOR: " + GL11.glGetString(GL11.GL_VENDOR));
        System.out.println("GL_RENDERER: " + GL11.glGetString(GL11.GL_RENDERER));
        System.out.println("GL_VERSION: " + GL11.glGetString(GL11.GL_VERSION));
        System.out.println("#####################################");

        texture_flush = BufferedImageUtil.getTexture("", new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB), -1985);
    }

    private void initDisplay() throws Exception {
        setIcon();
        Display.setDisplayMode(new DisplayMode(display_width, display_height));
        Display.setTitle(Constants.title);
        Display.setVSyncEnabled(true);
        Display.create();
    }

    private void setIcon() throws Exception {
        PNGImageData icon16x16 = new PNGImageData();
        PNGImageData icon32x32 = new PNGImageData();
        PNGImageData icon128x128 = new PNGImageData();

        icon16x16.loadImage(getClass().getResourceAsStream("image/icon/16x16.png"));
        icon32x32.loadImage(getClass().getResourceAsStream("image/icon/32x32.png"));
        icon128x128.loadImage(getClass().getResourceAsStream("image/icon/128x128.png"));

        Display.setIcon(new ByteBuffer[]{icon16x16.getImageBufferData(), icon32x32.getImageBufferData(), icon128x128.getImageBufferData()});
    }

    private void initGL() {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glClearColor(0.1F, 0.1F, 0.1F, 1.0F);
        GL11.glClearDepth(1.0f);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
    }

    public void mode_2D() {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0.0f, Display.getWidth(), Display.getHeight(), 0.0f, 0.0f, 1.0f);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_FOG);
    }

    public void mode_3D() {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GLU.gluPerspective(60.0f, ((float) Display.getWidth()) / ((float) Display.getHeight()), 0.1f, 500.0f);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_FOG);
    }

    public void prepare() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        flush_texture();
    }

    public void update() {
        long frame_time = Misc.get_time();
        if (frame_time - fps_previous > 1000) {
            fps = fps_count;
            fps_count = 0;
            fps_previous += 1000;
        }
        fps_count++;
        delta = (frame_time - frame_time_previous) / 1000.0f;
        frame_time_previous = frame_time;
        Display.update();
        Display.sync(framerate);
    }

    public float delta() {
        return delta;
    }

    public int fps() {
        return fps;
    }

    public void flush_texture() {
        texture_flush.bind();
    }

    public void flush_colour() {
        GL11.glColor3d(1, 1, 1);
    }

    public void destroy() {
        Display.destroy();
    }
}
