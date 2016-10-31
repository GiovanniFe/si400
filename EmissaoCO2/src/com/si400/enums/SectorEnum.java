package com.si400.enums;

/**
 *
 * @author Giovanni
 */
public enum SectorEnum {

    ALLX("All", "All"),
    ETOT("Electricity and heat production", "Eletricity and heat"),
    MANF("Industry and Construction", "Ind. and Construction"),
    BLDG("Houses and public and commercials buildings", "Houses and Buildings"),
    TRAN("Transport", "Transport"),
    OTHX("Other Sectors", "Other Sectors");
    private final String text;
    private final String shortText;

    private SectorEnum(String t, String st) {
        text = t;
        shortText = st;
    }

    @Override
    public String toString() {
        return this.text;
    }

    public String ToShortString() {
        return this.shortText;
    }

}
