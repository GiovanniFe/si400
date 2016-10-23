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
public class LeftMenuView1 implements LeftMenuView {

    private GridPane layoutLeft;
    private final Label lblFilters, lblCountry, lblSector, lblYear;
    private final ChoiceBox cbCountry, cbSector, cbYear;
    private final Button btnGenerate;

    public LeftMenuView1() {
        lblFilters = new Label("Filters");
        lblCountry = new Label("Country");
        lblSector = new Label("Sector");
        lblYear = new Label("Year");
        cbCountry = new ChoiceBox<>();
        cbSector = new ChoiceBox<>();
        cbYear = new ChoiceBox<>();
        btnGenerate = Utils.getButton("Generate", 80, Dimensions.getBTN_H());
        setLayoutLeft();
    }

    private void setLayoutLeft() {
        Utils.setLabelStyle(24, lblFilters);
        Utils.setLabelStyle(18, lblCountry, lblSector, lblYear);
        Utils.setCbStyle(cbCountry, cbSector, cbYear);
        GridPane.setConstraints(lblFilters, 0, 0);
        GridPane.setConstraints(lblCountry, 0, 1);
        GridPane.setConstraints(cbCountry, 0, 2);
        GridPane.setConstraints(lblSector, 0, 3);
        GridPane.setConstraints(cbSector, 0, 4);
        GridPane.setConstraints(lblYear, 0, 5);
        GridPane.setConstraints(cbYear, 0, 6);
        GridPane.setConstraints(btnGenerate, 0, 7);
        GridPane.setHalignment(btnGenerate, HPos.RIGHT);
        layoutLeft = Utils.getGpLayout(Dimensions.getW_LEFT1(), Dimensions.getH_SIDE(), 10, Pos.TOP_CENTER,
                lblFilters, lblCountry, lblSector, lblYear, cbCountry, cbSector, cbYear, btnGenerate);
        layoutLeft.setStyle("-fx-background-color: linear-gradient(#76869b 0%, #59738d 25%, #4b6482 100%);");
    }

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    @Override
    public GridPane getLayoutLeft() {
        return layoutLeft;
    }

    public Label getLblFilters() {
        return lblFilters;
    }

    public Label getLblCountry() {
        return lblCountry;
    }

    public Label getLblSector() {
        return lblSector;
    }

    public Label getLblYear() {
        return lblYear;
    }

    public ChoiceBox getCbCountry() {
        return cbCountry;
    }

    public ChoiceBox getCbSector() {
        return cbSector;
    }

    public ChoiceBox getCbYear() {
        return cbYear;
    }

    public Button getBtnGenerate() {
        return btnGenerate;
    }
    // </editor-fold>

}
