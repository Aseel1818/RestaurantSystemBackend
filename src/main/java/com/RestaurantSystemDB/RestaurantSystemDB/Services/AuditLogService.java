package com.RestaurantSystemDB.RestaurantSystemDB.Services;

import com.RestaurantSystemDB.RestaurantSystemDB.Audit.AuditLog;
import com.RestaurantSystemDB.RestaurantSystemDB.Repositories.AuditLogRepository;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Component
public class AuditLogService {
    private final AuditLogRepository auditLogRepository;
    private final AuditorAware<Long> auditorAware;


    public AuditLogService(AuditLogRepository auditLogRepository, AuditorAware<Long> auditorAware) {
        this.auditLogRepository = auditLogRepository;
        this.auditorAware = auditorAware;
    }

    public void createAuditLog( String tableName, String operation, Long userId) {
        AuditLog auditLog = new AuditLog();
        auditLog.setTableName(tableName);
        auditLog.setOperation(operation);
        auditLog.setUserId(userId);
        auditLog.setCreatedAt(LocalDateTime.now());

        auditLogRepository.save(auditLog);
    }
}
