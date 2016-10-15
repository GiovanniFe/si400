package com.si400.model;

import com.sun.javafx.PlatformUtil;
import java.io.File;
import java.util.regex.Pattern;

/**
 *
 * @author giovanni
 */
public class Strings {

    private static final String USR = System.getProperty("user.name");

    public static String getDirPath() {
        if (PlatformUtil.isWindows()) {
            return "C:\\Users\\" + USR + "\\Documents\\EmissionFile";
        }
        return "/home/" + USR + "/Documentos/EmissionFile";
    }

    public static String getZipPath() {
        if (PlatformUtil.isWindows()) {
            return getDirPath() + "\\data.zip";
        }
        return getDirPath() + "/data.zip";
    }

    public static String getCsvPath() {
        for (File f : new File(getDirPath()).listFiles()) {
            if (f.isFile()) {
                try {
                    if (f.getName().split(Pattern.quote("_"))[1].equals("Data.csv")) {
                        return getDirPath() + (PlatformUtil.isWindows() ? "\\" : "/") + f.getName();
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
        }
        return "";
    }

    public static String getUrl() {
        return ("http://databank.worldbank.org/data/AjaxDownload/FileDownloadHandler.ashx?filename=95c57340-718d-4983-b9db-93d4daa81f94.zip&filetype=CSV&language=en&displayfile=Data_Extract_From_World_Development_Indicators.zip");
    }

}
