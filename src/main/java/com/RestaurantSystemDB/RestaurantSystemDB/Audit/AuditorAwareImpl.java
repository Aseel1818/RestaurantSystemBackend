package com.RestaurantSystemDB.RestaurantSystemDB.Audit;

import com.RestaurantSystemDB.RestaurantSystemDB.Repositories.ItemsRepository;
import com.RestaurantSystemDB.RestaurantSystemDB.Repositories.UserRepository;
import com.RestaurantSystemDB.RestaurantSystemDB.jwt.JwtUtils;
import jakarta.persistence.Table;
import org.springframework.data.domain.AuditorAware;

import java.lang.annotation.Annotation;
import java.util.Optional;


public class AuditorAwareImpl implements AuditorAware<Long> {
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;

    public AuditorAwareImpl(JwtUtils jwtUtils, UserRepository userRepository ){
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;

    }

    public static String getTableName(Class<?> entityClass) {
        Annotation annotation = entityClass.getAnnotation(Table.class);
        if (annotation instanceof Table) {
            Table tableAnnotation = (Table) annotation;
            return tableAnnotation.name();
        }
        return null;
    }


    @Override
    public Optional<Long> getCurrentAuditor() {
        return Optional.empty();
    }
}

