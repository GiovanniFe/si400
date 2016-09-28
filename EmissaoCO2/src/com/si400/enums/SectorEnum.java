package com.si400.enums;

/**
 *
 * @author Giovanni
 */
public enum SectorEnum {

    ETOT("Eletricty and heat production"),
    MANF("Manufacturing industries and construction"),
    BLDG("Residential buildings and commercial and public services"),
    TRAN("Transport"),
    OTHX("Other Sectors");
    private String text;

    private SectorEnum(String t) {
        text = t;
    }

    @Override
    public String toString() {
        return this.text;
    }

}
