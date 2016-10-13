package com.si400.handler;

import com.si400.enums.SectorEnum;
import com.si400.model.CountryEmission;
import com.si400.model.Emissions;
import com.si400.view.MenuView;
import com.si400.view.MenuView1;
import com.si400.view.WarningView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;

/**
 *
 * @author giovanni
 */
public class MenuHandler1 {

    private Emissions emissions;
    private MenuView view;
    private String country;
    private SectorEnum sector;
    private Integer year;

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
        country = countryList.get(0);
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
        sector = sectorList.get(0);
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
            view.getCbYear().getItems().removeAll(view.getCbYear().getItems());
            view.getCbYear().getItems().addAll(yearList);
            view.getCbYear().setValue(yearList.get(0));
            year = Integer.parseInt(String.valueOf(yearList.get(0)));
        } else {
            year = null;
            view.getCbYear().getItems().removeAll(view.getCbYear().getItems());

        }
    }

    private void addEvents() {
        view.getCbCountry().getSelectionModel().selectedItemProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
            country = String.valueOf(view.getCbCountry().getValue());
            setYearsModel();
        });

        view.getCbSector().getSelectionModel().selectedItemProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
            sector = (SectorEnum) view.getCbSector().getValue();
            setYearsModel();
        });

        view.getCbYear().getSelectionModel().selectedItemProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
            year = Integer.parseInt(String.valueOf(view.getCbYear().getValue()));
        });
        view.getBtnGenerate().setOnAction(e -> setChart());
    }

    private void setChart() {
        if (year == null) {
            WarningView.display("Nenhum ano selecionado", "Ok");
            return;
        }
        view.getLayoutRight().getChildren().removeAll(view.getLayoutRight().getChildren());
        PieChart chart = new PieChart();
        switch (sector) {
            case BLDG:
                chart = generateChart(emissions.getEmissions().get(country).getBuildingsAndCommercial().get(year), SectorEnum.BLDG.toString());
                break;
            case ETOT:
                chart = generateChart(emissions.getEmissions().get(country).getEletricityAndHeat().get(year), SectorEnum.ETOT.toString());
                break;
            case MANF:
                chart = generateChart(emissions.getEmissions().get(country).getIndustryAndConstruction().get(year), SectorEnum.MANF.toString());
                break;
            case OTHX:
                chart = generateChart(emissions.getEmissions().get(country).getOtherSector().get(year), SectorEnum.OTHX.toString());
                break;
            case TRAN:
                chart = generateChart(emissions.getEmissions().get(country).getTransport().get(year), SectorEnum.TRAN.toString());
                break;
        }
        view.getLayoutRight().getChildren().add(chart);
    }

    private PieChart generateChart(Double value, String sector) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data(sector, value),
                new PieChart.Data("Outros Setores", 100 - value));
        PieChart p = new PieChart(pieChartData);
        p.setMinWidth(600);
        p.setMinHeight(600);
        return p;
    }

    private void setModels() {
        setCountryModel();
        setSectorModel();
        setYearsModel();
    }
}
