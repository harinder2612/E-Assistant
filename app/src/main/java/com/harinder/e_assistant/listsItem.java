package com.harinder.e_assistant;


public class listsItem {
    String networks,science,wireless,name;

    public listsItem(String name,String networks, String science, String wireless) {
        this.networks = networks;
        this.name=name;
        this.science = science;
        this.wireless = wireless;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNetworks() {
        return networks;

    }

    public void setNetworks(String networks) {
        this.networks = networks;
    }

    public String getScience() {
        return science;
    }

    public void setScience(String science) {
        this.science = science;
    }

    public String getWireless() {
        return wireless;
    }

    public void setWireless(String wireless) {
        this.wireless = wireless;
    }
}
