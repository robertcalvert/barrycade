/*
 *       _           _              ___                 ___  __ 
 *      | |  _  _ __| |_  _ _ __   |   \ __ _ _ _ ___  |_  )/ / 
 *      | |_| || / _` | || | '  \  | |) / _` | '_/ -_)  / // _ \
 *      |____\_,_\__,_|\_,_|_|_|_| |___/\__,_|_| \___| /___\___/ 
 *      (April 26th - 29th 2013) 
 *      <http://ludumdare.calvert.io>
 * 
 *      StateGameOver.java
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

import org.newdawn.slick.Color;

/**
 *
 * @author rob
 */
public final class StateGameOver implements IState {

    private final String id = "GAME_OVER";
    private final Core _core;
    private final String start_message = "PRESS X TO TRY AGAIN";
    private final String message = "GAME OVER!";

    public StateGameOver(Core core) {
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
        _core._title.render_background();
        Color ui_title_colour = Color.lightGray;
        _core._font.ui_big.render_centred((_core._display.display_width / 2),
                50, Constants.title.toUpperCase());
        _core._font.ui_title.render_centred((_core._display.display_width / 2),
                100, Constants.sub_title);
        _core._font.ui_subtitle.render_centred((_core._display.display_width / 2),
                125, Constants.sub_title2);
        _core._font.ui_big.render_centred((_core._display.display_width / 2),
                (_core._display.display_height / 2), message);
        _core._font.ui_title.render_centred((_core._display.display_width / 2),
                (_core._display.display_height / 2 + 50), String.valueOf(_core._game.points() + " POINTS"), Color.blue);
        if (Misc.get_time() / 400 % 2 == 0) {
            ui_title_colour = Color.darkGray;
        }
        _core._font.ui_title.render_centred((_core._display.display_width / 2),
                (_core._display.display_height - 50), start_message, ui_title_colour);
    }
}
