package com.si400.handler.swing;

import com.si400.enums.SectorEnum;
import com.si400.model.CountryEmission;
import com.si400.model.Emissions;
import com.si400.view.swing.DataFilterView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author giovanni
 */
public class DataFilterHandler {

    private final DataFilterView view;
    private Emissions emissions;

    public DataFilterHandler(DataFilterView v, Emissions e) {
        view = v;
        emissions = e;
        try {
            emissions.load();
        } catch (IOException err) {
            err.printStackTrace();
        }
        setCountryModel();
        setSectorModel();
        addEvents();
        view.setVisible(true);
    }

    private void setCountryModel() {
        List countryList = new ArrayList<>();
        for (String key : emissions.getEmissions().keySet()) {
            countryList.add(key);
        }
        java.util.Collections.sort(countryList);
        view.getCbCountry().setModel(new DefaultComboBoxModel(countryList.toArray()));
    }

    private void setSectorModel() {
        List sectorList = new ArrayList<>();
        sectorList.add(SectorEnum.BLDG);
        sectorList.add(SectorEnum.ETOT);
        sectorList.add(SectorEnum.MANF);
        sectorList.add(SectorEnum.OTHX);
        sectorList.add(SectorEnum.TRAN);
        java.util.Collections.sort(sectorList);
        view.getCbSector().setModel(new DefaultComboBoxModel(sectorList.toArray()));
    }

    private void setYearsModel() {
        List yearList = new ArrayList<>();
        Map<Integer, Double> yearsMap = new HashMap<>();
        CountryEmission ce = emissions.getEmissions().get((String) view.getCbCountry().getSelectedItem());
        switch ((SectorEnum) view.getCbSector().getSelectedItem()) {
            case BLDG:
                yearsMap = ce.getBuildingsAndCommercial();
                break;
            case ETOT:
                yearsMap = ce.getEletricityAndHeat();
                break;
            case MANF:
                yearsMap = ce.getIndustryAndConstruction();
                break;
            case OTHX:
                yearsMap = ce.getOtherSector();
                break;
            case TRAN:
                yearsMap = ce.getTransport();
                break;
            default:
                break;
        }

        for (Integer key : yearsMap.keySet()) {
            if (yearsMap.get(key) != null && yearsMap.get(key) != 0d) {
                yearList.add(key.toString());
            }
        }
        java.util.Collections.sort(yearList);
        view.getCbYear().setModel(new DefaultComboBoxModel(yearList.toArray()));
    }

    private void addEvents() {
        view.getCbSector().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                setYearsModel();
            }
        });

        view.getCbCountry().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                setYearsModel();
            }
        });
    }

}
