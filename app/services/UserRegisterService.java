package services;

import models.db.UserRegister;

public interface UserRegisterService {

    void addUser(UserRegister user);

    boolean userNameExists(String userName);

    boolean displayNameExists(String displayName);

}
