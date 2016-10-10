package com.si400.model;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 *
 * @author giovanni
 */
public class Utils {

    public static Button getMenuButton(String text) {
        Button btn = new Button(text);
        btn.setMinSize(0, 0);
        return btn;
    }

    public static GridPane getGpLeftLayout(List nodes, int minWidth, int minHeight, String bgColor) {
        GridPane gp = new GridPane();
        gp.setStyle("-fx-background-color: #AABBCC;");
        gp.setMinWidth(minWidth);
        gp.setMinHeight(minHeight);
        gp.getChildren().addAll(nodes);
        gp.setPadding(new Insets(15));
        gp.setVgap(20);
        gp.setHgap(20);
        return gp;
    }

    public static List getNodeList(Node... nodes) {
        List list = new ArrayList();
        for (Node n : nodes) {
            list.add(n);
        }
        return list;
    }
}
