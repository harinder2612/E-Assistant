package com.harinder.e_assistant;



public class oldAttnItem {

    String name,state;
    int progress;

    public oldAttnItem(String name, String state, int progress) {
        this.name = name;
        this.state = state;
        this.progress = progress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
