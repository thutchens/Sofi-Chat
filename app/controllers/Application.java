package controllers;

import models.User;
import models.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import play.data.Form;
import play.libs.Json;
import play.mvc.Result;

import java.io.Console;
import java.util.Map;

import services.MessageService;
import services.UserService;

import views.html.*;

@org.springframework.stereotype.Controller
public class Application extends play.mvc.Controller {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    private boolean invalidUser = false;

    // Loads the login page
    public Result index() {
        log.info("index(): Accessed the login page");

        if (!invalidUser) {
            return ok(index.render("", Form.form(User.class)));
        }

        invalidUser = false;
        return ok(index.render("Error: Invalid Username or Password", Form.form(User.class)));
    }

    // Loads the chatroom
    public Result chatroom() {
        //If user has not logged in and therefore there is no session variable
        if (session("name") == null) {
            log.debug("chatroom(): tried to access chatroom without login");
            return redirect(controllers.routes.Application.index());
        }

        log.info("chatroom(): Accessed the Chatroom page");
        return ok(room.render(Form.form(Message.class)));
    }

    public Result addUser() {
        Form<User> form = Form.form(User.class).bindFromRequest();

        //If user tries to add new user but doesnt fill every field
        if (form.hasErrors()) {
            log.error("addUser(): Username, Displayname, or password was not entered");
            return badRequest(index.render("", form));
        }

        User user = form.get();
        userService.addUser(user);
        log.info("addUser(): Created new user");
        return redirect(controllers.routes.Application.index());
    }

    public Result findUser() {
        Form<User> form = Form.form(User.class).bindFromRequest();

        // If an entry is left blank
        if (form.data().get("uName").isEmpty() || form.data().get("pword").isEmpty()) {
            form.hasErrors();
            log.debug("findUser(): Username or password was not entered");

            //form.reject("invalid username or password");
            return badRequest(index.render("", form));
        }

        User user = new User();
        user.setuName(form.data().get("uName"));
        user.setPword(form.data().get("pword"));
        String displayName = userService.findUser(user);

        // If the user is not found go back to same page with error message
        if (displayName == null) {
            log.debug("findUser(): Invalid Username or password was entered");
            form.reject("");
            invalidUser = true;
            return redirect(controllers.routes.Application.index());
        }

        // Stores the display name
        session("name", displayName);
        log.info("findUser(): User is logged in and saved display name: {} to the session", displayName);
        return redirect(controllers.routes.Application.chatroom());
    }

    public Result addMessage() {
        Form<Message> form = Form.form(Message.class).bindFromRequest();

        //if user tries to submit message but nothing is entered
        if (form.hasErrors()) {
            log.error("addMessage(): No message was entered");
            return badRequest(room.render(form));
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
