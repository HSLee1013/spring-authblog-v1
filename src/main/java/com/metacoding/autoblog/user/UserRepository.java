package com.metacoding.autoblog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager entityManager;

    public User findByUsername(String username) {
        Query q = entityManager.createQuery("select u from User u where u.username = :username", User.class);
        q.setParameter("username", username);
        try {
            return (User) q.getSingleResult();
        } catch (RuntimeException e) {
            throw new RuntimeException("아이디 혹은 패스워드가 일치하지 않습니다.");
        }
    }

    public void save(User entity) {
        entityManager.persist(entity);
    }
}
