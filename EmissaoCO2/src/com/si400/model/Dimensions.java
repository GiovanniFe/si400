package com.si400.model;

/**
 *
 * @author giovanni
 */
public class Dimensions {

    private static final int W = 1080;
    private static final int H = 720;
    private static final int H_SIDE = (H / 25) * 22;
    private static final int W_LEFT = 370;
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

    public static int getW_LEFT() {
        return W_LEFT;
    }

}
