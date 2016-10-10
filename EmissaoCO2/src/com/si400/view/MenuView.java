package com.si400.view;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author g168746
 */
public class MenuView {

    private Stage window;
    private final int X = 900, Y = 600;

    public void display() {
        window = new Stage();
        window.setScene(new MenuView1().getScene(X, Y));
        window.show();
    }

    public void display(Scene scene) {
        window = new Stage();
        window.setScene(scene);
        window.show();
    }
}
