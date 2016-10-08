package com.si400.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author g168746
 */
public class MenuView {

    private Stage window;
    private Scene scene;
    private BorderPane layoutMaster;
    private GridPane layoutLeft;
    private GridPane layoutRight;
    private GridPane layoutTop;
    private GridPane layoutBottom;
    private Button btn1, btn2, btn3, btnSair;
    private final int X = 720, Y = 720;

    public void display() {
        setLayouts();
        scene = new Scene(layoutMaster, X, Y);
        window = new Stage();
        window.setScene(scene);
        window.show();
    }

    private void setLayouts() {
        setLayoutLeft();
        layoutMaster = new BorderPane(null, layoutTop, layoutRight, layoutBottom, layoutLeft);
    }

    private void setLayoutLeft() {
        layoutLeft = new GridPane();
        layoutLeft.setStyle("-fx-background-color: #FF0000;");
        layoutLeft.setMinWidth(50);
        setLayoutRight();
    }

    private void setLayoutRight() {
        layoutRight = new GridPane();
        layoutRight.setStyle("-fx-background-color: #00FF00;");
        layoutRight.setMinWidth(50);
        setLayoutTop();
    }

    private void setLayoutTop() {
        btn1 = new Button();
        layoutTop = new GridPane();
        layoutTop.setStyle("-fx-background-color: #0000FF;");
        layoutTop.setMinHeight(50);
        setLayoutBottom();
    }

    private void setLayoutBottom() {
        layoutBottom = new GridPane();
        layoutBottom.setStyle("-fx-background-color: #FFFFFF;");
        layoutBottom.setMinHeight(Y / 16);
    }

    private Button createButton(String text) {
        Button btn = new Button(text);
        btn.setMinSize(0, 0);
        return btn;
    }

}
