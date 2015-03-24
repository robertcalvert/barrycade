/*
 *       _           _              ___                 ___  __ 
 *      | |  _  _ __| |_  _ _ __   |   \ __ _ _ _ ___  |_  )/ / 
 *      | |_| || / _` | || | '  \  | |) / _` | '_/ -_)  / // _ \
 *      |____\_,_\__,_|\_,_|_|_|_| |___/\__,_|_| \___| /___\___/ 
 *      (April 26th - 29th 2013) 
 *      <http://ludumdare.calvert.io>
 * 
 *      Misc.java
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

import java.io.File;
import java.security.CodeSource;
import java.util.Date;
import org.lwjgl.Sys;

/**
 *
 * @author rob
 */
public class Misc {

    public static long get_time() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    public static void set_lwjgl_librarypath() throws Exception {
        String base_path = get_base_path();
        if ("NETBEANS".equals(System.getProperty("org.lwjgl.librarypath"))) {
            System.setProperty("org.lwjgl.librarypath", base_path
                    + "../../../../lib/lwjgl2/native/" + org.lwjgl.LWJGLUtil.getPlatformName());
        } else {
            System.setProperty("org.lwjgl.librarypath", base_path
                    + "lib/native/" + org.lwjgl.LWJGLUtil.getPlatformName());
        }
    }

    public static String get_base_path() throws Exception {
        CodeSource code_source = Main.class.getProtectionDomain().getCodeSource();
        File jar_file = new File(code_source.getLocation().toURI().getPath());
        String jar_path = jar_file.getParentFile().getPath();
        return jar_path + "/";
    }

    public static void println(String message) {
        Date date = new Date();
        System.out.println(date + " " + message);
    }
    
    public static void potato() throws Exception {
        throw new Exception("It’s mashing!");
    }
    
    public static int random_number(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
}
