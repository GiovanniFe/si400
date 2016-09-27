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
            return getUnzipPath() + "\\2f6dffda-12b6-4275-8281-212288d80e64_Data.csv";
        }
        return getUnzipPath() + "/2f6dffda-12b6-4275-8281-212288d80e64_Data.csv";
    }

    public static String getUrl() {
        return ("http://databank.worldbank.org/data/AjaxDownload/FileDownloadHandler.ashx"
                + "?filename=2f6dffda-12b6-4275-8281-212288d80e64.zip&filetype=CSV"
                + "&language=en&displayfile=Data_Extract_From_World_Development_Indicators.zip");
    }

}
