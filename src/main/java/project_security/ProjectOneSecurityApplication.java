package project_security;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

import project_security.model.User;
import project_security.repository.UserRepository;

@SpringBootApplication
@EnableMethodSecurity // ✅ Enables @PreAuthorize, @PostAuthorize, etc.
public class ProjectOneSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectOneSecurityApplication.class, args);
		
		
		
	}
	@Bean
	CommandLineRunner init(UserRepository userRepo, PasswordEncoder passwordEncoder) {
	    return args -> {
	        if (userRepo.findByUsername("user1").isEmpty()) {
	            userRepo.save(new User("user1", passwordEncoder.encode("password"), "ROLE_USER"));
	        }
	        if (userRepo.findByUsername("admin").isEmpty()) {
	            userRepo.save(new User("admin", passwordEncoder.encode("admin123"), "ROLE_ADMIN"));
	        }
	    };
	}


}
