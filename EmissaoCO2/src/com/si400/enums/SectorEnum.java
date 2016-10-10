package com.si400.enums;

/**
 *
 * @author Giovanni
 */
public enum SectorEnum {

    ETOT("Produção de energia e calor"),
    MANF("Indústria e construção"),
    BLDG("Casas e prédios comerciais e públicos"),
    TRAN("Transporte"),
    OTHX("Outros Setores");
    private String text;

    private SectorEnum(String t) {
        text = t;
    }

    @Override
    public String toString() {
        return this.text;
    }

}
