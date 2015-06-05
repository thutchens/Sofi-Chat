package services;

import models.User;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    public String findUser(User user) {
        String displayName;
        String userName = user.getUserName();
        String password = user.getPassword();

        Query query = em.createQuery("SELECT dName FROM User u WHERE u.userName = :uname AND u.password = :pword")
                    .setParameter("uname", userName)
                    .setParameter("pword", password);

        //Get all query results for error checking
        List results = query.getResultList();

        if (!results.isEmpty()){
            Object result = query.getSingleResult();
            displayName = (String)(result);
        }
        else{
            displayName = null;
        }

        return displayName;
    }
}
