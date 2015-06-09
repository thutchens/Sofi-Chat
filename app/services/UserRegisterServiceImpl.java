package services;

import models.db.UserRegister;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@Transactional
public class UserRegisterServiceImpl implements UserRegisterService{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addUser(UserRegister user) {
        em.persist(user);
    }

}
