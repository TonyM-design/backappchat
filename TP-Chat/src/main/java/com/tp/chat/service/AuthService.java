package com.tp.chat.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.chat.entity.User;
import com.tp.chat.repository.UserRepository;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    public  Optional<User> login(String username, String password) {    
        return userRepository.findByEmailAndPassword(username, password);    
    }

    public Boolean signIn(User user) {
		if (user.getEmail() != null && user.getName() != null && user.getNickname() != null) {
			userRepository.save(user);
			return true;
		} else {
			return false;
		}
	}
}
