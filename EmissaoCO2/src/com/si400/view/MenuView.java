package com.si400.view;

import com.si400.model.Utils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author g168746
 */
public class MenuView {

    protected GridPane layoutTop;
    protected GridPane layoutBottom;
    private final Button btnSair, btn1, btn2, btn3;
    private final Label lblTitle, lblBottom;
    private final int X, Y;
    private final int btnX, btnY;

    public MenuView(int X, int Y) {
        this.X = X;
        this.Y = Y;
        btnX = ((X / 25) * 6) -5;
        btnY = (Y / 13) - 20;
        lblTitle = new Label("Global Emissions");
        btn1 = Utils.getButton("not implemented", btnX, btnY);
        btn2 = Utils.getButton("not implemented", btnX, btnY);
        btn3 = Utils.getButton("not implemented", btnX, btnY);
        btnSair = Utils.getButton("Exit", btnX / 4, btnY);
        lblBottom = new Label("Gabriel Brito | Giovanni Ferreira | João Lucas | 2016");
    }

    public void setLayoutTop() {
        Utils.setLabelStyle(26, lblTitle);
        btnSair.setOnAction(e -> System.exit(0));
        GridPane.setConstraints(lblTitle, 0, 0);
        GridPane.setConstraints(btn1, 1, 0);
        GridPane.setConstraints(btn2, 2, 0);
        GridPane.setConstraints(btn3, 3, 0);
        GridPane.setConstraints(btnSair, 4, 0);
        layoutTop = Utils.getGpLayout(0, (Y / 25) * 2, 0, Pos.CENTER, lblTitle, btnSair, btn1, btn2, btn3);
        layoutTop.setStyle("-fx-background-color: linear-gradient(#98a8bd 0%, #8195af 25%, #6d86a4 100%);");
        setLayoutBottom();
    }

    public void setLayoutBottom() {
        Utils.setLabelStyle(14, lblBottom);
        layoutBottom = Utils.getGpLayout(0, (Y / 25) * 1, 0, Pos.CENTER_RIGHT, lblBottom
        );
        layoutBottom.setPadding(new Insets(5));
        layoutBottom.setStyle("-fx-background-color: linear-gradient(#98a8bd 0%, #8195af 25%, #6d86a4 100%);");
    }

    public GridPane getLayoutTop() {
        return layoutTop;
    }

    public GridPane getLayoutBottom() {
        return layoutBottom;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public int getBtnY() {
        return btnY;
    }
}
