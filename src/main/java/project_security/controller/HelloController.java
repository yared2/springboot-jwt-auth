package project_security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "✅ Hello! You're authenticated using JWT!";
    }
    @GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminOnly() {
	    return "🔐 Hello Admin! This endpoint is restricted to ROLE_ADMIN.";
	}
}
