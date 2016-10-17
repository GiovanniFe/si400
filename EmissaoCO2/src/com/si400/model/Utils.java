package com.si400.model;

import com.si400.view.MenuView;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

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

        btn.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> btn.getScene().setCursor(Cursor.HAND));
        btn.addEventHandler(MouseEvent.MOUSE_EXITED, e -> btn.getScene().setCursor(Cursor.DEFAULT));
        return btn;
    }

    public static GridPane getGpLayout(int minWidth, int minHeight, Pos pos, Node... nodes) {
        GridPane gp = new GridPane();
        gp.setMinSize(minWidth, minHeight);
        gp.getChildren().addAll(nodes);
        gp.setVgap(20);
        gp.setHgap(20);
        gp.setPadding(new Insets(15));
        gp.setAlignment(pos);
        return gp;
    }

    public static List getNodeList(Node... nodes) {
        List list = new ArrayList();
        for (Node n : nodes) {
            list.add(n);
        }
        return list;
    }

    public static void setLabelStyle(int fSize, Label... lbl) {
        for (Label l : lbl) {
            l.setFont(new Font(fSize));
            l.setTextFill(Color.web("#FFFFFF"));
        }
    }

    public static void setCbStyle(ChoiceBox... choice) {
        for (ChoiceBox cb : choice) {
            cb.setMinHeight(30);
            cb.setMinWidth(90);
        }
    }

    public static String formatDouble(Double d) {
        return new DecimalFormat("#.00").format(d);
    }

    public static List getYearList(Map<Integer, Double> yearsMap, List yearList) {
        for (Integer key : yearsMap.keySet()) {
            if (yearsMap.get(key) != null && yearsMap.get(key) != 0d) {
                yearList.add(key.toString());
            }
        }
        return yearList;
    }

    public static List addAllIntegerList(List<Integer> list, Integer... iSet) {
        for (Integer i : iSet) {
            list.add(i);
        }
        return list;
    }

    public static List addAllMapList(List<Map<Integer, Double>> list, Map<Integer, Double>... dSet) {
        for (Map<Integer, Double> d : dSet) {
            list.add(d);
        }
        return list;
    }
}
