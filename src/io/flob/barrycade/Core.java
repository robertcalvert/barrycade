/*
 *       _           _              ___                 ___  __ 
 *      | |  _  _ __| |_  _ _ __   |   \ __ _ _ _ ___  |_  )/ / 
 *      | |_| || / _` | || | '  \  | |) / _` | '_/ -_)  / // _ \
 *      |____\_,_\__,_|\_,_|_|_|_| |___/\__,_|_| \___| /___\___/ 
 *      (April 26th - 29th 2013) 
 *      <http://ludumdare.calvert.io>
 * 
 *      Core.java
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

import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

/**
 *
 * @author rob
 */
public final class Core {

    public boolean debug;
    public final DisplayDriver _display;
    private final StateSplash _splash;
    public final StateTitle _title;
    private final StateSleep _sleep;
    public final FontLibrary _font;
    public final TextureLibrary _texture;
    public final InputHandler _input;
    public final StateGame _game;
    public final StateGameOver _gameover;
    private IState state;
    private IState state_previous;

    public Core() throws Exception {
        _display = new DisplayDriver();
        _splash = new StateSplash(this);
        _splash.tick();
        _title = new StateTitle(this);
        _sleep = new StateSleep(this);
        _font = new FontLibrary();
        _texture = new TextureLibrary();
        _input = new InputHandler(this);
        _game = new StateGame(this);
        _gameover = new StateGameOver(this);
        state_set(_title);
    }

    public IState state() {
        return state;
    }

    public void state_set(IState new_state) {
        state_previous = state;
        state = new_state;
    }

    public final void run() throws Exception {
        //Misc.potato();
        while (!Display.isCloseRequested()) {
            _display.prepare();
            if (Display.isActive()) {
                if (state.equals(_sleep)) {
                    state_set(state_previous);
                }
                state.tick();
            } else {
                _sleep.tick();
            }
            render_debug();
            _display.update();
            _input.poll();
        }
        _display.destroy();
        _input.destroy();
    }

    private void render_debug() {
        _display.mode_2D();
        if (debug) {

            int right_offset = 180;
            int KB = 1024;

            _font.ui_debug.render(4, 5, Constants.title + " " + Constants.version, Color.magenta);

            _font.ui_debug.render(4, 25, "OS_NAME: " + System.getProperty("os.name"), Color.magenta);
            _font.ui_debug.render(4, 35, "OS_ARCH: " + System.getProperty("os.arch") , Color.magenta);
            _font.ui_debug.render(4, 45, "OS_VERSION: " + System.getProperty("os.version") , Color.magenta);
            _font.ui_debug.render(4, 55, "LWJGL_VERSION: " + Sys.getVersion()
                    + " (" + org.lwjgl.LWJGLUtil.getPlatformName() + ")" , Color.magenta);
            _font.ui_debug.render(4, 65, "JRE_VENDOR: " + System.getProperty("java.vendor") , Color.magenta);
            _font.ui_debug.render(4, 75, "JRE_VERSION: " + System.getProperty("java.version") , Color.magenta);
            _font.ui_debug.render(4, 85, "GL_VENDOR: " + GL11.glGetString(GL11.GL_VENDOR) , Color.magenta);
            _font.ui_debug.render(4, 95, "GL_RENDERER: " + GL11.glGetString(GL11.GL_RENDERER) , Color.magenta);
            _font.ui_debug.render(4, 105, "GL_VERSION: " + GL11.glGetString(GL11.GL_VERSION) , Color.magenta);

            _font.ui_debug.render(4, 145, "STATE: " + state.id() , Color.magenta);
            _font.ui_debug.render(4, 155, "STATE_PREVIOUS: " + state_previous.id(), Color.magenta);

            _font.ui_debug.render(4, 175, "POSITION: " + _game._camera.position().toString(), Color.magenta);
            _font.ui_debug.render(4, 185, "OBSTACLES: " + _game._obstacles.size(), Color.magenta);
            _font.ui_debug.render(4, 195, "SPEED_FALL: " + _game._camera.fall_speed(), Color.magenta);

            _font.ui_debug.render(_display.display_width - right_offset, 5, "TIME: " + Misc.get_time(), Color.magenta);
            _font.ui_debug.render(_display.display_width - right_offset, 15, "DELTA: " + _display.delta(), Color.magenta);
            _font.ui_debug.render(_display.display_width - right_offset, 25, "FPS: " + _display.fps(), Color.magenta);
            _font.ui_debug.render(_display.display_width - right_offset, 45, "JVM_MAX_MEMORY: "
                    + (Runtime.getRuntime().maxMemory() / KB) + " KB", Color.magenta);
            _font.ui_debug.render(_display.display_width - right_offset, 55, "JVM_TOTAL_MEMORY: "
                    + (Runtime.getRuntime().totalMemory() / KB) + " KB", Color.magenta);
            _font.ui_debug.render(_display.display_width - right_offset, 65, "JVM_FREE_MEMORY: "
                    + (Runtime.getRuntime().freeMemory() / KB) + " KB", Color.magenta);
            _font.ui_debug.render(_display.display_width - right_offset, 75, "JVM_INUSE_MEMORY: "
                    + ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / KB) + " KB", Color.magenta);
        }
    }
}
