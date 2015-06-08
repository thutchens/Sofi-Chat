package services;

import models.Message;

import java.util.List;

public interface MessageService {

    void addMessage(Message msg);

    List<Message> getMessages();
}
