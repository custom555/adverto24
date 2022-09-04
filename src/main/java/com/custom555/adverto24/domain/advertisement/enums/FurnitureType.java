package com.custom555.adverto24.domain.advertisement.enums;

public enum FurnitureType {
    CHAIR("Krzesła"),
    ARMCHAIR("Fotele"),
    DESK("Biurka"),
    BED("Łóżka"),
    WARDROBE("Szafy");

    String name;
    FurnitureType(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
}
