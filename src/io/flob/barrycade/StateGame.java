/*
 *       _           _              ___                 ___  __ 
 *      | |  _  _ __| |_  _ _ __   |   \ __ _ _ _ ___  |_  )/ / 
 *      | |_| || / _` | || | '  \  | |) / _` | '_/ -_)  / // _ \
 *      |____\_,_\__,_|\_,_|_|_|_| |___/\__,_|_| \___| /___\___/ 
 *      (April 26th - 29th 2013) 
 *      <http://ludumdare.calvert.io>
 * 
 *      StateGame.java
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

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.Color;

/**
 *
 * @author rob
 */
public final class StateGame implements IState {

    private boolean gameover;
    private final String id = "GAME";
    public final Core _core;
    public GameCamera _camera;
    public GameCorridor _corridor;
    public boolean render_collision_boxs;
    private final float fog = 0.015f;
    public ArrayList<GameObstacle> _obstacles;
    private int points;

    public StateGame(Core core) throws Exception {
        _core = core;
        reset();
    }

    public void reset() throws Exception {
        gameover = false;
        points = 0;
        _corridor = new GameCorridor(this);
        _camera = new GameCamera(this);
        _obstacles = new ArrayList<GameObstacle>();
        _obstacles.add(new GameObstacle(this, -50));
        _obstacles.add(new GameObstacle(this, -80));
        _obstacles.add(new GameObstacle(this, -110));
        _obstacles.add(new GameObstacle(this, -140));
        _obstacles.add(new GameObstacle(this, -170));
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

        ByteBuffer temp = ByteBuffer.allocateDirect(16);
        temp.order(ByteOrder.nativeOrder());
        float fog_colour[] = new float[]{0f, 0f, 0f, 0f};
        GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_EXP);
        temp.asFloatBuffer().put(fog_colour).flip();
        GL11.glFog(GL11.GL_FOG_COLOR, temp.asFloatBuffer());
        GL11.glFogf(GL11.GL_FOG_DENSITY, fog);
        GL11.glHint(GL11.GL_FOG_HINT, GL11.GL_DONT_CARE);
        GL11.glFogf(GL11.GL_FOG_START, 0.0f);
        GL11.glFogf(GL11.GL_FOG_END, _corridor.deep);

        _camera.tick();
        _corridor.tick();
        for (int count = _obstacles.size(); count > 0; count--) {
            GameObstacle _obstacle = (GameObstacle) _obstacles.get(count - 1);
            _obstacle.tick();
        }
        if (gameover) {
            _core.state_set(_core._gameover);
        }
        _core._display.mode_2D();
        _core._font.ui_big.render_centred((_core._display.display_width / 2),
                50, String.valueOf(points), Color.blue);
    }

    public void gameover() {
        gameover = true;
    }

    public void render_collision_box(Vector3f max, Vector3f min) {
        if (render_collision_boxs) {
            GL11.glColor3d(1, 0, 0);
            GL11.glBegin(GL11.GL_LINE_STRIP);
            GL11.glVertex3f(min.getX(), min.getY(), min.getZ());
            GL11.glVertex3f(max.getX(), min.getY(), min.getZ());
            GL11.glVertex3f(max.getX(), max.getY(), min.getZ());
            GL11.glVertex3f(min.getX(), max.getY(), min.getZ());
            GL11.glVertex3f(min.getX(), min.getY(), min.getZ());
            GL11.glVertex3f(min.getX(), min.getY(), max.getZ());
            GL11.glVertex3f(max.getX(), min.getY(), max.getZ());
            GL11.glVertex3f(max.getX(), max.getY(), max.getZ());
            GL11.glVertex3f(min.getX(), max.getY(), max.getZ());
            GL11.glVertex3f(min.getX(), min.getY(), max.getZ());
            GL11.glEnd();
            GL11.glBegin(GL11.GL_LINES);
            GL11.glVertex3f(max.getX(), min.getY(), min.getZ());
            GL11.glVertex3f(max.getX(), min.getY(), max.getZ());
            GL11.glVertex3f(max.getX(), max.getY(), min.getZ());
            GL11.glVertex3f(max.getX(), max.getY(), max.getZ());
            GL11.glVertex3f(min.getX(), max.getY(), min.getZ());
            GL11.glVertex3f(min.getX(), max.getY(), max.getZ());
            GL11.glEnd();
            _core._display.flush_colour();
        }
    }

    public int points() {
        return points;
    }

    public void points_add(int _points) {
        points += _points;
    }
}
