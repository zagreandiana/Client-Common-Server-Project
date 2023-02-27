package ro.ubb.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.common.model.User;
import ro.ubb.common.exceptions.ServiceException;
import ro.ubb.common.service.UserService;
import ro.ubb.common.utils.ExceptionMessages;
import ro.ubb.common.utils.UserRoles;
import ro.ubb.common.utils.UserStatuses;
import ro.ubb.server.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void create(User user) {
        log.debug("### Entering create user method.");
        userRepository.save(user);
        log.debug("### Exiting create user method.");
    }

    @Override
    public User readOne(Long id) {
        log.debug("### Entering read user method.");
        Optional<User> optional = userRepository.findById(id);

        if (optional.isEmpty()) {
            throw new ServiceException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST.message);
        }

        User user = optional.get();
        log.debug("### Exiting read user method.");

        return user;
    }

    @Override
    public List<User> readAll() {
        log.debug("### Entering read users method.");
        List<User> users = userRepository.findAll();
        log.debug("### Exiting read users method.");

        return users;
    }

    @Override
    @Transactional
    public void update(User user) {
        log.debug("### Entering update users method.");
        Optional<User> optional = userRepository.findById(user.getId());

        User userToBeUpdated = optional.orElseThrow(() -> new ServiceException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST.message));

        userToBeUpdated.setFirstName(user.getFirstName());
        userToBeUpdated.setLastName(user.getLastName());
        userToBeUpdated.setEmail(user.getEmail());
        userToBeUpdated.setPassword(user.getPassword());
        userToBeUpdated.setRole(user.getRole());
        userToBeUpdated.setStatus(user.getStatus());
        log.debug("### Exiting update users method.");
    }

    @Override
    public void delete(Long id) {
        log.debug("### Entering delete user method.");
        userRepository.deleteById(id);
        log.debug("### Exiting delete user method.");
    }

    @Override
    public List<User> readAll(UserStatuses status) {
        return userRepository.findAllByStatus(status);
    }

    @Override
    public List<User> readAll(UserRoles role) {
        return userRepository.findAllByRole(role);
    }
}
