package com.tp.chat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.tp.chat.entity.Message;
import com.tp.chat.repository.MessageRepository;

@Service
public class MessageService {

	@Autowired
	MessageRepository messageRepository;

	public Boolean addMessage(Message message) {
		if (message.getContent() != null && message.getDate() != null) {
			messageRepository.save(message);
			return true;
		} else {
			return false;
		}
	}

	public List<Message> getAllMessages() {
		return messageRepository.findAll();
	}

	public Message getMessageById(Integer id) {
		Optional<Message> optionalMessage = messageRepository.findById(id);
		if (optionalMessage.isPresent()) {
			Message foundMessage = optionalMessage.get();
			return foundMessage;
		} else {
			return new Message();
		}
	}

	public Boolean deleteMessageById(Integer id) {
		Optional<Message> optionalMessage = messageRepository.findById(id);
		if (optionalMessage.isPresent()) {
			messageRepository.deleteById(id);
			;
			return true;
		} else
			return false;
	}

	public Boolean updateMessageById(Integer id, Message message) {
		Optional<Message> optionalMessage = messageRepository.findById(id);
		if (optionalMessage.isPresent() && message.getId() == id) {
			messageRepository.save(null);
			return true;
		} else {
			return false;
		}
	}

	public List<Message> getMessagesByCanalId(Integer canalId) {
		return messageRepository.findByCanalId(canalId);
	}
	
	public List<Message> get15LastMessagesByCanalId(@PathVariable Integer canalId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size) {
		Pageable pageable = PageRequest.of(page, size);
		return messageRepository.get15LastMessageByCanalId(canalId,pageable);
	}
}
