import static play.test.Helpers.GET;
import static play.test.Helpers.route;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.SEE_OTHER;
import static play.test.Helpers.callAction;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.running;
import static play.test.Helpers.status;
import org.junit.Test;

import play.mvc.Result;
import play.test.FakeRequest;

import java.util.HashMap;
import java.util.Map;


public class MessageTest {

    //Tests that chat messages can be added
    @Test
    public void callAddMessage() {
        running(fakeApplication(), new Runnable() {
            @Override
            public void run() {
                Map<String, String> formParams = new HashMap<String, String>();
                formParams.put("name", "foo");

                FakeRequest fakeRequest = fakeRequest().withFormUrlEncodedBody(formParams);

                Result result = callAction(controllers.routes.ref.Application.addMessage(), fakeRequest);
                assertThat(status(result)).isEqualTo(SEE_OTHER);
            }
        });
    }

    //Tests that chat messages can be found
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
