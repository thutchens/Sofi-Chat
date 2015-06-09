package services;

import models.db.UserLogin;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.List;

@Service
@Transactional
public class UserLoginServiceImpl implements UserLoginService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public String getDisplayName(UserLogin user) {
        Query query = em.createQuery("SELECT dName FROM UserLogin u WHERE u.uName = :uname AND u.pword = :pword")
                        .setParameter("uname", user.getuName())
                        .setParameter("pword", user.getPword());

        // Get all query results for error checking
        List results = query.getResultList();

        String displayName;

        if (!results.isEmpty()) {
            Object result = query.getSingleResult();
            displayName = (String)(result);
        } else {
            displayName = null;
        }

        return displayName;
    }
}
