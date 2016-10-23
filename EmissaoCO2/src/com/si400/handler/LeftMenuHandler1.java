package com.si400.handler;

import com.si400.abstracts.LeftMenuHandler;
import com.si400.enums.SectorEnum;
import com.si400.model.CountryEmission;
import com.si400.model.Dimensions;
import com.si400.model.Emissions;
import com.si400.model.Utils;
import com.si400.view.LeftMenuView1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class LeftMenuHandler1 implements LeftMenuHandler {

    private final Emissions emissions;
    private final LeftMenuView1 view;
    private final GridPane layoutRight;
    private String country;
    private SectorEnum sector;
    private Integer year;

    public LeftMenuHandler1(LeftMenuView1 v, GridPane layoutR, Emissions e) {
        emissions = e;
        view = v;
        layoutRight = layoutR;
        setModels();
        addEvents();
    }

    private void setModels() {
        setCountryModel();
        setSectorModel();
        setYearModel();
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

    private void setYearModel() {
        if (sector == SectorEnum.ALLX) {
            setYearsModelAll();
            return;
        }
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
        Utils.setCbYearWithList(view.getCbYear(), year, Utils.getYearList(yearsMap));
    }

    private void setYearsModelAll() {
        List yearSetList = Utils.getYearListFromCountry(emissions, country);
        Utils.setCbYearWithList(view.getCbYear(), year, yearSetList);
    }

    private void addEvents() {
        view.getCbCountry().getSelectionModel().selectedItemProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
            country = String.valueOf(view.getCbCountry().getValue());
            setYearModel();
        });

        view.getCbSector().getSelectionModel().selectedItemProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
            sector = (SectorEnum) view.getCbSector().getValue();
            setYearModel();
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
            new Alert(AlertType.WARNING, "Nenhum ano selecionado", ButtonType.OK).showAndWait();
            return;
        }
        layoutRight.getChildren().removeAll(layoutRight.getChildren());
        PieChart chart = new PieChart();
        switch (sector) {
            case ALLX:
                chart = LeftMenuHandler1.this.getPieChart(getValuesFromCe(emissions.getEmissions().get(country), year));
                break;
            case BLDG:
                chart = getPieChart(emissions.getEmissions().get(country).getBuildingsAndCommercial().get(year), SectorEnum.BLDG.toString());
                break;
            case ETOT:
                chart = getPieChart(emissions.getEmissions().get(country).getEletricityAndHeat().get(year), SectorEnum.ETOT.toString());
                break;
            case MANF:
                chart = getPieChart(emissions.getEmissions().get(country).getIndustryAndConstruction().get(year), SectorEnum.MANF.toString());
                break;
            case OTHX:
                chart = getPieChart(emissions.getEmissions().get(country).getOtherSector().get(year), SectorEnum.OTHX.toString());
                break;
            case TRAN:
                chart = getPieChart(emissions.getEmissions().get(country).getTransport().get(year), SectorEnum.TRAN.toString());
        }
        layoutRight.getChildren().add(chart);
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

    private PieChart getPieChart(Double value, String sector) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data(sector + " " + Utils.formatDouble(value) + "% ", value),
                new PieChart.Data("Remaining Sectors " + Utils.formatDouble(100 - value) + "% ", 100 - value));
        return setPieChart("Individual Sector", pieChartData);
    }

    private PieChart getPieChart(Map<SectorEnum, Double> mapNum) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data(SectorEnum.BLDG.toString() + " " + Utils.formatDouble(mapNum.get(SectorEnum.BLDG)) + "% ", mapNum.get(SectorEnum.BLDG)),
                new PieChart.Data(SectorEnum.ETOT.toString() + " " + Utils.formatDouble(mapNum.get(SectorEnum.ETOT)) + "% ", mapNum.get(SectorEnum.ETOT)),
                new PieChart.Data(SectorEnum.MANF.toString() + " " + Utils.formatDouble(mapNum.get(SectorEnum.MANF)) + "% ", mapNum.get(SectorEnum.MANF)),
                new PieChart.Data(SectorEnum.TRAN.toString() + " " + Utils.formatDouble(mapNum.get(SectorEnum.TRAN)) + "% ", mapNum.get(SectorEnum.TRAN)),
                new PieChart.Data(SectorEnum.OTHX.toString() + " " + Utils.formatDouble(mapNum.get(SectorEnum.OTHX)) + "% ", mapNum.get(SectorEnum.OTHX)));
        return setPieChart("All Sectors", pieChartData);
    }

    private PieChart setPieChart(String title, ObservableList data) {
        PieChart p = new PieChart(data);
        p.setMinHeight(Dimensions.getH_SIDE() - 20);
        p.setLabelsVisible(false);
        p.setTitle("All Sectors");
        return p;
    }
}
