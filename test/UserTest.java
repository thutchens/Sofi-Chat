import configs.AppConfig;

import models.User;

import services.UserService;

import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.POST;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.route;
import static play.test.Helpers.running;
import org.junit.Test;

import play.mvc.Result;
import play.test.FakeRequest;

import java.util.HashMap;

import play.data.Form;
import play.libs.ws.WS;
import play.twirl.api.Html;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import static play.test.Helpers.*;

@ContextConfiguration(classes = { AppConfig.class, TestDataConfig.class })
public class UserTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private UserService userService;

    // Tests that a user can be found
    //controller test
    @Test
    public void userRoute() {
        running(fakeApplication(), new Runnable() {
            @Override
            public void run() {
                Map<String, String> formParams = new HashMap<String, String>();
                formParams.put("userName", "foo");
                formParams.put("password", "foo");
                formParams.put("displayName", "foo");

                FakeRequest fakeRequest = fakeRequest().withFormUrlEncodedBody(formParams);

                Result result = callAction(controllers.routes.ref.Application.addUser(), fakeRequest);
                assertThat(result).isNotNull();
            }
        });
    }

    // Test to make sure a user can be added
    @Test
    public void callAddUser() {
        User user = new User();
        user.setdName("testing123");
        user.setPword("testing");
        user.setuName("testing123");
        userService.addUser(user);
        assertThat(user.getId()).isNotNull();
    }
}
