package ro.ubb.common.service;

import ro.ubb.common.model.User;
import ro.ubb.common.utils.UserRoles;
import ro.ubb.common.utils.UserStatuses;

import java.util.List;

public interface UserService {

    void create(User user);
    User readOne(Long id);
    List<User> readAll();
    List<User> readAll(UserStatuses status);
    List<User> readAll(UserRoles role);
    void update(User user);
    void delete(Long id);
}
