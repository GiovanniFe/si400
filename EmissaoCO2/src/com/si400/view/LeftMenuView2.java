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
public class LeftMenuView2 implements LeftMenuView {

    private GridPane layoutLeft;
    private final Label lblFilters, lblCountry, lblYear1, lblYear2;
    private final ChoiceBox cbCountry, cbYear1, cbYear2;
    private final Button btnGenerate;

    public LeftMenuView2() {
        lblFilters = new Label("Filters");
        lblCountry = new Label("Country");
        lblYear1 = new Label("Year 1");
        lblYear2 = new Label("Year 2");
        cbCountry = new ChoiceBox<>();
        cbYear1 = new ChoiceBox<>();
        cbYear2 = new ChoiceBox<>();
        btnGenerate = Utils.getButton("Generate", 80, Dimensions.getBTN_H());
        setLayoutLeft();
    }

    private void setLayoutLeft() {
        Utils.setLabelStyle(24, lblFilters);
        Utils.setLabelStyle(18, lblCountry, lblYear1, lblYear2);
        Utils.setCbStyle(cbCountry, cbYear1, cbYear2);
        GridPane.setConstraints(lblFilters, 0, 0);
        GridPane.setConstraints(lblCountry, 0, 1);
        GridPane.setConstraints(cbCountry, 0, 2);
        GridPane.setConstraints(lblYear1, 0, 3);
        GridPane.setConstraints(cbYear1, 0, 4);
        GridPane.setConstraints(lblYear2, 0, 5);
        GridPane.setConstraints(cbYear2, 0, 6);
        GridPane.setConstraints(btnGenerate, 0, 7);
        GridPane.setHalignment(btnGenerate, HPos.RIGHT);
        layoutLeft = Utils.getGpLayout(Dimensions.getW_LEFT2(), Dimensions.getH_SIDE(), 15, Pos.TOP_CENTER,
                lblFilters, lblCountry, lblYear1, lblYear2, cbCountry, cbYear1, cbYear2, btnGenerate);
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

    public ChoiceBox getCbYear1() {
        return cbYear1;
    }

    public ChoiceBox getCbYear2() {
        return cbYear2;
    }

    public Button getBtnGenerate() {
        return btnGenerate;
    }
    // </editor-fold>

}
