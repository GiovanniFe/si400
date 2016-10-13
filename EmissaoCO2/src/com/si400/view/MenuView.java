package com.si400.view;

import com.si400.model.Utils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author g168746
 */
public abstract class MenuView {

    protected Pane layoutRight;
    protected GridPane layoutTop;
    protected GridPane layoutBottom;
    private Button btnSair, btn1, btn2, btn3;
    private Label lblTitle, lblBottom;
    private int X, Y;

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public abstract Label getLblFilters();

    public abstract Label getLblCountry();

    public abstract Label getLblSector();

    public abstract Label getLblYear();

    public abstract ChoiceBox getCbCountry();

    public abstract ChoiceBox getCbSector();

    public abstract ChoiceBox getCbYear();

    public abstract Button getBtnGenerate();

    public abstract BorderPane getLayoutMaster();

    public abstract GridPane getLayoutLeft();

    // </editor-fold>
    public MenuView(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    public void setLayoutTop() {
        lblTitle = new Label("Global Emissions");
        lblTitle.setTextFill(Color.web("#FFFFFF"));
        lblTitle.setFont(new Font(24));
        btn1 = Utils.getButton("teste", X / 5, Y / 20);
        btn2 = Utils.getButton("teste", X / 5, Y / 20);
        btn3 = Utils.getButton("teste", X / 5, Y / 20);
        btnSair = Utils.getButton("Sair", X / 11, Y / 20);
        btnSair.setOnAction(e -> System.exit(0));
        GridPane.setConstraints(lblTitle, 0, 0);
        GridPane.setConstraints(btn1, 1, 0);
        GridPane.setConstraints(btn2, 2, 0);
        GridPane.setConstraints(btn3, 3, 0);
        GridPane.setConstraints(btnSair, 4, 0);
        layoutTop = Utils.getGpTopLayout(Utils.getNodeList(lblTitle, btnSair, btn1, btn2, btn3), 0, (Y / 26) * 2);
        layoutTop.setStyle("-fx-background-color: linear-gradient(#98a8bd 0%, #8195af 25%, #6d86a4 100%);");
        setLayoutBottom();
    }

    public void setLayoutBottom() {
        lblBottom = new Label("Gabriel Brito | Giovanni Ferreira | João Lucas | 2016");
        lblBottom.setTextFill(Color.web("#FFFFFF"));
        layoutBottom = new GridPane();
        layoutBottom.setMinHeight((Y / 26));
        layoutBottom.setPadding(new Insets(5));
        layoutBottom.setAlignment(Pos.CENTER_RIGHT);
        layoutBottom.getChildren().add(lblBottom);
        layoutBottom.setStyle("-fx-background-color: linear-gradient(#98a8bd 0%, #8195af 25%, #6d86a4 100%);");
        layoutBottom.setMinHeight(30);
    }

    public GridPane getLayoutTop() {
        return layoutTop;
    }

    public GridPane getLayoutBottom() {
        return layoutBottom;
    }

    public Pane getLayoutRight() {
        return layoutRight;
    }
}
