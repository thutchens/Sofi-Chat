package services;
import static org.fest.assertions.Assertions.assertThat;

import configs.AppConfig;

import controllers.TestDataConfig;

import models.db.Message;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(classes = {AppConfig.class, TestDataConfig.class})
public class MessageTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private MessageService messageService;

    // Test that a message can be added
    @Test
    public void callAddMessage() {
        Message msg = new Message();
        msg.setMsg("test");
        msg.setMsgFrom("tester");
        messageService.addMessage(msg);
        assertThat(msg.getId()).isNotNull();
    }
}
