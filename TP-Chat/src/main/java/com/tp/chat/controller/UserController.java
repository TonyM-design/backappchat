package com.tp.chat.controller;

import java.util.Collections;
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

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;
import com.tp.chat.entity.Canal;
import com.tp.chat.entity.LoginRequest;
import com.tp.chat.entity.User;
import com.tp.chat.repository.UserRepository;
import com.tp.chat.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	UserService userService;



	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/associate/{id}")
	public ResponseEntity getUsersByCanalId(@PathVariable Integer id) {
		List<User> users = userService.getUsersByCanal(id);
        return new ResponseEntity<>(users, HttpStatus.OK);
	}


	@GetMapping("/{id}")
	public ResponseEntity getUserById(@PathVariable Integer id) {
		User userSearched = userService.getUserById(id);
		if (userSearched.getName() != null) {
			return ResponseEntity.ok(userSearched);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping
	public ResponseEntity addUser(@RequestBody User user) {
		String userEmail = user.getEmail();
		String userNickname = user.getNickname();
		Optional<User> existingEmail = userService.getUserByEmail(userEmail);
		Optional<User> existingNickname = userService.getUserByNickname(userNickname);
		System.out.println(existingNickname.isPresent());
	
		if (existingEmail.isPresent() || existingNickname.isPresent()) {
			return ResponseEntity.badRequest().body("Un utilisateur avec le même email ou le meme nom d'utilisateur existe déjà.");
		} else {
			Boolean hasBeenAdded = userService.addUser(user);
			if (hasBeenAdded) {
				return ResponseEntity.ok(user);
			} else {
				return ResponseEntity.badRequest().body("Impossible d'ajouter l'utilisateur.");
			}
		}
	}

	// @PutMapping("/{id}")
	// public ResponseEntity updateUserById(@PathVariable Integer id, @RequestBody
	// User user) {
	// if (userService.updateUserById(id, user)) {
	// return ResponseEntity.ok("L'utilisateur " + id + " a bien été modifié : " +
	// user.toString());
	// }
	// return ResponseEntity.notFound().build();

	// }
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUserById(@PathVariable Integer id, @RequestBody User user) {
		User updatedUser = userService.updateUserById(id, user);
		if (updatedUser != null) {
			return ResponseEntity.ok(updatedUser);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteUserById(@PathVariable Integer id) {
		if (userService.deleteUserById(id)) {
			return ResponseEntity.ok("L'utilisateur " + id + " a bien été supprimé");
		}
		return ResponseEntity.notFound().build();
	}

	// @PostMapping("/signIn")
	// public ResponseEntity<Boolean> signIn(@RequestBody LoginRequest request) {
	// boolean isAuthenticated = userService.signIn(request.getEmail(),
	// request.getPassword());
	// return ResponseEntity.ok(isAuthenticated);
	// }

	@PostMapping("/login")
	public ResponseEntity<User> signIn(@RequestBody LoginRequest request) {
		Optional<User> authenticatedUser = userService.signIn(request.getEmail(), request.getPassword());

		if (authenticatedUser.isPresent()) {
			User user = authenticatedUser.get();
			return ResponseEntity.ok(user);
		}

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // ou tout autre réponse appropriée
	}

}
