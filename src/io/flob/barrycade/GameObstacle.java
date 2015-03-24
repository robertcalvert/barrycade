/*
 *       _           _              ___                 ___  __ 
 *      | |  _  _ __| |_  _ _ __   |   \ __ _ _ _ ___  |_  )/ / 
 *      | |_| || / _` | || | '  \  | |) / _` | '_/ -_)  / // _ \
 *      |____\_,_\__,_|\_,_|_|_|_| |___/\__,_|_| \___| /___\___/ 
 *      (April 26th - 29th 2013) 
 *      <http://ludumdare.calvert.io>
 * 
 *      GameObstacle.java
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
import java.io.BufferedInputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author rob
 */
public final class GameObstacle {

    public final StateGame _game;
    private Vector3f position;
    public ArrayList<GameObstacleTile> _tiles;
    private boolean done = false;

    public GameObstacle(StateGame game, float z) throws Exception {
        _game = game;
        position = new Vector3f(0.0f, 0.0f, z);
        load_tiles();
    }

    public float z() {
        return position.getZ();
    }

    public void tick() throws Exception {
        position.z += _game._camera.fall_speed() * _game._core._display.delta();
        if (position.z > -30f & !done) {
            done = true;
            _game._obstacles.add(new GameObstacle(_game, -200));
        }
        if (position.z > 1f) {
            _game.points_add(10);
            _game._camera.speed_fall_add(0.30f);
            _game._obstacles.remove(this);
        }
        for (int count = 0; count < _tiles.size(); count++) {
            GameObstacleTile _tile = (GameObstacleTile) _tiles.get(count);
            _tile.tick();

        }
    }

    public void load_tiles() throws Exception {
        BufferedImage tiles = ImageIO.read(
                new BufferedInputStream(getClass().getResourceAsStream("image/obstacle/" + Misc.random_number(1, Constants.obstacles) + ".png")));
        int size = tiles.getWidth();
        _tiles = new ArrayList<GameObstacleTile>();
        for (int y = 0; y < (size); y++) {
            for (int x = 0; x < (size); x++) {
                int rgb = tiles.getRGB(x, y);
                if (rgb != 0) {
                    GameObstacleTile _tile = new GameObstacleTile(this, size, x, y, rgb);
                    _tiles.add(_tile);
                }
            }
        }
    }
}
