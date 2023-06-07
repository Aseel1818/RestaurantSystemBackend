package com.RestaurantSystemDB.RestaurantSystemDB.Models;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.sql.Timestamp;

@MappedSuperclass
@Data
public class BaseEntity {
    @Column(nullable = false)
    Boolean isDeleted = false;

    @Temporal(TemporalType.TIMESTAMP)
    @Nullable
    Timestamp creationDate;


    @Temporal(TemporalType.TIMESTAMP)
    @Nullable
    Timestamp updateDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Nullable
    Timestamp deleteDate;
}

