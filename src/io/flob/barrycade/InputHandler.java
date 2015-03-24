/*
 *       _           _              ___                 ___  __ 
 *      | |  _  _ __| |_  _ _ __   |   \ __ _ _ _ ___  |_  )/ / 
 *      | |_| || / _` | || | '  \  | |) / _` | '_/ -_)  / // _ \
 *      |____\_,_\__,_|\_,_|_|_|_| |___/\__,_|_| \___| /___\___/ 
 *      (April 26th - 29th 2013) 
 *      <http://ludumdare.calvert.io>
 * 
 *      InputHandler.java
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

import org.lwjgl.input.Keyboard;

/**
 *
 * @author rob
 */
public final class InputHandler {

    private final Core _core;

    public InputHandler(Core core) throws Exception {
        _core = core;
    }

    public void poll() throws Exception {

        if (_core.state().equals(_core._title)) {
            while (Keyboard.next()) {
                if (Keyboard.getEventKeyState()) {
                    if (Keyboard.getEventKey() == Keyboard.KEY_F1) {
                        _core.debug = !_core.debug;
                        return;
                    }
                    if (Keyboard.getEventKey() == Keyboard.KEY_X) {
                        _core.state_set(_core._game);
                        return;
                    }
                }
            }
        }

        if (_core.state().equals(_core._game)) {
            while (Keyboard.next()) {
                if (Keyboard.getEventKeyState()) {
                    if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
                        _core.state_set(_core._title);
                        return;
                    }
                    if (Keyboard.getEventKey() == Keyboard.KEY_F1) {
                        _core.debug = !_core.debug;
                        return;
                    }
                    if (Keyboard.getEventKey() == Keyboard.KEY_F2) {
                        _core._game.render_collision_boxs = !_core._game.render_collision_boxs;
                        return;
                    }
                }
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_UP) | Keyboard.isKeyDown(Keyboard.KEY_W)) {
                _core._game._camera.move_up();
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_DOWN) | Keyboard.isKeyDown(Keyboard.KEY_S)) {
                _core._game._camera.move_down();
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_LEFT) | Keyboard.isKeyDown(Keyboard.KEY_A)) {
                _core._game._camera.move_left();
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT) | Keyboard.isKeyDown(Keyboard.KEY_D)) {
                _core._game._camera.move_right();
            }
        }
        
        if (_core.state().equals(_core._gameover)) {
            while (Keyboard.next()) {
                if (Keyboard.getEventKeyState()) {
                    if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) { 
                        _core.state_set(_core._title);
                        _core._game.reset();
                        return;
                    }
                    if (Keyboard.getEventKey() == Keyboard.KEY_F1) {
                        _core.debug = !_core.debug;
                        return;
                    }
                    if (Keyboard.getEventKey() == Keyboard.KEY_X) {
                        _core.state_set(_core._game);
                        _core._game.reset();
                        return;
                    }
                }
            }
        }

    }

    public void destroy() {
        Keyboard.destroy();
    }
}
