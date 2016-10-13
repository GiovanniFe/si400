package com.si400.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author g168746
 */
public class WarningView {

    private static Stage stage;
    private static Scene scene;
    private static GridPane gp;
    private static Label lbl;
    private static Button btn;

    public static void display(String message, String btnMessage) {
        stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        lbl = new Label(message);
        btn = new Button(btnMessage);
        GridPane.setColumnSpan(lbl, 3);
        GridPane.setConstraints(btn, 1, 1);
        GridPane.setConstraints(lbl, 0, 0);
        gp.getChildren().addAll(btn, lbl);
        gp.setPadding(new Insets(15));
        scene = new Scene(gp, 300, 100);
        stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait();
    }
}
