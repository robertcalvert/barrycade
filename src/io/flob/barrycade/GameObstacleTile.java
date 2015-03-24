/*
 *       _           _              ___                 ___  __ 
 *      | |  _  _ __| |_  _ _ __   |   \ __ _ _ _ ___  |_  )/ / 
 *      | |_| || / _` | || | '  \  | |) / _` | '_/ -_)  / // _ \
 *      |____\_,_\__,_|\_,_|_|_|_| |___/\__,_|_| \___| /___\___/ 
 *      (April 26th - 29th 2013) 
 *      <http://ludumdare.calvert.io>
 * 
 *      GameObstacleTile.java
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
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author rob
 */
public final class GameObstacleTile {

    private final GameObstacle _obstacle;
    private final float size;
    private Vector2f position;
    private final int rgb;

    public GameObstacleTile(GameObstacle obstacle, float _size, int x, int y, int _rgb) throws Exception {
        _obstacle = obstacle;
        size = (obstacle._game._corridor.size / _size);
        position = new Vector2f(
                -obstacle._game._corridor.size + (size + (x * size) * 2), -obstacle._game._corridor.size + (size + (y * size) * 2));
        rgb = _rgb;
    }

    public void tick() {
        if (intersects(_obstacle._game._camera.collision_max(),
                _obstacle._game._camera.collision_min())) {
            if (rgb == -65536) {
                _obstacle._game._camera.speed_fall_add(1.5f);
                _obstacle._tiles.remove(this);
                return;
            }
            if (rgb == -16767745) {
                _obstacle._game._camera.speed_fall_add(-0.30f);
                _obstacle._tiles.remove(this);
                return;
            }
            if (rgb == -1376512) {
                _obstacle._game.points_add(25);
                _obstacle._tiles.remove(this);
                return;
            }
            _obstacle._game.gameover();
            return;
        }
        _obstacle._game._core._display.mode_3D();
        if (rgb == -65536) {
            _obstacle._game._core._texture.red.bind();
        } else if (rgb == -16767745) {
            _obstacle._game._core._texture.blue.bind();
        } else if (rgb == -1376512) {
            _obstacle._game._core._texture.yellow.bind();
        } else {
            _obstacle._game._core._texture.white.bind();
        }
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glNormal3f(0f, 0f, 1f);
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex3f(-size + position.getX(),
                -size + position.getY(), size + _obstacle.z());   // Bottom Left Of The Texture and Quad
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex3f(size + position.getX(),
                -size + position.getY(), size + _obstacle.z());   // Bottom Right Of The Texture and Quad
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex3f(size + position.getX(),
                size + position.getY(), size + _obstacle.z());   // Top Right Of The Texture and Quad
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex3f(-size + position.getX(),
                size + position.getY(), size + _obstacle.z());   // Top Left Of The Texture and Quad
        GL11.glEnd();
        _obstacle._game.render_collision_box(collision_max(), collision_min());
    }

    private Vector3f collision_min() {
        return new Vector3f(position.getX() - size, position.getY() - size, _obstacle.z() + size);
    }

    private Vector3f collision_max() {
        return new Vector3f(position.getX() + size, position.getY() + size, _obstacle.z() + size);
    }

    private boolean intersects(Vector3f _position_max, Vector3f _position_min) {
        if (_position_min.getX() > collision_min().getX() && _position_max.getX() < collision_max().getX()) {
            if (_position_min.getY() > collision_min().getY() && _position_max.getY() < collision_max().getY()) {
                if (_position_min.getZ() > collision_min().getZ() && _position_max.getZ() < collision_max().getZ()) {
                    return true;
                }
            }
        }
        return false;
    }
}
