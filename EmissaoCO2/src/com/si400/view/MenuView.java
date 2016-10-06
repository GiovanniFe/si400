package com.si400.view;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author g168746
 */
public class MenuView extends Application {

    private Stage window;
    private Scene scene;
    private GridPane layout;
    private Button btn1; 
    
    public static void main(String[] args) {
        launch(args);
    }
    public static void display(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        layout = new GridPane();
        btn1 = new Button("teste");
        GridPane.setConstraints(btn1, 0, 0);        
        layout.getChildren().addAll(btn1);
        scene = new Scene(layout, 250, 400);
        window.setScene(scene);
        window.show();
    }

}
