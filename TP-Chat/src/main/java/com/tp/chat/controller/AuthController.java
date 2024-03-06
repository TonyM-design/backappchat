package com.tp.chat.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tp.chat.entity.LoginRequest;
import com.tp.chat.entity.User;
import com.tp.chat.service.AuthService;
import com.tp.chat.service.UserService;

@RestController
@RequestMapping("auth")
public class AuthController {

	@Autowired
	AuthService authService;

	@Autowired 
	UserService userService;

	@PostMapping("/signin")
	public ResponseEntity signin(@RequestBody User user) {
		String userEmail = user.getEmail();
		String userNickname = user.getNickname();
		Optional<User> existingEmail = userService.getUserByEmail(userEmail);
		Optional<User> existingNickname = userService.getUserByNickname(userNickname);
		System.out.println(existingNickname.isPresent());
	
		if (existingEmail.isPresent() || existingNickname.isPresent()) {
			return ResponseEntity.badRequest().body("Un utilisateur avec le même email ou le meme nom d'utilisateur existe déjà.");
		} else {
			Boolean hasBeenAdded = authService.signIn(user);
			if (hasBeenAdded) {
				return ResponseEntity.ok(user);
			} else {
				return ResponseEntity.badRequest().body("Impossible d'ajouter l'utilisateur.");
			}
		}
	}

	

	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody LoginRequest request) {
		Optional<User> authenticatedUser = authService.login(request.getEmail(), request.getPassword());

		if (authenticatedUser.isPresent()) {
			User user = authenticatedUser.get();
			return ResponseEntity.ok(user);
		}

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	}

}
