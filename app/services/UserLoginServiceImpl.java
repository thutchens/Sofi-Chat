package services;

import models.db.UserLogin;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@Transactional
public class UserLoginServiceImpl implements UserLoginService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public String getDisplayName(UserLogin user) {
        List<?> results = em.createQuery("SELECT dName FROM UserLogin u WHERE u.uName = :uname AND u.pword = :pword")
                            .setParameter("uname", user.getuName())
                            .setParameter("pword", user.getPword())
                            .getResultList();

        String displayName;

        if (!results.isEmpty()) {
            Object result = results.get(0);
            displayName = (String)(result);
        } else {
            displayName = null;
        }

        return displayName;
    }
}
