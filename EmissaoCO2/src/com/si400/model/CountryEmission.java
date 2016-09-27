package com.si400.model;

import java.util.Map;

/**
 *
 * @author Giovanni
 */
public class CountryEmission {

    private Map<Integer, Double> eletricityAndHeat;
    private Map<Integer, Double> industryAndConstruction;
    private Map<Integer, Double> buildingsAndCommercial;
    private Map<Integer, Double> transport;
    private Map<Integer, Double> otherSector;

    public CountryEmission() {
    }

    public CountryEmission(Map<Integer, Double> eletricityAndHeat, Map<Integer, Double> industryAndConstruction, Map<Integer, Double> buildingsAndCommercial, Map<Integer, Double> transport, Map<Integer, Double> otherSector) {
        this.eletricityAndHeat = eletricityAndHeat;
        this.industryAndConstruction = industryAndConstruction;
        this.buildingsAndCommercial = buildingsAndCommercial;
        this.transport = transport;
        this.otherSector = otherSector;
    }

    public Map<Integer, Double> getEletricityAndHeat() {
        return eletricityAndHeat;
    }

    public void setEletricityAndHeat(Map<Integer, Double> eletricityAndHeat) {
        this.eletricityAndHeat = eletricityAndHeat;
    }

    public Map<Integer, Double> getIndustryAndConstruction() {
        return industryAndConstruction;
    }

    public void setIndustryAndConstruction(Map<Integer, Double> industryAndConstruction) {
        this.industryAndConstruction = industryAndConstruction;
    }

    public Map<Integer, Double> getBuildingsAndCommercial() {
        return buildingsAndCommercial;
    }

    public void setBuildingsAndCommercial(Map<Integer, Double> buildingsAndCommercial) {
        this.buildingsAndCommercial = buildingsAndCommercial;
    }

    public Map<Integer, Double> getTransport() {
        return transport;
    }

    public void setTransport(Map<Integer, Double> tranport) {
        this.transport = tranport;
    }

    public Map<Integer, Double> getOtherSector() {
        return otherSector;
    }

    public void setOtherSector(Map<Integer, Double> otherSector) {
        this.otherSector = otherSector;
    }

}
