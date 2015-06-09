package services;

import static org.fest.assertions.Assertions.assertThat;

import configs.AppConfig;

import controllers.TestDataConfig;

import models.UserRegisterHTML;
import models.UserLoginHTML;
import models.db.UserRegister;
import models.db.UserLogin;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(classes = { AppConfig.class, TestDataConfig.class })
public class UserTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private UserRegisterService userRegService;

    @Autowired
    private UserLoginService userLogService;

    // Test to make sure a user can be added
    @Test
    public void callFindUser() {
        UserLoginHTML user = new UserLoginHTML();
        user.setPword("testing");
        user.setuName("testing123");

        UserLogin usr = new UserLogin();
        usr.setuName(user.getuName());
        usr.setPword(user.getPword());

        userLogService.getDisplayName(usr);
        assertThat(usr.getId()).isNotNull();
    }

    // Test to make sure a user can be added
    @Test
    public void callAddUser() {
        UserRegisterHTML user = new UserRegisterHTML();
        user.setdNameR("testing123");
        user.setPwordR("testing");
        user.setuNameR("testing123");

        UserRegister usr = new UserRegister();
        usr.setdNameR(user.getdNameR());
        usr.setuNameR(user.getuNameR());
        usr.setPwordR(user.getPwordR());

        userRegService.addUser(usr);
        assertThat(usr.getId()).isNotNull();
    }
}
