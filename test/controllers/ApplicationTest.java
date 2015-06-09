package controllers;
import static play.test.Helpers.GET;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.route;
import static play.test.Helpers.running;

import configs.AppConfig;

import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.ContextConfiguration;
import org.junit.Test;

import play.libs.ws.WS;
import play.mvc.Result;

import java.util.concurrent.TimeUnit;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

@ContextConfiguration(classes = {
    AppConfig.class, TestDataConfig.class
})
public class ApplicationTest extends AbstractTransactionalJUnit4SpringContextTests {

    // Tests that the index page can be reached
    @Test
    public void loginRoute() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Result result = route(fakeRequest(GET, "/"));
                assertThat(result).isNotNull();
                assertThat(status(result)).isEqualTo(OK);
                assertThat(contentType(result)).isEqualTo("text/html");
                assertThat(charset(result)).isEqualTo("utf-8");
                assertThat(contentAsString(result)).contains("Sofi Chat Room");
            }
        });
    }

    // Tests that the chat room can be reached
    @Test
    public void chatRoute() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Result result = route(fakeRequest(GET, "/chat").withSession("name", "test"));
                assertThat(result).isNotNull();
                assertThat(status(result)).isEqualTo(OK);
                assertThat(contentType(result)).isEqualTo("text/html");
                assertThat(charset(result)).isEqualTo("utf-8");
                assertThat(contentAsString(result)).contains("Sofi Chat Room");
            }
        });
    }

    // Make sure the index site is accessible through http
    @Test
    public void realIndexRequest() {
        running(testServer(3333), new Runnable() {
            public void run() {
                assertThat(WS.url("http://localhost:3333/").get().get(5, TimeUnit.SECONDS).getStatus()).isEqualTo(OK);
            }
        });
    }

    // Make sure that the chat site is accessible through http
    @Test
    public void realUsersRequest() {
        running(testServer(3333), new Runnable() {
            public void run() {
                assertThat(WS.url("http://localhost:3333/chat").get().get(5, TimeUnit.SECONDS).getStatus()).isEqualTo(OK);
            }
        });
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
