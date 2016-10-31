package com.si400.view;

import com.si400.abstracts.LeftMenuView;
import com.si400.model.Dimensions;
import com.si400.model.Utils;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author g168746
 */
public class LeftMenuView3 implements LeftMenuView {

    private GridPane layoutLeft;
    private final Label lblFilters, lblCountry1, lblCountry2, lblYear;
    private final ChoiceBox cbCountry, cbCountry1, cbYear;
    private final Button btnGenerate;

    public LeftMenuView3() {
        lblFilters = new Label("Filters");
        lblCountry1 = new Label("Country 1");
        lblCountry2 = new Label("Country 2");
        lblYear = new Label("Year");
        cbCountry = new ChoiceBox<>();
        cbCountry1 = new ChoiceBox<>();
        cbYear = new ChoiceBox<>();
        btnGenerate = Utils.getButton("Generate", 80, Dimensions.getBTN_H());
        setLayoutLeft();
    }

    private void setLayoutLeft() {
        Utils.setLabelStyle(24, lblFilters);
        Utils.setLabelStyle(18, lblCountry1, lblCountry2, lblYear);
        Utils.setCbStyle(cbCountry, cbCountry1, cbYear);
        GridPane.setConstraints(lblFilters, 0, 0);
        GridPane.setConstraints(lblCountry1, 0, 1);
        GridPane.setConstraints(cbCountry, 0, 2);
        GridPane.setConstraints(lblCountry2, 0, 3);
        GridPane.setConstraints(cbCountry1, 0, 4);
        GridPane.setConstraints(lblYear, 0, 5);
        GridPane.setConstraints(cbYear, 0, 6);
        GridPane.setConstraints(btnGenerate, 0, 7);
        GridPane.setHalignment(btnGenerate, HPos.RIGHT);
        layoutLeft = Utils.getGpLayout(Dimensions.getW_LEFT2(), Dimensions.getH_SIDE(), 15, Pos.TOP_CENTER,
                lblFilters, lblCountry1, lblCountry2, lblYear, cbCountry, cbCountry1, cbYear, btnGenerate);
        layoutLeft.setStyle("-fx-background-color: linear-gradient(#76869b 0%, #59738d 25%, #4b6482 100%);");
    }

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    @Override
    public GridPane getLayoutLeft() {
        return layoutLeft;
    }

    @Override
    public ChoiceBox getCbCountry() {
        return cbCountry;
    }

    public ChoiceBox getCbCountry1() {
        return cbCountry1;
    }

    public ChoiceBox getCbYear() {
        return cbYear;
    }

    public Button getBtnGenerate() {
        return btnGenerate;
    }
    // </editor-fold>
}
