package com.custom555.adverto24.domain.advertisement.enums;

public enum State {
    NEW("Nowe"),
    USED("Używane"),
    DAMAGED("Uszkodzone");

    private String name;

    State(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
}
