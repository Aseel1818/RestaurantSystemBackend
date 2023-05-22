package com.RestaurantSystemDB.RestaurantSystemDB.Audit;

import jakarta.persistence.Table;

import java.lang.annotation.Annotation;


public class AuditorAwareImpl  {
    public static String getTableName(Class<?> entityClass) {
        Annotation annotation = entityClass.getAnnotation(Table.class);
        if (annotation instanceof Table) {
            Table tableAnnotation = (Table) annotation;
            return tableAnnotation.name();
        }
        return null;
    }



}

