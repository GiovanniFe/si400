package com.si400.view;


import java.io.File;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author giovanni
 */
public class SplashView extends Application {

    private ImageView iView;
    private StackPane layout;
    private Stage window;
    private Scene scene;
    private Label label;

    public void display(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage w) throws Exception {
        window = w;
        label = new Label("Em Construção");
        label.setFont(new Font("Verdana", 52));
//        ClassLoader cl = this.getClass().getClassLoader();        
//        iView = new ImageView(new Image(cl.getResourceAsStream("/com/si400/resource/splash.jpg")));
        layout = new StackPane();        
        layout.getChildren().add(label);
        scene = new Scene(layout, 515, 307);        
        window.setScene(scene);        
        window.show();
    }

}
