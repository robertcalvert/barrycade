/*
 *       _           _              ___                 ___  __ 
 *      | |  _  _ __| |_  _ _ __   |   \ __ _ _ _ ___  |_  )/ / 
 *      | |_| || / _` | || | '  \  | |) / _` | '_/ -_)  / // _ \
 *      |____\_,_\__,_|\_,_|_|_|_| |___/\__,_|_| \___| /___\___/ 
 *      (April 26th - 29th 2013) 
 *      <http://ludumdare.calvert.io>
 * 
 *      Main.java
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

import java.util.Date;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;

/**
 * @since 27APR2013
 * @author rob
 */
public final class Main {

    @SuppressWarnings("CallToThreadDumpStack")
    public static void main(String[] args) throws Exception {
        Core _core;
        String _tmp = null;
        Misc.set_lwjgl_librarypath();
        System.out.println("#####################################");
        System.out.println(Constants.title + " " + Constants.version);
        System.out.println("#####################################");
        System.out.println(new Date());
        System.out.println("OS_NAME: " + System.getProperty("os.name"));
        System.out.println("OS_ARCH: " + System.getProperty("os.arch"));
        System.out.println("OS_VERSION: " + System.getProperty("os.version"));
        System.out.println("LWJGL_VERSION: " + Sys.getVersion() + " (" + org.lwjgl.LWJGLUtil.getPlatformName() + ")");
        System.out.println("JRE_VENDOR: " + System.getProperty("java.vendor"));
        System.out.println("JRE_VERSION: " + System.getProperty("java.version"));
        try {
            _core = new Core();
            _core.run();
        } catch (Exception ex) {
            System.out.println("************** POTATO! ***************");
            System.out.println(ex.getMessage());
            System.out.println(ex.toString());
            ex.printStackTrace();
            for (StackTraceElement element : ex.getStackTrace()) {
                _tmp = _tmp + element + System.getProperty("line.separator");
            }
            Sys.alert(Constants.title,
                    "************** POTATO! ***************"
                    + System.getProperty("line.separator")
                    + ex.getMessage()
                    + System.getProperty("line.separator")
                    + ex.toString()
                    + System.getProperty("line.separator")
                    + _tmp
                    + "*************************************");
            System.out.println("*************************************");
            Display.destroy();
            Keyboard.destroy();
        } finally {
            System.out.println("#####################################");
        }
    }
}
