package com.si400.enums;

/**
 *
 * @author Giovanni
 */
public enum SectorEnum {

    ETOT("Electricity and heat production"),
    MANF("Industry and Construction"),
    BLDG("Houses and public and commercials buildings"),
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
