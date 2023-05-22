package com.RestaurantSystemDB.RestaurantSystemDB.Services;

import com.RestaurantSystemDB.RestaurantSystemDB.Audit.AuditLog;
import com.RestaurantSystemDB.RestaurantSystemDB.Repositories.AuditLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuditLogService {
    private final AuditLogRepository auditLogRepository;

    public AuditLogService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    public void createAuditLog(Long userId, String tableName, String operation) {
        AuditLog auditLog = new AuditLog();
        auditLog.setUserId(userId);
        auditLog.setTableName(tableName);
        auditLog.setOperation(operation);
        auditLog.setCreatedAt(LocalDateTime.now());

        auditLogRepository.save(auditLog);
    }
}
