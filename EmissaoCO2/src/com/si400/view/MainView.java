package com.si400.view;

import com.si400.abstracts.LeftMenuView;
import com.si400.handler.LeftMenuHandler1;
import com.si400.handler.LeftMenuHandler2;
import com.si400.handler.LeftMenuHandler3;
import com.si400.model.Dimensions;
import com.si400.model.Emissions;
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
    private LeftMenuView1 layoutLeft1;
    private LeftMenuView2 layoutLeft2;
    private LeftMenuView3 layoutLeft3;
    private LeftMenuHandler1 handler1;
    private LeftMenuHandler2 handler2;
    private LeftMenuHandler3 handler3;
    private final Emissions emissions;

    public MainView() {
        lblTitle = new Label("Global Emissions");
        btn1 = Utils.getButton("Sector by Year", Dimensions.getBTN_W(), Dimensions.getBTN_H());
        btn2 = Utils.getButton("Country Years Comparison", Dimensions.getBTN_W(), Dimensions.getBTN_H());
        btn3 = Utils.getButton("Countries Sectors Comparison", Dimensions.getBTN_W(), Dimensions.getBTN_H());
        btnSair = Utils.getButton("Exit", Dimensions.getBTN_W() / 4, Dimensions.getBTN_H());
        lblBottom = new Label("Gabriel Brito | Giovanni Ferreira | João Lucas | 2016");
        layoutRight = new GridPane();
        emissions = new Emissions();
        setLayoutTop();
        addEvents();
    }

    public void display() {
        layoutLeft1 = new LeftMenuView1();
        handler1 = new LeftMenuHandler1(layoutLeft1, layoutRight, emissions);
        window = new Stage();
        layoutMaster = new BorderPane(null, layoutTop, layoutRight, layoutBottom, layoutLeft1.getLayoutLeft());
        scene = new Scene(layoutMaster, Dimensions.getW(), Dimensions.getH());
        window.setTitle("Country Emissions");
        window.setScene(scene);
        window.show();
    }

    public void switchLeft(LeftMenuView v, int rightWidth) {
        layoutRight.setMinWidth(rightWidth);
        layoutRight.getChildren().removeAll(layoutRight.getChildren());
        layoutMaster.setLeft(v.getLayoutLeft());
    }

    private void setLayoutTop() {
        Utils.setLabelStyle(26, lblTitle);
        GridPane.setConstraints(lblTitle, 0, 0);
        GridPane.setConstraints(btn1, 1, 0);
        GridPane.setConstraints(btn2, 2, 0);
        GridPane.setConstraints(btn3, 3, 0);
        GridPane.setConstraints(btnSair, 4, 0);
        layoutTop = Utils.getGpLayout(0, (Dimensions.getH() / 25) * 2, 0, Pos.CENTER, lblTitle, btnSair, btn1, btn2, btn3);
        layoutTop.setStyle("-fx-background-color: linear-gradient(#98a8bd 0%, #8195af 25%, #6d86a4 100%);");
        setLayoutBottom();
    }

    private void setLayoutBottom() {
        Utils.setLabelStyle(14, lblBottom);
        layoutBottom = Utils.getGpLayout(0, (Dimensions.getH() / 25) * 1, 0, Pos.CENTER_RIGHT, lblBottom);
        layoutBottom.setPadding(new Insets(5));
        layoutBottom.setStyle("-fx-background-color: linear-gradient(#98a8bd 0%, #8195af 25%, #6d86a4 100%);");
        setLayoutRight();
    }

    private void setLayoutRight() {
        layoutRight.setStyle("-fx-background-color: #BBCCDD;");
        layoutRight.setMinWidth(Dimensions.getW_RIGHT1());
        layoutRight.setAlignment(Pos.CENTER);
    }

    private void addEvents() {
        btn1.setOnAction(e -> {
            switchLeft(layoutLeft1, Dimensions.getW_RIGHT1());
        });
        btn2.setOnAction(e -> {
            if (layoutLeft2 == null) {
                layoutLeft2 = new LeftMenuView2();
                handler2 = new LeftMenuHandler2(layoutLeft2, layoutRight, emissions);
            }
            switchLeft(layoutLeft2, Dimensions.getW_RIGHT2());
        });
        btn3.setOnAction(e -> {
            if (layoutLeft3 == null) {
                layoutLeft3 = new LeftMenuView3();
                handler3 = new LeftMenuHandler3(layoutLeft3, layoutRight, emissions);
            }
            switchLeft(layoutLeft3, Dimensions.getW_RIGHT2());
        });
        btnSair.setOnAction(e -> System.exit(0));
    }
}
