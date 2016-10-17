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
import java.util.regex.Pattern;

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
        BufferedInputStream in = null;
        FileOutputStream fout = null;
        try {
            createDirectory();
            in = new BufferedInputStream(new URL(Strings.getUrl()).openStream());
            fout = new FileOutputStream(Strings.getZipPath());
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

        new Unzip().unzip(Strings.getZipPath(), Strings.getDirPath());

        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(new File(Strings.getCsvPath()))), ',');
        String[] row;
        while ((row = reader.readNext()) != null) {
            if (row[1].split(Pattern.quote(".")).length != 4) {
                continue;
            }

            CountryEmission ce;

            if ((ce = emissions.get(row[2])) == null) {
                ce = new CountryEmission();
                ce.setBuildingsAndCommercial(new HashMap<>());
                ce.setEletricityAndHeat(new HashMap<>());
                ce.setIndustryAndConstruction(new HashMap<>());
                ce.setOtherSector(new HashMap<>());
                ce.setTransport(new HashMap<>());
            }

            switch (row[1].substring(7, 11)) {
                case "BLDG":
                    setYears(row, ce.getBuildingsAndCommercial());
                    break;
                case "ETOT":
                    setYears(row, ce.getEletricityAndHeat());
                    break;
                case "MANF":
                    setYears(row, ce.getIndustryAndConstruction());
                    break;
                case "OTHX":
                    setYears(row, ce.getOtherSector());
                    break;
                case "TRAN":
                    setYears(row, ce.getTransport());
                    break;
                default:
                    break;
            }

            emissions.put(row[2], ce);
        }
    }

    private void createDirectory() {
        if (!new File(Strings.getDirPath()).exists()) {
            new File(Strings.getDirPath()).mkdir();
        }
    }

    private void setYears(String[] line, Map<Integer, Double> ce) {
        ce.put(1990, line[4].equals("..") ? 0d : Double.parseDouble(line[4]));
        ce.put(2000, line[5].equals("..") ? 0d : Double.parseDouble(line[5]));
        for (int i = 7; i < 14; i++) {
            ce.put(i + 2000, line[i].equals("..") ? 0d : Double.parseDouble(line[i]));
        }
    }
}
