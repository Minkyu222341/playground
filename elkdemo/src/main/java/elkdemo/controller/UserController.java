package elkdemo.controller;

import elkdemo.entity.User;
import elkdemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        logger.info("REST request to get all users");
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        logger.info("REST request to get user with id: {}", id);
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    logger.error("User with id {} not found", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        logger.info("REST request to create user: {}", user);
        try {
            User createdUser = userService.createUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating user: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        logger.info("REST request to update user with id: {}", id);
        return userService.updateUser(id, user)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    logger.error("User with id {} not found for update", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        logger.info("REST request to delete user with id: {}", id);
        if (userService.deleteUser(id)) {
            return ResponseEntity.noContent().build();
        } else {
            logger.error("User with id {} not found for deletion", id);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable String role) {
        logger.info("REST request to get users with role: {}", role);
        List<User> users = userService.findUsersByRole(role);
        return ResponseEntity.ok(users);
    }
}