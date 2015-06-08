import configs.AppConfig;

import models.Message;

import services.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.ContextConfiguration;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;
import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;
import org.junit.Test;

import play.mvc.Result;
import static play.test.Helpers.*;

@ContextConfiguration(classes = {
    AppConfig.class, TestDataConfig.class
})
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

    // Tests that chat messages can be found
    @Test
    public void getChatsRoute() {
        running(fakeApplication(), new Runnable() {
            @Override
            public void run() {
                Result result = route(fakeRequest(GET, "/chats"));
                assertThat(result).isNotNull();
            }
        });
    }
}
