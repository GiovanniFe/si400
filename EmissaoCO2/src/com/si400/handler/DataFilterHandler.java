package com.si400.handler;

import com.si400.model.Emissions;
import com.si400.view.DataFilterView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author giovanni
 */
public class DataFilterHandler {

    private final DataFilterView view;
    private Emissions emissions;

    public DataFilterHandler(DataFilterView v, Emissions e) {
        view = v;
        emissions = e;
        try {
            emissions.load();
        } catch (IOException err) {
            err.printStackTrace();
        }
        setModel();
        view.setVisible(true);
    }

    private void setModel() {
        List list = new ArrayList<String>();
        for (String key : emissions.getEmissions().keySet()) {
            list.add(key);
        }
        java.util.Collections.sort(list);

        view.getCbPais().setModel(new DefaultComboBoxModel(list.toArray()));
    }

}
