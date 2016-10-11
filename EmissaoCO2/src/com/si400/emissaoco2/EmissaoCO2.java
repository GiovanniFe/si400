package com.si400.emissaoco2;
import com.si400.view.SplashView;

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
            new SplashView().display();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
