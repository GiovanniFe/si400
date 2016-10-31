package com.si400.abstracts;

import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;

/**
 *
 * @author giovanni
 */
public interface LeftMenuView {

    public GridPane getLayoutLeft();

    public ChoiceBox getCbCountry();
}
