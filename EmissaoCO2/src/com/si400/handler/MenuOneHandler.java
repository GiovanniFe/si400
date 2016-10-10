package com.si400.handler;

import com.si400.enums.SectorEnum;
import com.si400.model.CountryEmission;
import com.si400.model.Emissions;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.control.ChoiceBox;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author giovanni
 */
public class MenuOneHandler {

    private Emissions emissions;

    public MenuOneHandler() {
        emissions = new Emissions();
        try {
            emissions.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCountryModel(ChoiceBox<String> cb) {
        List<String> countryList = new ArrayList<>();
        for (String key : emissions.getEmissions().keySet()) {
            countryList.add(key);
        }
        java.util.Collections.sort(countryList);
        cb.getItems().addAll(countryList);
        cb.setValue(countryList.get(0));
    }

    public void setSectorModel(ChoiceBox<SectorEnum> cb) {
        List<SectorEnum> sectorList = new ArrayList<>();
        sectorList.add(SectorEnum.BLDG);
        sectorList.add(SectorEnum.ETOT);
        sectorList.add(SectorEnum.MANF);
        sectorList.add(SectorEnum.OTHX);
        sectorList.add(SectorEnum.TRAN);
        java.util.Collections.sort(sectorList);
        cb.getItems().addAll(sectorList);
        cb.setValue(sectorList.get(0));        
    }

    public void setYearsModel(ChoiceBox<String> cbCountry, ChoiceBox<SectorEnum> cbSector, ChoiceBox<String> cbYear) {
        List yearList = new ArrayList<>();
        Map<Integer, Double> yearsMap = new HashMap<>();
        CountryEmission ce = emissions.getEmissions().get(cbCountry.getValue());
        switch ((SectorEnum) cbSector.getValue()) {
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
        cbYear.getItems().addAll(yearList);
    }

//    private void addEvents() {
//        view.getCbSector().addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                setYearsModel();
//            }
//        });
//
//        view.getCbCountry().addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                setYearsModel();
//            }
//        });
//    }
}
