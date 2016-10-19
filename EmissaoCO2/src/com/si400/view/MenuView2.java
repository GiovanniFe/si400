package com.si400.view;

import com.si400.model.Utils;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author g168746
 */
public class MenuView2 extends MenuView {

    private GridPane layoutLeft;
    private final GridPane layoutRight;
    private final Label lblFilters, lblCountry, lblYear1, lblYear2;
    private final ChoiceBox cbCountry, cbYear1, cbYear2;
    private final Button btnGenerate;
    private final int layoutRightY, layoutRightX;

    public MenuView2(int x, int y) {
        super(x, y);
        layoutRightY = (getY() / 25) * 22;
        layoutRightX = (getX() / 4) * 3;
        lblFilters = new Label("Filters");
        lblCountry = new Label("Country");
        lblYear1 = new Label("From Year");
        lblYear2 = new Label("To Year");
        cbCountry = new ChoiceBox<>();
        cbYear1 = new ChoiceBox<>();
        cbYear2 = new ChoiceBox<>();
        layoutRight = new GridPane();
        btnGenerate = Utils.getButton("Generate", 80, getBtnY());
    }

    public BorderPane getLayoutMaster() {
        setLayoutLeft();
        return new BorderPane(null, layoutTop, layoutRight, layoutBottom, layoutLeft);
    }

    public void setLayoutLeft() {
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
        layoutLeft = Utils.getGpLayout(250, layoutRightY, 15, Pos.TOP_CENTER, lblFilters, lblCountry, lblYear1, lblYear2, cbCountry, cbYear1, cbYear2, btnGenerate);
        layoutLeft.setStyle("-fx-background-color: linear-gradient(#76869b 0%, #59738d 25%, #4b6482 100%);");
        setLayoutRight();
    }

    public void setLayoutRight() {
        layoutRight.setStyle("-fx-background-color: #BBCCDD;");
        layoutRight.setMinWidth(getX() - 250);
        layoutRight.setAlignment(Pos.CENTER);
        super.setLayoutTop();
    }

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public GridPane getLayoutLeft() {
        return layoutLeft;
    }

    public Pane getLayoutRight() {
        return layoutRight;
    }

    public Label getLblFilters() {
        return lblFilters;
    }

    public Label getLblCountry() {
        return lblCountry;
    }

    public Label getLblYear1() {
        return lblYear1;
    }

    public Label getLblYear2() {
        return lblYear2;
    }

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

    public int getLayoutRightY() {
        return layoutRightY;
    }

    public int getLayoutRightX() {
        return layoutRightX;
    }
    // </editor-fold>

}
