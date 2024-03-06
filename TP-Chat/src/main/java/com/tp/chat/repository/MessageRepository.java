package com.tp.chat.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tp.chat.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

	List<Message> findByCanalId(Integer canalId);


	@Query("SELECT m FROM Message m WHERE m.canal.id = ?1 ORDER BY m.date DESC")
		List<Message> get15LastMessageByCanalId(Integer canalId, Pageable page);
	

}
