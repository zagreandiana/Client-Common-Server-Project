package ro.ubb.server.repository;

import ro.ubb.common.model.User;
import ro.ubb.common.utils.UserRoles;
import ro.ubb.common.utils.UserStatuses;

import java.util.List;

public interface UserRepository extends Repository<User, Long> {

    List<User> findAllByRole(UserRoles role);
    List<User> findAllByStatus(UserStatuses status);
}
