package com.si400.emissaoco2;

import com.si400.view.SplashView;

/**
 *
 * @author Giovanni
 */
public class EmissaoCO2 {

    public static void main(String[] args) {
        try {
            new SplashView().display();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
