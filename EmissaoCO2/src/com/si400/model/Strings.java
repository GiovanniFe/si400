package com.si400.model;

import java.io.File;
import java.util.regex.Pattern;

/**
 *
 * @author giovanni
 */
public class Strings {

    private static final String USR = System.getProperty("user.name");
    private static final String OS = System.getProperty("os.name");

    public static String getDirPath() {
        if (OS.equalsIgnoreCase("win") || OS.equalsIgnoreCase("Windows 7")) {
            return "C:\\Users\\" + USR + "\\Documents\\EmissionFile";
        }
        return "/home/" + USR + "/Documentos/EmissionFile";
    }

    public static String getZipPath() {
        if (OS.equalsIgnoreCase("win") || OS.equalsIgnoreCase("Windows 7")) {
            return getDirPath() + "\\data.zip";
        }
        return getDirPath() + "/data.zip";
    }

    public static String getCsvPath() {
        for (File f : new File(getDirPath()).listFiles()) {
            if (f.isFile()) {
                try {
                    if (f.getName().split(Pattern.quote("_"))[1].equalsIgnoreCase("Data.csv")) {
                        return getDirPath() + (OS.equalsIgnoreCase("win") || OS.equalsIgnoreCase("Windows 7") ? "\\" : "/") + f.getName();
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
        }
        return "";
    }

    public static String getUrl() {
        return ("https://drive.google.com/uc?export=download&id=0B3_GqcqBjJMVYmgyUVlHRUtnbzQ");
    }

}
