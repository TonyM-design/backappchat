package com.tp.chat.controller;

import java.util.Collections;
import java.util.List;

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

import com.tp.chat.entity.Canal;
import com.tp.chat.entity.User;
import com.tp.chat.repository.CanalRepository;
import com.tp.chat.service.CanalService;
import com.tp.chat.service.UserService;

@RestController
@RequestMapping("canals")
public class CanalController {

	
	@Autowired
	CanalService canalService;
	@Autowired
    UserService userService;

	@GetMapping
	public List<Canal> getAllCanals() {
		return canalService.getAllCanals();
	}

	@GetMapping("/allCanals/user{id}")
	public List<Canal> getCanalsByUserId(@PathVariable Integer id) {
		User userSearched = userService.getUserById(id);
		if (userSearched.getName() != null) {
			return canalService.getCanalsByUserId(id);
		} else {
			return Collections.emptyList();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity getCanalById(@PathVariable Integer id) {
		Canal canalSearched = canalService.getCanalById(id);
		if (canalSearched.getName() != null) {
			return ResponseEntity.ok(canalSearched);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity addCanal(@RequestBody Canal canal) {
		Boolean hasBeenAdded = canalService.addCanal(canal);
		if (hasBeenAdded) {
			return ResponseEntity.ok(canal);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity updateCanalById(@PathVariable Integer id, @RequestBody Canal canal) {
		if (canalService.updateCanalById(id, canal)) {
			return ResponseEntity.ok("Le canal " + id + " a bien été modifié : " + canal.toString());
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteCanalById(@PathVariable Integer id) {
		if (canalService.deleteCanalById(id)) {
			return ResponseEntity.ok("Le canal " + id + " a bien été supprimé");
		}
		return ResponseEntity.notFound().build();
	}

}
