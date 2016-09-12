package com.si400.handler;

import com.si400.view.MenuView;
import com.si400.view.SplashView;
import static java.lang.Thread.sleep;

/**
 *
 * @author Giovanni
 */
public class SplashHandler {

    private final SplashView view;

    public SplashHandler(SplashView v) {
        view = v;
        view.setVisible(true);
        loadingTime();
    }

    public void loadingTime() {
        new Thread() {

            @Override
            public void run() {
                try {
                    sleep(2000);
                    view.setVisible(false);
                    new MenuHandler(new MenuView());
                } catch (InterruptedException ie) {
                }
            }
        }.start();
    }
;

}
