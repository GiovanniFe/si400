package com.si400.model;

/**
 *
 * @author giovanni
 */
public class Strings {

    private static final String OS = System.getProperty("os.name").toLowerCase();
    private static final String USR = System.getProperty("user.name");

    public static String getZipPath() {
        if (OS.equals("win") || OS.equals("windows 7")) {
            return "C:\\Users\\" + USR + "\\Documents\\EmissionFile\\data.zip";
        }
        return "/home/" + USR + "/Documentos/EmissionFile/data.zip";
    }

    public static String getUnzipPath() {
        if (OS.equals("win") || OS.equals("windows 7")) {
            return "C:\\Users\\" + USR + "\\Documents\\EmissionFile";
        }
        return "/home/" + USR + "/Documentos/EmissionFile";
    }

    public static String getCsvPath() {
        if (OS.equals("win") || OS.equals("windows 7")) {
            return getUnzipPath() + "\\62372792-576c-407b-9d18-8b56b4491275_Data.csv";
        }
        return getUnzipPath() + "/62372792-576c-407b-9d18-8b56b4491275_Data.csv";
    }

    public static String getUrl() {
        return ("http://databank.worldbank.org/data/AjaxDownload/FileDownloadHandler.ashx?filename=62372792-576c-407b-9d18-8b56b4491275.zip&filetype=CSV&language=en&displayfile=Data_Extract_From_World_Development_Indicators.zip");
    }

}
