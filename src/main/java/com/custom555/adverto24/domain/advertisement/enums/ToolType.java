package com.custom555.adverto24.domain.advertisement.enums;

public enum ToolType {
    HAMMER("Młotki"),
    SCREWDRIVER("Śrubokręty"),
    WRENCH("Klucze francuskie"),
    SAW("Piły"),
    POWER("Elektronarzędzia");

    private String name;
    ToolType(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
}
