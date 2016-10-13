package com.si400.model;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 *
 * @author giovanni
 */
public class Utils {

    public static Button getButton(String text, int width, int height) {
        Button btn = new Button(text);
        btn.setMinSize(width, height);
        btn.setStyle("-fx-background-color: \n"
                + "#a6b5c9,\n"
                + "linear-gradient(#303842 0%, #3e5577 20%, #375074 100%),\n"
                + "linear-gradient(#768aa5 0%, #849cbb 5%, #5877a2 50%, #486a9a 51%, #4a6c9b 100%);\n"
                + "-fx-text-fill: #242d35;\n"
                + "-fx-font-family: \"Helvetica\";\n"
                + "-fx-font-size: 12px;\n"
                + "-fx-text-fill: white;");
        btn.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            btn.setStyle("-fx-background-color: \n"
                    + "#B7c6dA,\n"
                    + "linear-gradient(#414953 0%, #4f6688 20%, #486185 100%),\n"
                    + "linear-gradient(#879bb6 0%, #95adcc 5%, #6988b3 50%, #597bab 51%, #5b7dac 100%);\n"
                    + "-fx-text-fill: #242d35;\n"
                    + "-fx-font-family: \"Helvetica\";\n"
                    + "-fx-font-size: 12px;\n"
                    + "-fx-text-fill: white;");
        });
        btn.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            btn.setStyle("-fx-background-color: \n"
                    + "#a6b5c9,\n"
                    + "linear-gradient(#303842 0%, #3e5577 20%, #375074 100%),\n"
                    + "linear-gradient(#768aa5 0%, #849cbb 5%, #5877a2 50%, #486a9a 51%, #4a6c9b 100%);\n"
                    + "-fx-text-fill: #242d35;\n"
                    + "-fx-font-family: \"Helvetica\";\n"
                    + "-fx-font-size: 12px;\n"
                    + "-fx-text-fill: white;");
        });
        return btn;
    }

    public static GridPane getGpLeftLayout(List nodes, int minWidth, int minHeight) {
        GridPane gp = getGpLayout(nodes, minWidth, minHeight);
        gp.setPadding(new Insets(15));
        return gp;
    }

    public static GridPane getGpTopLayout(List nodes, int minWidth, int minHeight) {
        GridPane gp = getGpLayout(nodes, minWidth, minHeight);
        gp.setPadding(new Insets(8));
        return gp;
    }

    private static GridPane getGpLayout(List nodes, int minWidth, int minHeight) {
        GridPane gp = new GridPane();
        gp.setMinWidth(minWidth);
        gp.setMinHeight(minHeight);
        gp.getChildren().addAll(nodes);
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
