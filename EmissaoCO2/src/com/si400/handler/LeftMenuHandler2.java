package com.si400.handler;

import com.si400.abstracts.LeftMenuHandler;
import com.si400.enums.SectorEnum;
import com.si400.model.CountryEmission;
import com.si400.model.Dimensions;
import com.si400.model.Emissions;
import com.si400.model.Utils;
import com.si400.view.LeftMenuView2;
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
public class LeftMenuHandler2 implements LeftMenuHandler {

    private final Emissions emissions;
    private final LeftMenuView2 view;
    private final GridPane layoutRight;
    private String country;
    private Integer year1, year2;

    public LeftMenuHandler2(LeftMenuView2 v, GridPane layoutR, Emissions e) {
        emissions = e;
        view = v;
        layoutRight = layoutR;
        setModels();
        addEvents();
    }

    private void setModels() {
        country = Utils.setCountryModel(emissions, view);
        setYearModel();
    }

    private void setYearModel() {
        List yearSetList = Utils.getYearListFromCountry(emissions, country);
        Utils.setCbYearWithList(view.getCbYear1(), year1, yearSetList);
        Utils.setCbYearWithList(view.getCbYear2(), year2, yearSetList);
    }

    private void addEvents() {
        view.getCbCountry().getSelectionModel().selectedItemProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
            country = String.valueOf(view.getCbCountry().getValue());
            setYearModel();
        });

        view.getCbYear1().getSelectionModel().selectedItemProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
            if (view.getCbYear1().getValue() != null) {
                year1 = Integer.parseInt(String.valueOf(view.getCbYear1().getValue()));
                return;
            }
            year1 = null;
        });
        view.getCbYear2().getSelectionModel().selectedItemProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
            if (view.getCbYear2().getValue() != null) {
                year2 = Integer.parseInt(String.valueOf(view.getCbYear2().getValue()));
                return;
            }
            year2 = null;
        });
        view.getBtnGenerate().setOnAction(e -> setChart());
    }

    private void setChart() {
        if (year1 == null || year2 == null) {
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
        CountryEmission ce = emissions.getEmissions().get(country);

        chart.setTitle("Country Years Comparison");
        xAxis.setLabel("Sectors");
        yAxis.setLabel("% of total fuel combustion");
        chart.setMinHeight(Dimensions.getH_SIDE() - 20);
        chart.setMinWidth(Dimensions.getW_RIGHT2() - 20);
        chart.setLegendSide(Side.BOTTOM);

        y1.setName(year1.toString());
        y1.getData().add(new XYChart.Data(SectorEnum.BLDG.ToShortString(), ce.getBuildingsAndCommercial().get(year1)));
        y1.getData().add(new XYChart.Data(SectorEnum.ETOT.ToShortString(), ce.getEletricityAndHeat().get(year1)));
        y1.getData().add(new XYChart.Data(SectorEnum.MANF.ToShortString(), ce.getIndustryAndConstruction().get(year1)));
        y1.getData().add(new XYChart.Data(SectorEnum.TRAN.ToShortString(), ce.getTransport().get(year1)));
        y1.getData().add(new XYChart.Data(SectorEnum.OTHX.ToShortString(), ce.getOtherSector().get(year1)));

        y2.setName(year2.toString());
        y2.getData().add(new XYChart.Data(SectorEnum.BLDG.ToShortString(), ce.getBuildingsAndCommercial().get(year2)));
        y2.getData().add(new XYChart.Data(SectorEnum.ETOT.ToShortString(), ce.getEletricityAndHeat().get(year2)));
        y2.getData().add(new XYChart.Data(SectorEnum.MANF.ToShortString(), ce.getIndustryAndConstruction().get(year2)));
        y2.getData().add(new XYChart.Data(SectorEnum.TRAN.ToShortString(), ce.getTransport().get(year2)));
        y2.getData().add(new XYChart.Data(SectorEnum.OTHX.ToShortString(), ce.getOtherSector().get(year2)));

        chart.getData().addAll(y1, y2);
        return chart;
    }
}
