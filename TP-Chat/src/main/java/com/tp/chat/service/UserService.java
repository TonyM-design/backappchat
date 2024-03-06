package com.tp.chat.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.chat.entity.Canal;
import com.tp.chat.entity.User;
import com.tp.chat.repository.CanalRepository;
import com.tp.chat.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	CanalRepository	canalRepository;
	public Boolean addUser(User user) {
		if (user.getEmail() != null && user.getName() != null && user.getNickname() != null) {
			user.setBadgeColor(generateColor());
			userRepository.save(user);
			return true;
		} else {
			return false;
		}
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User getUserById(Integer id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			User foundUser = optionalUser.get();
			return foundUser;
		} else {
			return new User();
		}
	}

	public List<User> getUsersByCanal(Integer canalId) {
        Canal canal = canalRepository.findById(canalId).orElse(null);
        if (canal != null) {
            return canalRepository.findUsersByCanal(canal);
        } else {
            return Collections.emptyList();
        }
    }

	public Boolean deleteUserById(Integer id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			userRepository.deleteById(id);
			;
			return true;
		} else
			return false;
	}

	// public Boolean updateUserById(Integer id, User user) {
	// 	Optional<User> optionalUser = userRepository.findById(id);
	// 	if (optionalUser.isPresent() && user.getId() == id) {
	// 		userRepository.save(null);
	// 		return true;
	// 	} else {
	// 		return false;
	// 	}

	// }

	public String generateColor(){
		StringBuilder color = new StringBuilder("#");
    String characters = "0123456789ABCDEF";
		for (int i = 0; i < 6; i++) {
			int index = (int) Math.floor(Math.random() * 16);
        color.append(characters.charAt(index));}
		return color.toString();
	}

	public User updateUserById(Integer id, User user) {
        Optional<User> existingUserOpt = userRepository.findById(id);

        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            if (user.getName() != null) existingUser.setName(user.getName());
            if (user.getNickname() != null) existingUser.setNickname(user.getNickname());
            if (user.getEmail() != null) existingUser.setEmail(user.getEmail());
            return userRepository.save(existingUser);
        }

        return null;  
    }


	

	public Optional<User> signIn(String email, String password) { 
		return userRepository.findByEmailAndPassword(email, password);
    }
    public Optional<User> getUserByEmailAndPassword(String userEmail, String userPassword) {
        return userRepository.findByEmailAndPassword(userEmail, userPassword);
    }
    public Optional<User> getUserByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail);
    }
    public Optional<User> getUserByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

}
