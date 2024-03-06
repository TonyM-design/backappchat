package com.tp.chat.repository;


import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tp.chat.entity.Canal;
import com.tp.chat.entity.User;

@Repository
public interface CanalRepository extends JpaRepository<Canal, Integer> {
    @Query("SELECT DISTINCT m.user FROM Canal c JOIN c.messages m WHERE c = :canal")
    List<User> findUsersByCanal(@Param("canal") Canal canal);
}
