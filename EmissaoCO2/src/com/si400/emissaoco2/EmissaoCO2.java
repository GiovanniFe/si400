package com.si400.emissaoco2;

import com.si400.handler.DataFilterHandler;
import com.si400.model.Emissions;
import com.si400.view.DataFilterView;

/**
 *
 * @author Giovanni
 */
public class EmissaoCO2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            new DataFilterHandler(new DataFilterView(), new Emissions());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        new SplashHandler(new SplashView());
    }
}
