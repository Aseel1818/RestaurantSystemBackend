package com.RestaurantSystemDB.RestaurantSystemDB.Audit;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.time.LocalDateTime;

@Entity
@Table(name = "audit_log")
@EntityListeners(AuditingEntityListener.class)
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    @CreatedBy
    private Long userId;

    @Column(name = "table_name")
    @NotBlank
    private String tableName;

    @Column(name = "operation")
    @NotBlank
    private String operation;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    // Constructors

    public AuditLog() {
    }

    public AuditLog(Long userId, String tableName, String operation, LocalDateTime createdAt) {
        this.userId = userId;
        this.tableName = tableName;
        this.operation = operation;
        this.createdAt = createdAt;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
