package elkdemo.service;

import elkdemo.entity.User;
import elkdemo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        logger.info("Fetching all users");
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        logger.info("Fetching user with id: {}", id);
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            logger.warn("User with id {} not found", id);
        }
        return user;
    }

    public User createUser(User user) {
        logger.info("Creating new user: {}", user.getUsername());
        User savedUser = userRepository.save(user);
        logger.info("User created successfully with id: {}", savedUser.getId());
        return savedUser;
    }

    public Optional<User> updateUser(Long id, User userDetails) {
        logger.info("Updating user with id: {}", id);
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setUsername(userDetails.getUsername());
                    existingUser.setEmail(userDetails.getEmail());
                    existingUser.setRole(userDetails.getRole());
                    User updatedUser = userRepository.save(existingUser);
                    logger.info("User updated successfully: {}", updatedUser);
                    return updatedUser;
                });
    }

    public boolean deleteUser(Long id) {
        logger.info("Deleting user with id: {}", id);
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            logger.info("User deleted successfully");
            return true;
        }
        logger.warn("Cannot delete user. User with id {} not found", id);
        return false;
    }

    public List<User> findUsersByRole(String role) {
        logger.info("Finding users with role: {}", role);
        List<User> users = userRepository.findByRole(role);
        logger.info("Found {} users with role: {}", users.size(), role);
        return users;
    }
}