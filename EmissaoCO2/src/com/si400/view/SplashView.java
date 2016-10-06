package com.si400.view;


import java.io.File;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author giovanni
 */
public class SplashView extends Application {

    private ImageView iView;
    private Pane layout;
    private Stage window;
    private Scene scene;
    private Label label, label1, label2;

    public static void main(String[] args) {
        launch(args);
    }
    public void display(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage w) throws Exception {
        window = w;
        label = new Label("Emissões de Dióxido de Carbono");
        label.setFont(new Font("Arial Black", 32));
        label1 = new Label("por Setor");
        label.setFont(new Font("Arial Black", 32));
        label2 = new Label("loading...");
        label.setFont(new Font("Arial Black", 18));
        label.setLayoutX(0);
        label.setLayoutY(0);
        label1.setLayoutY(100);
        label1.setLayoutX(250);
        label2.setLayoutY(200);
        label2.setLayoutX(400);        
        iView = new ImageView(new Image(getClass().getResourceAsStream("/com/si400/resource/splash.jpg")));
        layout = new Pane();        
        layout.getChildren().addAll(iView, label, label1, label2);
        scene = new Scene(layout, 515, 307);        
        window.setScene(scene);        
        window.show();
    }

}
