package com.si400.handler;

import com.si400.enums.SectorEnum;
import com.si400.model.CountryEmission;
import com.si400.model.Emissions;
import com.si400.view.MenuView;
import com.si400.view.MenuView1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;

/**
 *
 * @author giovanni
 */
public class MenuHandler1 {

    private Emissions emissions;
    private MenuView view;

    public MenuHandler1(MenuView1 v) {
        emissions = new Emissions();
        view = v;
        try {
            emissions.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setModels();
        addEvents();
    }

    public Scene getScene() {
        return new Scene(view.getLayoutMaster());
    }

    public void setCountryModel() {
        List<String> countryList = new ArrayList<>();
        for (String key : emissions.getEmissions().keySet()) {
            countryList.add(key);
        }
        java.util.Collections.sort(countryList);
        view.getCbCountry().getItems().addAll(countryList);
        view.getCbCountry().setValue(countryList.get(0));
    }

    public void setSectorModel() {
        List<SectorEnum> sectorList = new ArrayList<>();
        sectorList.add(SectorEnum.BLDG);
        sectorList.add(SectorEnum.ETOT);
        sectorList.add(SectorEnum.MANF);
        sectorList.add(SectorEnum.OTHX);
        sectorList.add(SectorEnum.TRAN);
        java.util.Collections.sort(sectorList);
        view.getCbSector().getItems().addAll(sectorList);
        view.getCbSector().setValue(sectorList.get(0));
    }

    public void setYearsModel() {
        List yearList = new ArrayList<>();
        Map<Integer, Double> yearsMap = new HashMap<>();
        CountryEmission ce = emissions.getEmissions().get((String) view.getCbCountry().getValue());
        switch ((SectorEnum) view.getCbSector().getValue()) {
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
        if (!yearList.isEmpty()) {
            view.getCbYear().getItems().addAll(yearList);
            view.getCbYear().setValue(yearList.get(0));
        }
    }

    private void addEvents() {
        view.getCbCountry().getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                setYearsModel();
            }
        });

        view.getCbSector().getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                setYearsModel();
            }
        });
    }

    private void setModels() {
        setCountryModel();
        setSectorModel();
        setYearsModel();
    }
}
