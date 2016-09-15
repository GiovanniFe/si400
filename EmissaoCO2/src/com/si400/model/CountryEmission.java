package com.si400.model;

/**
 *
 * @author Giovanni
 */
public class CountryEmission {

    private String name;
    private Double[] eletricityAndHeat;
    private Double[] industryAndConstruction;
    private Double[] buildingsAndCommercial;
    private Double[] tranport;
    private Double[] otherSector;

    public CountryEmission(String name, Double[] eletricityAndHeat, Double[] industryAndConstruction, Double[] buildingsAndCommercial, Double[] tranport, Double[] otherSector) {
        this.name = name;
        this.eletricityAndHeat = eletricityAndHeat;
        this.industryAndConstruction = industryAndConstruction;
        this.buildingsAndCommercial = buildingsAndCommercial;
        this.tranport = tranport;
        this.otherSector = otherSector;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double[] getEletricityAndHeat() {
        return eletricityAndHeat;
    }

    public void setEletricityAndHeat(Double[] eletricityAndHeat) {
        this.eletricityAndHeat = eletricityAndHeat;
    }

    public Double[] getIndustryAndConstruction() {
        return industryAndConstruction;
    }

    public void setIndustryAndConstruction(Double[] industryAndConstruction) {
        this.industryAndConstruction = industryAndConstruction;
    }

    public Double[] getBuildingsAndCommercial() {
        return buildingsAndCommercial;
    }

    public void setBuildingsAndCommercial(Double[] buildingsAndCommercial) {
        this.buildingsAndCommercial = buildingsAndCommercial;
    }

    public Double[] getTranport() {
        return tranport;
    }

    public void setTranport(Double[] tranport) {
        this.tranport = tranport;
    }

    public Double[] getOtherSector() {
        return otherSector;
    }

    public void setOtherSector(Double[] otherSector) {
        this.otherSector = otherSector;
    }

}
