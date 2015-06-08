package controllers;

import models.User;
import models.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import play.data.Form;
import play.libs.Json;
import play.mvc.Result;

import java.util.Map;

import services.MessageService;
import services.UserService;

import views.html.*;

@org.springframework.stereotype.Controller
public class Application extends play.mvc.Controller{

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    // Loads the login page
    public Result index() {
        log.info("index(): Accessed the login page");
        return ok(index.render("Sofi Chat Room", Form.form(User.class)));
    }

    // Loads the chatroom
    public Result chatroom() {

        if (session("name") == null) {
            log.error("chatroom(): tried to access chatroom without login");
            return redirect(controllers.routes.Application.index());
        }

        log.info("chatroom(): Accessed the Chatroom page");
        return ok(room.render("Sofi Chat Room", Form.form(Message.class)));
    }

    public Result addUser() {
        Form<User> form = Form.form(User.class).bindFromRequest();

        if (form.hasErrors()) {
            log.error("addUser(): Username or password was not entered");
            return badRequest(index.render("Sofi Chat Room", form));
        }

        User user = form.get();
        userService.addUser(user);
        log.info("addUser(): Created new user");
        return redirect(controllers.routes.Application.index());
    }

    public Result findUser() {
        Form<User> form = Form.form(User.class).bindFromRequest();

        // If an entry is left blank
        if (form.hasErrors()) {
            log.error("findUser(): Username or password was not entered");
            return badRequest(index.render("Sofi Chat Room", form));
        }

        User user = form.get();
        String displayName = userService.findUser(user);

        // If the user is not found
        if (displayName == null) {
            log.error("findUser(): Invalid Username or password was entered");
            return badRequest(index.render("Error: Invalid Username or Password", form));
        }

        // Stores the display name
        session("name", displayName);
        log.info("findUser(): Saved display name: {} to the session", displayName);
        return redirect(controllers.routes.Application.chatroom());
    }

    public Result addMessage() {
        Form<Message> form = Form.form(Message.class).bindFromRequest();

        if (form.hasErrors()) {
            log.error("addMessage(): No message was entered");
            return badRequest(room.render("Sofi Chat Room", form));
        }

        Message msg = form.get();
        msg.setMsgFrom(session("name"));
        log.trace("addMessage(): Set the display name for messages to session name");
        messageService.addMessage(msg);
        log.debug("addMessage(): Added a new message to the database");
        return redirect(controllers.routes.Application.chatroom());
    }

    public Result getAllMessages() {
        return ok(Json.toJson(messageService.getMessages()));
    }
}
