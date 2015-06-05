package services;

import models.User;

public interface UserService {

    void addUser(User user);
    String findUser(User user);

}