package me.vepu.restclient1;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by victor on 10/19/17.
 */

public class Item {

    @JsonProperty("name")
    private String name;

    @JsonProperty("units")
    private int units;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }
}
