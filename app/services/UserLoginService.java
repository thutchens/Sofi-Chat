package services;

import models.db.UserLogin;

public interface UserLoginService {

    String getDisplayName(UserLogin user);
}
