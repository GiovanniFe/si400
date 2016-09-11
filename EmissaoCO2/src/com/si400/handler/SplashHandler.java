/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.si400.handler;

import com.si400.view.SplashView;

/**
 *
 * @author Giovanni
 */
public class SplashHandler {

    private final SplashView view;

    public SplashHandler(SplashView v) {
        view = v;
        view.setVisible(true);
    }

}
