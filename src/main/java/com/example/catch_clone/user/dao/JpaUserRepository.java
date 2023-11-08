package com.example.catch_clone.user.dao;

import com.example.catch_clone.user.entity.User;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaUserRepository implements UserRepository{

    private final EntityManager em;

    public JpaUserRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = em.find(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {

        List<User> result = em.createQuery("select u from users u where u.username =:username", User.class)
            .setParameter("username", username)
            .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select u from users u", User.class)
            .getResultList();
    }
}
