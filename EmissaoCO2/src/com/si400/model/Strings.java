package com.si400.model;

/**
 *
 * @author giovanni
 */
public class Strings {

    private static final String OS = System.getProperty("os.name").toLowerCase();
    private static final String USR = System.getProperty("user.name");

    public static String getZipPath() {
        if (OS.equals("win")) {
            return "C:\\Users\\" + USR + "\\Documents\\EmissionFile\\data.zip";
        }
        return "/home/" + USR + "/Documentos/EmissionFile/data.zip";
    }

    public static String getUnzipPath() {
        if (OS.equals("win")) {
            return "C:\\Users\\" + USR + "\\Documents\\EmissionFile";
        }
        return "/home/" + USR + "/Documentos/EmissionFile";
    }

    public static String getCsvPath() {
        if (OS.equals("win")) {
            return getUnzipPath() + "\\2cd45ae2-e095-4375-8224-ff4cf176ed52_Data.csv";
        }
        return getUnzipPath() + "/2cd45ae2-e095-4375-8224-ff4cf176ed52_Data.csv";
    }

    public static String getUrl() {
        return ("http://databank.worldbank.org/data/AjaxDownload/FileDownloadHandler.ashx?filename=2cd45ae2-e095-4375-8224-ff4cf176ed52.zip&filetype=CSV&language=en&displayfile=Data_Extract_From_World_Development_Indicators.zip");
    }

}
