package com.si400.model;

/**
 *
 * @author giovanni
 */
public class Dimensions {

    private static final int W = 1150;
    private static final int H = 720;
    private static final int H_SIDE = (H / 25) * 22;
    private static final int W_LEFT1 = 330;
    private static final int W_LEFT2 = 230;
    private static final int W_RIGHT1 = W - W_LEFT1;
    private static final int W_RIGHT2 = W - W_LEFT2;
    private static final int BTN_W = ((W / 35) * 8) - 15;
    private static final int BTN_H = (H / 13) - 20;

    public static int getW() {
        return W;
    }

    public static int getH() {
        return H;
    }

    public static int getBTN_W() {
        return BTN_W;
    }

    public static int getBTN_H() {
        return BTN_H;
    }

    public static int getH_SIDE() {
        return H_SIDE;
    }

    public static int getW_LEFT1() {
        return W_LEFT1;
    }

    public static int getW_LEFT2() {
        return W_LEFT2;
    }

    public static int getW_RIGHT1() {
        return W_RIGHT1;
    }

    public static int getW_RIGHT2() {
        return W_RIGHT2;
    }
}
