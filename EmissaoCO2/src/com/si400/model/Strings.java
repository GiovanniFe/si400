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
            return getUnzipPath() + "\\32634418-aff4-4c1d-850e-bd29784b5663_Data.csv";
        }
        return getUnzipPath() + "/32634418-aff4-4c1d-850e-bd29784b5663_Data.csv";
    }

    public static String getUrl() {
        return ("http://databank.worldbank.org/data/AjaxDownload/FileDownloadHandler.ashx"
                + "?filename=32634418-aff4-4c1d-850e-bd29784b5663.zip&filetype=CSV"
                + "&language=en&displayfile=Data_Extract_From_World_Development_Indicators.zip");
    }

}
