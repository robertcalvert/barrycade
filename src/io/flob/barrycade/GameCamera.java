/*
 *       _           _              ___                 ___  __ 
 *      | |  _  _ __| |_  _ _ __   |   \ __ _ _ _ ___  |_  )/ / 
 *      | |_| || / _` | || | '  \  | |) / _` | '_/ -_)  / // _ \
 *      |____\_,_\__,_|\_,_|_|_|_| |___/\__,_|_| \___| /___\___/ 
 *      (April 26th - 29th 2013) 
 *      <http://ludumdare.calvert.io>
 * 
 *      GameCamera.java
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
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author rob
 */
public final class GameCamera {

    private final StateGame _game;
    private Vector3f position;
    private final int speed_move = 5;
    private float speed_fall = 25;
    private float speed_fall_plus = 0;
    private float size = 0.5f;

    public GameCamera(StateGame game) {
        _game = game;
        position = new Vector3f(0, 0, 0);
    }
    
    public float fall_speed() {
        return speed_fall + speed_fall_plus;
    }
    
    public void speed_fall_add(float _speed) {
        speed_fall_plus += _speed;
        if (fall_speed() < speed_fall) {
            speed_fall_plus = 0;
        }
    }

    public Vector3f position() {
        return position;
    }

    public void tick() {
        GL11.glLoadIdentity();
        GL11.glTranslatef(position.getX(), position.getY(), position.getZ());
        _game._core._display.mode_3D();
        _game.render_collision_box(collision_max(), collision_min());
    }

    private float distance() {
        return speed_move * _game._core._display.delta();
    }

    public void move_left() throws Exception {
        position.x += distance();
        if (position.x > (_game._corridor.size - size)) {
            position.x = _game._corridor.size - size;
        }
    }

    public void move_right() throws Exception {
        position.x -= distance();
        if (position.x < (-_game._corridor.size + size)) {
            position.x = -_game._corridor.size + size;
        }
    }

    public void move_up() throws Exception {
        position.y -= distance();
        if (position.y < (-_game._corridor.size + size)) {
            position.y = -_game._corridor.size + size;
        }
    }

    public void move_down() throws Exception {
        position.y += distance();
        if (position.y > (_game._corridor.size - size)) {
            position.y = _game._corridor.size - size;
        }
    }
    
    public Vector3f collision_min() {
        return new Vector3f(-(position.getX() - (size / 2)),
                -(position.getY() - (size / 2)), -(position.getZ() - (size / 2)  * 3));
    }
    
    public Vector3f collision_max() {
        return new Vector3f(-(position.getX() + (size / 2)),
                -(position.getY() + (size / 2)), 
                -(position.getZ() + (size / 2)  * 2));
    }
}
