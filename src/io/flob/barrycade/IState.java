/*
 *       _           _              ___                 ___  __ 
 *      | |  _  _ __| |_  _ _ __   |   \ __ _ _ _ ___  |_  )/ / 
 *      | |_| || / _` | || | '  \  | |) / _` | '_/ -_)  / // _ \
 *      |____\_,_\__,_|\_,_|_|_|_| |___/\__,_|_| \___| /___\___/ 
 *      (April 26th - 29th 2013) 
 *      <http://ludumdare.calvert.io>
 * 
 *      IState.java
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

/**
 *
 * @author rob
 */
public interface IState {
    
    public String id();

    public void tick() throws Exception;
    
}
