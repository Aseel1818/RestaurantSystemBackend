package com.RestaurantSystemDB.RestaurantSystemDB;
import com.RestaurantSystemDB.RestaurantSystemDB.Audit.AuditorAwareImpl;
import com.RestaurantSystemDB.RestaurantSystemDB.Repositories.ItemsRepository;
import com.RestaurantSystemDB.RestaurantSystemDB.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import com.RestaurantSystemDB.RestaurantSystemDB.jwt.JwtUtils;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class RestaurantSystemDbApplication {
	@Autowired
	private ItemsRepository itemsRepository ;
	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(RestaurantSystemDbApplication.class, args);
	}

	@Bean
	public AuditorAware<Long> auditorAware() {
		return new AuditorAwareImpl(jwtUtils, userRepository);
	}
}






