package com.tp.chat.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.chat.entity.Canal;
import com.tp.chat.entity.User;
import com.tp.chat.repository.CanalRepository;
import com.tp.chat.repository.UserRepository;

@Service
public class CanalService {

	@Autowired
	CanalRepository canalRepository;
	@Autowired
	UserRepository userRepository;

	public Boolean addCanal(Canal canal) {			System.out.println(canal);

		if (canal.getName() != null) {
			canalRepository.save(canal);
			return true;
		} else {
			return false;
		}
	}

	public List<Canal> getAllCanals() {
		return canalRepository.findAll();
	}

	public Canal getCanalById(Integer id) {
		Optional<Canal> optionalCanal = canalRepository.findById(id);
		if (optionalCanal.isPresent()) {
			Canal foundCanal = optionalCanal.get();
			return foundCanal;
		} else {
			return new Canal();
		}
	}

	public Boolean deleteCanalById(Integer id) {
		Optional<Canal> optionalCanal = canalRepository.findById(id);
		if (optionalCanal.isPresent()) {
			canalRepository.deleteById(id);
			;
			return true;
		} else
			return false;
	}

	public Boolean updateCanalById(Integer id, Canal canal) {
		System.out.println("-------------------------------------------------------");
		Optional<Canal> optionalCanal = canalRepository.findById(id);
		
		if (optionalCanal.isPresent() && canal.getId() == id) {
			canalRepository.save(canal);
			return true;
		} else {
			return false;
		}
	}


	public List<Canal> getCanalsByUserId(Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return userRepository.findChannelsByMessages_User(userId);
        } else {
            return Collections.emptyList();
        }
    }

	public Map<Canal, Integer> getActiveCanalsLength(Integer userId){
		List<Canal> canalsToGetLength = getCanalsByUserId(userId);
		Map<Canal, Integer> canalLengthMap = new HashMap<Canal, Integer>();
		for(Canal canal: canalsToGetLength){
			int lengthMessages = canal.getMessages().size();
		canalLengthMap.put(canal, lengthMessages);	
		}
		return canalLengthMap;

	}
}
