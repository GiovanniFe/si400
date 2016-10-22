package com.si400.view;

import com.si400.handler.MenuHandler1;
import com.si400.model.Dimensions;
import com.si400.model.Utils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author g168746
 */
public class MainView {

    protected GridPane layoutTop, layoutBottom, layoutRight;
    private final Button btnSair, btn1, btn2, btn3;
    private final Label lblTitle, lblBottom;
    private Stage window;
    private Scene scene;
    private BorderPane layoutMaster;

    public MainView() {
        lblTitle = new Label("Global Emissions");
        btn1 = Utils.getButton("not implemented", Dimensions.getBTN_W(), Dimensions.getBTN_H());
        btn2 = Utils.getButton("not implemented", Dimensions.getBTN_W(), Dimensions.getBTN_H());
        btn3 = Utils.getButton("not implemented", Dimensions.getBTN_W(), Dimensions.getBTN_H());
        btnSair = Utils.getButton("Exit", Dimensions.getBTN_W() / 4, Dimensions.getBTN_H());
        lblBottom = new Label("Gabriel Brito | Giovanni Ferreira | João Lucas | 2016");
        layoutRight = new GridPane();
        setLayoutTop();
    }

    public void display() {
        LeftMenuView1 view = new LeftMenuView1();
        new MenuHandler1(view, layoutRight);
        window = new Stage();
        layoutMaster = new BorderPane(null, layoutTop, layoutRight, layoutBottom, view.getLayoutLeft());
        scene = new Scene(layoutMaster, Dimensions.getW(), Dimensions.getH());
        window.setTitle("Country Emissions");
        window.setScene(scene);
        window.show();
    }

    public void setLayoutTop() {
        Utils.setLabelStyle(26, lblTitle);
        btnSair.setOnAction(e -> System.exit(0));
        GridPane.setConstraints(lblTitle, 0, 0);
        GridPane.setConstraints(btn1, 1, 0);
        GridPane.setConstraints(btn2, 2, 0);
        GridPane.setConstraints(btn3, 3, 0);
        GridPane.setConstraints(btnSair, 4, 0);
        layoutTop = Utils.getGpLayout(0, (Dimensions.getH() / 25) * 2, 0, Pos.CENTER, lblTitle, btnSair, btn1, btn2, btn3);
        layoutTop.setStyle("-fx-background-color: linear-gradient(#98a8bd 0%, #8195af 25%, #6d86a4 100%);");
        setLayoutBottom();
    }

    public void setLayoutBottom() {
        Utils.setLabelStyle(14, lblBottom);
        layoutBottom = Utils.getGpLayout(0, (Dimensions.getH() / 25) * 1, 0, Pos.CENTER_RIGHT, lblBottom);
        layoutBottom.setPadding(new Insets(5));
        layoutBottom.setStyle("-fx-background-color: linear-gradient(#98a8bd 0%, #8195af 25%, #6d86a4 100%);");
        setLayoutRight();
    }

    public void setLayoutRight() {
        layoutRight.setStyle("-fx-background-color: #BBCCDD;");
        layoutRight.setMinWidth(Dimensions.getW() - Dimensions.getW_LEFT());
        layoutRight.setAlignment(Pos.CENTER);
    }
}
