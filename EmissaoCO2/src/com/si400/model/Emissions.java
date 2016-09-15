package com.si400.model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Collection;

/**
 *
 * @author Giovanni
 */
public class Emissions {

    private Collection<CountryEmission> emissions;

    public Emissions(Collection<CountryEmission> emissions) {
        this.emissions = emissions;
    }

    public Collection<CountryEmission> getEmissions() {
        return emissions;
    }

    public void setEmissions(Collection<CountryEmission> emissions) {
        this.emissions = emissions;
    }

    public void load(URL url) throws IOException {
        Reader source = new InputStreamReader(url.openStream());

    }

}
