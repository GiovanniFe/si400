package com.si400.view;

import com.si400.model.Utils;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author g168746
 */
public abstract class MenuView {

    protected GridPane layoutRight;
    protected GridPane layoutTop;
    protected GridPane layoutBottom;
    private Button btnSair, btn1, btn2, btn3;
    private int X, Y;

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public abstract Label getLblFilters();

    public abstract Label getLblCountry();

    public abstract Label getLblSector();

    public abstract Label getLblYear();

    public abstract ChoiceBox getCbCountry();

    public abstract ChoiceBox getCbSector();

    public abstract ChoiceBox getCbYear();

    public abstract Button getBtn();

    public abstract BorderPane getLayoutMaster();

    // </editor-fold>
    public MenuView(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    public void setLayoutRight() {
        layoutRight = new GridPane();
        layoutRight.setStyle("-fx-background-color: #BBCCDD;");
        layoutRight.setMinWidth((X / 4) * 3);
        setLayoutTop();
    }

    public void setLayoutTop() {
        btn1 = new Button("teste");
        btn2 = new Button("teste");
        btn3 = new Button("teste");
        btnSair = new Button("sair");
        btn1.setMinHeight(Y / 13);
        btn2.setMinHeight(Y / 13);
        btn3.setMinHeight(Y / 13);
        btnSair.setMinHeight(Y / 13);
        btnSair.setOnAction(e -> System.exit(0));
        GridPane.setConstraints(btn1, 0, 0);
        GridPane.setConstraints(btn2, 1, 0);
        GridPane.setConstraints(btn3, 2, 0);
        GridPane.setConstraints(btnSair, 3, 0);
        layoutTop = Utils.getGpTopLayout(Utils.getNodeList(btnSair, btn1, btn2, btn3), 0, (Y / 13));
        setLayoutBottom();
    }

    public void setLayoutBottom() {
        layoutBottom = new GridPane();
        layoutBottom.setStyle("-fx-background-color: #CCCCDD;");
        layoutBottom.setMinHeight(30);
    }
}
