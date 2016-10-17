package com.si400.view;

import com.si400.handler.MenuHandler1;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

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

    public void display() {
        launch();
    }

    @Override
    public void start(Stage w) throws Exception {
        window = w;
        setLabels();
        iView = new ImageView(new Image(getClass().getResourceAsStream("/com/si400/resource/splash.jpg")));
        layout = new Pane();
        layout.getChildren().addAll(iView, label, label1, label2);
        scene = new Scene(layout, 515, 307);
        window.setScene(scene);
        window.show();
        PauseTransition delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished(e -> {
            window.close();
            new ContainerView().display(new MenuHandler1(new MenuView1(900, 650)).getScene());
        });
        delay.play();
    }

    private void setLabels() {
        label = new Label("Carbon Dioxide Emissions");
        label.setFont(new Font("Arial Black", 20));
        label.setLayoutX(150);
        label.setLayoutY(180);
        label.setTextFill(new Color(1, 1, 1, 1));

        label1 = new Label("by Sector");
        label1.setFont(new Font("Arial Black", 18));
        label1.setLayoutX(300);
        label1.setLayoutY(215);
        label1.setTextFill(new Color(1, 1, 1, 1));

        label2 = new Label("loading...");
        label2.setFont(new Font("Arial Black", 12));
        label2.setLayoutX(430);
        label2.setLayoutY(275);
        label2.setTextFill(new Color(1, 1, 1, 1));
    }

}
