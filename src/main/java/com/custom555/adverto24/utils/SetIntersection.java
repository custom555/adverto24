package com.custom555.adverto24.utils;

import java.util.List;
import java.util.Set;

public class SetIntersection {
    public static <T> Set<T> getIntersection(List<Set<T>> setList){
        Set<T> resultSet = setList.remove(0);
        if(!setList.isEmpty()) {
            for (Set<T> s : setList) {
                resultSet.retainAll(s);
            }
        }
        return resultSet;
    }
}
