package com.tp.chat.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tp.chat.entity.Canal;
import com.tp.chat.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findByEmail(String email);
    Optional<User> findByNickname(String nickname);

    // find all channels where user have interacted with even if channel is public without users
    @Query("SELECT DISTINCT c FROM Canal c " +
    "JOIN c.messages m " +
    "WHERE m.user.id = :userId")
    List<Canal> findChannelsByMessages_User(@Param("userId") int userId);

}

