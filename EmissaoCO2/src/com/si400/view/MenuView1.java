package com.si400.view;

import com.si400.enums.SectorEnum;
import com.si400.handler.MenuOneHandler;
import com.si400.model.Utils;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

/**
 *
 * @author g168746
 */
public class MenuView1 {

    private Scene scene;
    private BorderPane layoutMaster;
    private GridPane layoutLeft;
    private GridPane layoutRight;
    private GridPane layoutTop;
    private GridPane layoutBottom;
    private int X, Y;
    private MenuOneHandler handler;

    public Scene getScene(int x, int y) {
        X = x;
        Y = y;
        handler = new MenuOneHandler();
        setLayoutLeft();
        layoutMaster = new BorderPane(null, layoutTop, layoutRight, layoutBottom, layoutLeft);
        return scene = new Scene(layoutMaster, X, Y);
    }

    private void setLayoutLeft() {
        Label lbl1 = new Label("Filtros"), lbl2 = new Label("PAÍS"), lbl3 = new Label("SETOR"), lbl4 = new Label("ANO");
        ChoiceBox<String> cb1 = new ChoiceBox<>(), cb3 = new ChoiceBox<>();
        ChoiceBox<SectorEnum> cb2 = new ChoiceBox<SectorEnum>();
        Button btn = new Button("Visualizar");
        lbl1.setFont(new Font(38));
        lbl2.setFont(new Font(18));
        lbl3.setFont(new Font(18));
        lbl4.setFont(new Font(18));
        cb1.setMinHeight(30);
        cb2.setMinHeight(30);
        cb3.setMinHeight(30);
        GridPane.setConstraints(lbl1, 0, 0);
        GridPane.setConstraints(lbl2, 0, 1);
        GridPane.setConstraints(cb1, 0, 2);
        GridPane.setConstraints(lbl3, 0, 3);
        GridPane.setConstraints(cb2, 0, 4);
        GridPane.setConstraints(lbl4, 0, 5);
        GridPane.setConstraints(cb3, 0, 6);
        GridPane.setConstraints(btn, 0, 7);
        GridPane.setHalignment(btn, HPos.RIGHT);
        layoutLeft = Utils.getGpLeftLayout(Utils.getNodeList(lbl1, lbl2, lbl3, lbl4, cb1, cb2, cb3, btn), (X / 4) * 1, 0, "#AABBCC");        
        handler.setCountryModel(cb1);
        handler.setSectorModel(cb2);        
        setLayoutRight();
    }

    private void setLayoutRight() {
        layoutRight = new GridPane();
        layoutRight.setStyle("-fx-background-color: #BBCCDD;");
        layoutRight.setMinWidth((X / 4) * 3);
        setLayoutTop();
    }

    private void setLayoutTop() {
        layoutTop = new GridPane();
        layoutTop.setStyle("-fx-background-color: #99AACC;");
        layoutTop.setMinHeight(Y / 13);
        setLayoutBottom();
    }

    private void setLayoutBottom() {
//        Label la
        layoutBottom = new GridPane();
        layoutBottom.setStyle("-fx-background-color: #CCCCDD;");
        layoutBottom.setMinHeight(30);
    }
}
