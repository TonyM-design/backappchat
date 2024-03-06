package com.tp.chat.controller;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tp.chat.entity.Message;
import com.tp.chat.service.MessageService;

@RestController
@RequestMapping("messages")
public class MessageController {

	@Autowired
	MessageService messageService;

	@GetMapping
	public List<Message> getAllMessages() {
		return messageService.getAllMessages();
	}

	@GetMapping("/{id}")
	public ResponseEntity getMessageById(@PathVariable Integer id) {
		Message messageSearched = messageService.getMessageById(id);
		if (messageSearched.getContent() != null) {
			return ResponseEntity.ok(messageSearched);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@GetMapping("/canal/{id}")
	public List<Message> getMessageByCanalId(@PathVariable Integer id) {
		return messageService.getMessagesByCanalId(id);
	}
	@GetMapping("/canal/{id}/test")
	public List<Message> get15LastMessageByCanalId(@PathVariable Integer id, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "15") Integer size) {
		List<Message> messageList = messageService.get15LastMessagesByCanalId(id,page, size);
		int startIndex = Math.max(0, messageList.size() - 15); // Pour s'assurer que l'indice de début est valide
		List<Message> lastMessages = messageList.subList(startIndex, messageList.size());
		return lastMessages;
	}
	

	@PostMapping
	public ResponseEntity addMessage(@RequestBody Message message) {
		message.setDate(Date.from(Instant.now()));
		Boolean hasBeenAdded = messageService.addMessage(message);
		if (hasBeenAdded) {
			return ResponseEntity.ok(message);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity updateMessageById(@PathVariable Integer id, @RequestBody Message message) {
		if (messageService.updateMessageById(id, message)) {
			return ResponseEntity.ok("Le message " + id + " a bien été modifié: " + message.toString());
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteMessageById(@PathVariable Integer id) {
		if (messageService.deleteMessageById(id)) {
			return ResponseEntity.ok("Le message " + id + " a bien été supprimé");
		}
		return ResponseEntity.notFound().build();
	}

}
