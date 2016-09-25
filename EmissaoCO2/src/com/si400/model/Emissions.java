package com.si400.model;

import com.opencsv.CSVReader;
import com.si400.util.Unzip;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Giovanni
 */
public class Emissions {

    private Map<String, CountryEmission> emissions;

    public Emissions() {
        emissions = new HashMap<>();
    }

    public Map<String, CountryEmission> getEmissions() {
        return emissions;
    }

    public void setEmissions(Map<String, CountryEmission> emissions) {
        this.emissions = emissions;
    }

    public void load() throws IOException {
        boolean isFirst = true;
        BufferedInputStream in = null;
        FileOutputStream fout = null;
        try {
            in = new BufferedInputStream(new URL("http://databank.worldbank.org/data/AjaxDownload/FileDownloadHandler.ashx?filename=ed012a5f-4eb5-40ba-9cfb-4edd8ee92bc4.zip&filetype=CSV&language=en&displayfile=Data_Extract_From_World_Development_Indicators.zip").openStream());
            fout = new FileOutputStream("C:\\Users\\Giovanni\\Documents\\teste.zip");

            final byte data[] = new byte[1024];
            int count;
            while ((count = in.read(data, 0, 1024)) != -1) {
                fout.write(data, 0, count);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (fout != null) {
                fout.close();
            }
        }

        new Unzip().unzip("C:\\Users\\Giovanni\\Documents\\teste.zip", "C:\\Users\\Giovanni\\Documents\\File");

        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(new File("C:\\Users\\Giovanni\\Documents\\File\\ed012a5f-4eb5-40ba-9cfb-4edd8ee92bc4_Data.csv"))), ',');
        String[] line;
        while ((line = reader.readNext()) != null) {
            if (isFirst) {
                isFirst = false;
                continue;
            }

            CountryEmission ce;

            if ((ce = emissions.get(line[2])) == null) {
                ce = new CountryEmission();
                ce.setBuildingsAndCommercial(new HashMap<>());
                ce.setEletricityAndHeat(new HashMap<>());
                ce.setIndustryAndConstruction(new HashMap<>());
                ce.setOtherSector(new HashMap<>());
                ce.setTranport(new HashMap<>());
            }

            switch (line[1].substring(7, 11)) {
                case "BLDG":
                    setYears(line, ce.getBuildingsAndCommercial());
                    break;
                case "ETOT":
                    setYears(line, ce.getEletricityAndHeat());
                    break;
                case "MANT":
                    setYears(line, ce.getIndustryAndConstruction());
                    break;
                case "OTHX":
                    setYears(line, ce.getOtherSector());
                    break;
                case "TRAN":
                    setYears(line, ce.getTranport());
                    break;
                default:
                    break;
            }

            emissions.put(line[2], ce);
        }
        System.out.print("bla");
    }

    private void setYears(String[] line, Map<Integer, Double> ce) {
        ce.put(1990, line[4].equals("..") ? 0d : Double.parseDouble(line[4]));
        ce.put(2000, line[5].equals("..") ? 0d : Double.parseDouble(line[5]));
        for (int i = 6; i < 14; i++) {
            ce.put(i + 2000, line[i].equals("..") ? 0d : Double.parseDouble(line[i]));
        }
    }
}
