import static play.mvc.Http.Status.SEE_OTHER;
import static play.test.Helpers.callAction;
import static play.test.Helpers.status;

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
import java.util.Map;


public class UserTest {

    //Tests that a user can be found
    @Test
    public void userRoute() {
        running(fakeApplication(), new Runnable() {
            @Override
            public void run() {
                Result result = route(fakeRequest(POST, "/users"));
                assertThat(result).isNotNull();
            }
        });
    }

    //Tests that chat messages can be added
    @Test
    public void callAddUser() {
        running(fakeApplication(), new Runnable() {
            @Override
            public void run() {
                Map<String, String> formParams = new HashMap<String, String>();
                formParams.put("name", "foo");

                FakeRequest fakeRequest = fakeRequest().withFormUrlEncodedBody(formParams);

                Result result = callAction(controllers.routes.ref.Application.addUser(), fakeRequest);
                assertThat(status(result)).isEqualTo(SEE_OTHER);
            }
        });
    }

}
