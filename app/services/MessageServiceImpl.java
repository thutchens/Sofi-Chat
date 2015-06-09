package services;

import models.db.Message;

import com.google.common.collect.Lists;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    @PersistenceContext
    private EntityManager em;

    private static final int MAX_MESSAGES = 50;

    @Override
    public void addMessage(Message msg) {
        em.persist(msg);
    }

    @Override
    public List<Message> getMessages() {
        List<Message> messages = em.createQuery("SELECT m FROM Message m order by m.id DESC", Message.class)
                                   .setMaxResults(MAX_MESSAGES)
                                   .getResultList();
        return messages;
    }
}
