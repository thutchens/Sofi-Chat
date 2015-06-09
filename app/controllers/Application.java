package controllers;

import models.db.Message;
import models.db.UserLogin;
import models.db.UserRegister;

import models.MessageHTML;
import models.UserLoginHTML;
import models.UserRegisterHTML;

import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import play.data.Form;
import play.libs.Json;
import play.mvc.Result;
import play.api.data.validation.*;

import java.io.Console;
import java.util.Map;

import services.MessageService;
import services.UserLoginService;
import services.UserRegisterService;

import views.html.*;

@org.springframework.stereotype.Controller
public class Application extends play.mvc.Controller {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private UserRegisterService userRegisterService;

    @Autowired
    private MessageService messageService;

    // Loads the login page
    public Result index() {
        log.debug("index(): Accessed the login page");

        return ok(index.render(Form.form(UserLoginHTML.class),Form.form(UserRegisterHTML.class)));
    }

    // Loads the chatroom
    public Result chatroom() {
        //If user has not logged in and therefore there is no session variable
        if (session("name") == null) {
            log.debug("chatroom(): tried to access chatroom without login");
            return redirect(controllers.routes.Application.index());
        }

        log.info("chatroom(): Accessed the Chatroom page");
        return ok(room.render(Form.form(MessageHTML.class)));
    }

    public Result addUser() {
        Form<UserLoginHTML> form = Form.form(UserLoginHTML.class).bindFromRequest();
        Form<UserRegisterHTML> form2 = Form.form(UserRegisterHTML.class).bindFromRequest();

        //If user tries to add new user but doesnt fill every field
        if (form.hasErrors()) {
            log.debug("addUser(): Username, Displayname, or password was not entered");
            return badRequest(index.render(form, form2));
        }

        UserRegisterHTML user = form2.get();

        UserRegister reg = new UserRegister();
        reg.setdNameR(user.getdNameR());
        reg.setPwordR(user.getPwordR());
        reg.setuNameR(user.getuNameR());

        userRegisterService.addUser(reg);
        log.info("addUser(): Created new user");
        return redirect(controllers.routes.Application.index());
    }

    public Result login() {
        Form<UserLoginHTML> form = Form.form(UserLoginHTML.class).bindFromRequest();
        Form<UserRegisterHTML> form2 = Form.form(UserRegisterHTML.class).bindFromRequest();

        // If an entry is left blank
        if (form.hasErrors()) {
            log.debug("findUser(): Username or password was not entered");
            return badRequest(index.render(form, form2));
        }

        UserLoginHTML user = form.get();

        UserLogin login = new UserLogin();
        login.setuName(user.getuName());
        login.setPword(user.getPword());


        String displayName = userLoginService.getDisplayName(login);

        // If the user is not found go back to same page with error message
        if (displayName == null) {
            log.debug("findUser(): Invalid Username or password was entered");
            form.reject("invalidUser","Error: Invalid Username or Password");
            return badRequest(index.render(form, form2));
        }

        // Stores the display name
        session("name", displayName);
        log.info("findUser(): User is logged in and saved display name: {} to the session", displayName);
        return redirect(controllers.routes.Application.chatroom());
    }

    public Result addMessage() {
        Form<MessageHTML> form = Form.form(MessageHTML.class).bindFromRequest();

        //if user tries to submit message but nothing is entered
        if (form.hasErrors()) {
            log.debug("addMessage(): No message was entered");
            return badRequest(room.render(form));
        }

        MessageHTML msg = form.get();
        msg.setMsgFrom(session("name"));

        Message mess = new Message();
        mess.setMsg(msg.getMsg());
        mess.setMsgFrom(msg.getMsgFrom());

        messageService.addMessage(mess);
        log.debug("addMessage(): Added a new message to the database");
        return redirect(controllers.routes.Application.chatroom());
    }

    public Result getAllMessages() {
        return ok(Json.toJson(messageService.getMessages()));
    }
}
