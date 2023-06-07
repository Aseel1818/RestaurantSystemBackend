package com.RestaurantSystemDB.RestaurantSystemDB.Repositories;

import com.RestaurantSystemDB.RestaurantSystemDB.Audit.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}