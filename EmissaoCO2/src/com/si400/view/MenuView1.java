package com.si400.view;

import com.si400.model.Utils;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

/**
 *
 * @author g168746
 */
public class MenuView1 extends MenuView {

    private GridPane layoutLeft;
    private Label lblFilters, lblCountry, lblSector, lblYear;
    private final ChoiceBox cbCountry, cbSector, cbYear;
    private final Button btn;
    private int X, Y;

    public MenuView1(int X, int Y) {
        super(X, Y);
        lblFilters = new Label("Filtros");
        lblCountry = new Label("PAÍS");
        lblSector = new Label("SETOR");
        lblYear = new Label("ANO");
        cbCountry = new ChoiceBox<>();
        cbSector = new ChoiceBox<>();
        cbYear = new ChoiceBox<>();
        btn = new Button("Visualizar");
    }

    public BorderPane getLayoutMaster() {
        setLayoutLeft();
        return new BorderPane(null, layoutTop, layoutRight, layoutBottom, layoutLeft);
    }

    public void setLayoutLeft() {
        lblFilters.setFont(new Font(38));
        lblCountry.setFont(new Font(18));
        lblSector.setFont(new Font(18));
        lblYear.setFont(new Font(18));
        cbCountry.setMinHeight(30);
        cbSector.setMinHeight(30);
        cbYear.setMinHeight(30);
        cbYear.setMinWidth(90);
        GridPane.setConstraints(lblFilters, 0, 0);
        GridPane.setConstraints(lblCountry, 0, 1);
        GridPane.setConstraints(cbCountry, 0, 2);
        GridPane.setConstraints(lblSector, 0, 3);
        GridPane.setConstraints(cbSector, 0, 4);
        GridPane.setConstraints(lblYear, 0, 5);
        GridPane.setConstraints(cbYear, 0, 6);
        GridPane.setConstraints(btn, 0, 7);
        GridPane.setHalignment(btn, HPos.RIGHT);
        layoutLeft = Utils.getGpLeftLayout(Utils.getNodeList(lblFilters, lblCountry, lblSector, lblYear, cbCountry, cbSector, cbYear, btn), (X / 4) * 1, 0);
        super.setLayoutRight();
    }

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
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

    public Button getBtn() {
        return btn;
    }

    // </editor-fold>
}
