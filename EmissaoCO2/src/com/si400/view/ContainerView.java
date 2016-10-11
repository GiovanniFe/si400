package com.si400.view;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author g168746
 */
public class ContainerView {

    private Stage window;

    public void display(Scene scene) {
        window = new Stage();
        window.setScene(scene);
        window.show();
    }
}
