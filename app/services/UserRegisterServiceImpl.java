package services;

import models.db.UserRegister;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@Transactional
public class UserRegisterServiceImpl implements UserRegisterService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addUser(UserRegister user) {
        em.persist(user);
    }

    @Override
    public boolean userNameExists(String userName) {
        Object total = em.createQuery("SELECT COUNT(u) FROM UserLogin u WHERE u.uName = :uname")
                         .setParameter("uname", userName)
                         .getSingleResult();

        int numUserNames = ((Number)total).intValue();

        if (numUserNames > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean displayNameExists(String displayName) {
        Object total = em.createQuery("SELECT COUNT(u) FROM UserLogin u WHERE u.dName = :dname")
                         .setParameter("dname", displayName)
                         .getSingleResult();

        int numDisplayNames = ((Number)total).intValue();

        if (numDisplayNames > 0) {
            return true;
        } else {
            return false;
        }
    }

}
