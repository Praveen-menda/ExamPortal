package com.exam;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ExamportalApplication implements CommandLineRunner {

	@Autowired
	UserServiceImpl userService;
	public static void main(String[] args) {
		SpringApplication.run(ExamportalApplication.class, args);
	}

	@Autowired
	BCryptPasswordEncoder b;
	@Override
	public void run(String... args) throws Exception {
	/*	User user = new User();
		user.setFirstname("Praveen");
		user.setLastname("Choudhary");
		user.setPassword(b.encode("qwe"));
		user.setUsername("praveen@gmail.com");
		user.setPhone("1234567890");
		Role role = new Role();
		role.setRoleId(44L);
		role.setRole("ADMIN");
		Set<UserRole> rol = new HashSet<>();
		UserRole userRole = new UserRole();
		userRole.setRole(role);
		rol.add(userRole);
		userService.createUser(user,rol);*/
	}
}
