package services;

import models.Message;

import com.google.common.collect.Lists;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@Transactional
public class MessageServiceImpl implements MessageService{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addMessage(Message msg) {
        em.persist(msg);
    }

    @Override
    public List<Message> getMessages() {
        List<Message> messages = em.createQuery("SELECT m FROM Message m").getResultList();
        return Lists.reverse(messages);
    }
}
