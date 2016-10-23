package com.si400.handler;

import com.si400.abstracts.LeftMenuHandler;
import com.si400.enums.SectorEnum;
import com.si400.model.CountryEmission;
import com.si400.model.Dimensions;
import com.si400.model.Emissions;
import com.si400.model.Utils;
import com.si400.view.LeftMenuView3;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;

/**
 *
 * @author giovanni
 */
public class LeftMenuHandler3 implements LeftMenuHandler {

    private final Emissions emissions;
    private final LeftMenuView3 view;
    private final GridPane layoutRight;
    private String country1, country2;
    private Integer year;

    public LeftMenuHandler3(LeftMenuView3 v, GridPane layoutR, Emissions e) {
        emissions = e;
        view = v;
        layoutRight = layoutR;
        setModels();
        addEvents();
    }

    private void setModels() {
        setCountryModel();
        setYearModel();
    }

    private void setCountryModel() {
        List<String> countryList = new ArrayList<>();
        for (String key : emissions.getEmissions().keySet()) {
            countryList.add(key);
        }
        java.util.Collections.sort(countryList);
        view.getCbCountry1().getItems().addAll(countryList);
        view.getCbCountry1().setValue(country1 = countryList.get(0));
        view.getCbCountry2().getItems().addAll(countryList);
        view.getCbCountry2().setValue(country2 = countryList.get(0));
    }

    private void setYearModel() {
        List yearSetList = Utils.getYearListFromCountry(emissions, country1, country2);
        Utils.setCbYearWithList(view.getCbYear(), year, yearSetList);
    }

    private void addEvents() {
        view.getCbCountry1().getSelectionModel().selectedItemProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
            country1 = String.valueOf(view.getCbCountry1().getValue());
            setYearModel();
        });
        view.getCbCountry2().getSelectionModel().selectedItemProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
            country2 = String.valueOf(view.getCbCountry2().getValue());
            setYearModel();
        });

        view.getCbYear().getSelectionModel().selectedItemProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
            if (view.getCbYear().getValue() != null) {
                year = Integer.parseInt(String.valueOf(view.getCbYear().getValue()));
                return;
            }
            year = null;
        });
        view.getBtnGenerate().setOnAction(e -> setChart());
    }

    private void setChart() {
        if (year == null) {
            new Alert(Alert.AlertType.WARNING, "Nenhum ano selecionado", ButtonType.OK).showAndWait();
            return;
        }
        layoutRight.getChildren().removeAll(layoutRight.getChildren());
        layoutRight.getChildren().add(getBarChart());
    }

    private BarChart getBarChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
        XYChart.Series y1 = new XYChart.Series(), y2 = new XYChart.Series();
        CountryEmission ce1 = emissions.getEmissions().get(country1);
        CountryEmission ce2 = emissions.getEmissions().get(country2);

        chart.setTitle("Country Years Comparison");
        xAxis.setLabel("Sectors");
        yAxis.setLabel("% of total fuel combustion");
        chart.setMinHeight(Dimensions.getH_SIDE() - 20);
        chart.setMinWidth(Dimensions.getW_RIGHT2() - 20);
        chart.setLegendSide(Side.BOTTOM);

        y1.setName(country1);
        y1.getData().add(new XYChart.Data(SectorEnum.BLDG.ToShortString(), ce1.getBuildingsAndCommercial().get(year)));
        y1.getData().add(new XYChart.Data(SectorEnum.ETOT.ToShortString(), ce1.getEletricityAndHeat().get(year)));
        y1.getData().add(new XYChart.Data(SectorEnum.MANF.ToShortString(), ce1.getIndustryAndConstruction().get(year)));
        y1.getData().add(new XYChart.Data(SectorEnum.TRAN.ToShortString(), ce1.getTransport().get(year)));
        y1.getData().add(new XYChart.Data(SectorEnum.OTHX.ToShortString(), ce1.getOtherSector().get(year)));

        y2.setName(country2);
        y2.getData().add(new XYChart.Data(SectorEnum.BLDG.ToShortString(), ce2.getBuildingsAndCommercial().get(year)));
        y2.getData().add(new XYChart.Data(SectorEnum.ETOT.ToShortString(), ce2.getEletricityAndHeat().get(year)));
        y2.getData().add(new XYChart.Data(SectorEnum.MANF.ToShortString(), ce2.getIndustryAndConstruction().get(year)));
        y2.getData().add(new XYChart.Data(SectorEnum.TRAN.ToShortString(), ce2.getTransport().get(year)));
        y2.getData().add(new XYChart.Data(SectorEnum.OTHX.ToShortString(), ce2.getOtherSector().get(year)));

        chart.getData().addAll(y1, y2);
        return chart;
    }
}
