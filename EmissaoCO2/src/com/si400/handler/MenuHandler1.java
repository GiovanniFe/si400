package com.si400.handler;

import com.si400.enums.SectorEnum;
import com.si400.model.CountryEmission;
import com.si400.model.Dimensions;
import com.si400.model.Emissions;
import com.si400.model.Utils;
import com.si400.view.LeftMenuView1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;

/**
 *
 * @author giovanni
 */
public class MenuHandler1 {

    private Emissions emissions;
    private LeftMenuView1 view;
    private String country;
    private SectorEnum sector;
    private Integer year;
    private GridPane layoutRight;

    public MenuHandler1(LeftMenuView1 v, GridPane layoutR) {
        emissions = new Emissions();
        view = v;
        layoutRight = layoutR;
        try {
            emissions.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setModels();
        addEvents();
    }

    private void setCountryModel() {
        List<String> countryList = new ArrayList<>();
        for (String key : emissions.getEmissions().keySet()) {
            countryList.add(key);
        }
        java.util.Collections.sort(countryList);
        view.getCbCountry().getItems().addAll(countryList);
        view.getCbCountry().setValue(country = countryList.get(0));
    }

    private void setSectorModel() {
        List<SectorEnum> sectorList = new ArrayList<>();
        Utils.addAllSecList(sectorList, SectorEnum.ALLX, SectorEnum.BLDG, SectorEnum.ETOT, SectorEnum.MANF, SectorEnum.OTHX, SectorEnum.TRAN);
        java.util.Collections.sort(sectorList);
        view.getCbSector().getItems().addAll(sectorList);
        view.getCbSector().setValue(sector = sectorList.get(0));
    }

    private void setYearsModel() {
        if (sector == SectorEnum.ALLX) {
            setYearsModelAll();
            return;
        }
        List yearList = new ArrayList<>();
        Map<Integer, Double> yearsMap = new HashMap<>();
        CountryEmission ce = emissions.getEmissions().get(country);
        switch (sector) {
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
        }
        setCbYearWithList(Utils.getYearList(yearsMap, yearList));
    }

    private void setYearsModelAll() {
        Set yearSet = new HashSet();
        List<Integer> yearSetList = new ArrayList(), yearList = new ArrayList();
        List<Map<Integer, Double>> sectorList = new ArrayList();
        CountryEmission ce = emissions.getEmissions().get(country);
        Utils.addAllIntegerList(yearList, 1990, 2000, 2007, 2008, 2009, 2010, 2011, 2012, 2013);
        Utils.addAllMapList(sectorList, ce.getBuildingsAndCommercial(), ce.getEletricityAndHeat(), ce.getIndustryAndConstruction(),
                ce.getOtherSector(), ce.getTransport());
        for (Integer y : yearList) {
            Double valueSum = 0d;
            for (Map<Integer, Double> map : sectorList) {
                if (map.get(y) != null && !map.get(y).equals(0d)) {
                    valueSum += map.get(y);
                }
            }
            if (valueSum <= 101 && valueSum >= 99) {
                yearSet.add(y);
            }
        }
        yearSetList.addAll(yearSet);
        java.util.Collections.sort(yearSetList);
        setCbYearWithList(yearSetList);
    }

    private void setCbYearWithList(List list) {
        java.util.Collections.sort(list);
        if (!list.isEmpty()) {
            view.getCbYear().getItems().removeAll(view.getCbYear().getItems());
            view.getCbYear().getItems().addAll(list);
            if (year != null) {
                view.getCbYear().setValue(list.contains(year) ? year : list.get(0));
            } else {
                view.getCbYear().setValue(list.get(0));
            }
            year = Integer.parseInt(String.valueOf(list.get(0)));
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
            if (view.getCbYear().getValue() != null) {
                year = Integer.parseInt(String.valueOf(view.getCbYear().getValue()));
            }
        });
        view.getBtnGenerate().setOnAction(e -> setChart());
    }

    private void setChart() {
        if (year == null) {
            Alert alert = new Alert(AlertType.WARNING, "Nenhum ano selecionado", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        layoutRight.getChildren().removeAll(layoutRight.getChildren());
        PieChart chart = new PieChart();
        switch (sector) {
            case ALLX:
                chart = generatePieChart(getValuesFromCe(emissions.getEmissions().get(country), year));
                break;
            case BLDG:
                chart = generatePieChart(emissions.getEmissions().get(country).getBuildingsAndCommercial().get(year), SectorEnum.BLDG.toString());
                break;
            case ETOT:
                chart = generatePieChart(emissions.getEmissions().get(country).getEletricityAndHeat().get(year), SectorEnum.ETOT.toString());
                break;
            case MANF:
                chart = generatePieChart(emissions.getEmissions().get(country).getIndustryAndConstruction().get(year), SectorEnum.MANF.toString());
                break;
            case OTHX:
                chart = generatePieChart(emissions.getEmissions().get(country).getOtherSector().get(year), SectorEnum.OTHX.toString());
                break;
            case TRAN:
                chart = generatePieChart(emissions.getEmissions().get(country).getTransport().get(year), SectorEnum.TRAN.toString());
        }
        layoutRight.getChildren().add(chart);
    }

    private PieChart generatePieChart(Double value, String sector) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data(sector + " " + Utils.formatDouble(value) + "% ", value),
                new PieChart.Data("Remaining Sectors " + Utils.formatDouble(100 - value) + "% ", 100 - value));
        PieChart p = new PieChart(pieChartData);
        p.setMinWidth(Dimensions.getH_SIDE()- 20);
        p.setMinHeight(Dimensions.getH_SIDE()- 20);
        p.setLabelsVisible(false);
        p.setTitle("Individual Sector");
        return p;
    }

    private PieChart generatePieChart(Map<SectorEnum, Double> mapNum) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data(SectorEnum.BLDG.toString() + " " + Utils.formatDouble(mapNum.get(SectorEnum.BLDG)) + "% ", mapNum.get(SectorEnum.BLDG)),
                new PieChart.Data(SectorEnum.ETOT.toString() + " " + Utils.formatDouble(mapNum.get(SectorEnum.ETOT)) + "% ", mapNum.get(SectorEnum.ETOT)),
                new PieChart.Data(SectorEnum.MANF.toString() + " " + Utils.formatDouble(mapNum.get(SectorEnum.MANF)) + "% ", mapNum.get(SectorEnum.MANF)),
                new PieChart.Data(SectorEnum.TRAN.toString() + " " + Utils.formatDouble(mapNum.get(SectorEnum.TRAN)) + "% ", mapNum.get(SectorEnum.TRAN)),
                new PieChart.Data(SectorEnum.OTHX.toString() + " " + Utils.formatDouble(mapNum.get(SectorEnum.OTHX)) + "% ", mapNum.get(SectorEnum.OTHX)));
        PieChart p = new PieChart(pieChartData);
        p.setMinWidth(Dimensions.getH_SIDE()- 20);
        p.setMinHeight(Dimensions.getH_SIDE()- 20);
        p.setLabelsVisible(false);
        p.setTitle("All Sectors");
        return p;
    }

    private Map getValuesFromCe(CountryEmission ce, int year) {
        Map<SectorEnum, Double> mapNum = new HashMap<>();
        mapNum.put(SectorEnum.BLDG, ce.getBuildingsAndCommercial().get(year));
        mapNum.put(SectorEnum.ETOT, ce.getEletricityAndHeat().get(year));
        mapNum.put(SectorEnum.MANF, ce.getIndustryAndConstruction().get(year));
        mapNum.put(SectorEnum.TRAN, ce.getTransport().get(year));
        mapNum.put(SectorEnum.OTHX, ce.getOtherSector().get(year));
        return mapNum;
    }

    private void setModels() {
        setCountryModel();
        setSectorModel();
        setYearsModel();
    }
}
