package com.custom555.adverto24.domain.advertisement.enums;

public enum WorkingTime {
    FULL_TIME("Pełny etat"),
    PART_TIME("Niepełny etat"),
    SEASONAL("Praca sezonowa"),
    INTERNSHIP("Staż");

    private String name;
    WorkingTime(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
}
