package com.ibexsys.pwd.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ibexsys.pwd.entity.User;

@Repository
@Transactional
public class UserRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager em;

    public User findById(Long id) {
        User user = em.find(User.class, id);
        return user;
    }

    public List<User> findAll() {
        TypedQuery<User> namedQuery = em.createNamedQuery("find_all_users", User.class);
        return namedQuery.getResultList();
    }

    public User findByFullName(String first, String last) {
        TypedQuery<User> namedQuery = em.createNamedQuery("find_User_by_full_name", User.class);
        namedQuery.setParameter(0, first);
        namedQuery.setParameter(1, last);

        return namedQuery.getSingleResult();
    }

    public User saveUser(User user) {
        if (user != null) {
            em.merge(user);
        }

        return user;
    }

}
