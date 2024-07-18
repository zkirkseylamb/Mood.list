package com.example.music.controllers;

import com.example.music.dao.UsersDao;
import com.example.music.models.Users;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User controller
 */
@RestController
@RequestMapping("/users")
public class UsersController {
    /**
     * User data access object
     */
    private final UsersDao usersDao;

    /**
     * Constructor
     *
     * @param usersDao user data access object
     */
    public UsersController(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    /**
     * Get all users
     *
     * @return list of users
     */
    @GetMapping("")
    public List<Users> getAllUsers() {
        return usersDao.getAllUsers();
    }

    /**
     * Get user by username
     *
     * @param username username
     * @return user
     */
    @GetMapping("/{username}")
    public Users getUser(@PathVariable String username) {
        return usersDao.getUser(username);
    }

    /**
     * Create user
     *
     * @param user user
     * @return created user
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Users createUser(@RequestBody Users user) {
        return usersDao.createUser(user);
    }

    /**
     * Update user
     *
     * @param username username
     * @param users     user
     * @return updated user
     */
    @PutMapping("/{username}")
    public Users updateUser(@PathVariable String username, @RequestBody Users users) {
        return usersDao.updateUser(users, false);
    }

    /**
     * Delete user
     *
     * @param username username
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable String username) {
        usersDao.deleteUser(username);
    }

    /**
     * Get user roles
     *
     * @param username username
     * @return list of roles
     */
    @GetMapping("/{username}/roles")
    public List<String> getUserRoles(@PathVariable String username) {
        return usersDao.getRolesForUser(username);
    }

    /**
     * Add user role
     *
     * @param username username
     * @param role     role
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{username}/roles")
    public void addUserRole(@PathVariable String username, @RequestBody String role) {
        usersDao.addRoleToUser(username, role);
    }

    /**
     * Remove user role
     *
     * @param username username
     * @param role     role
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{username}/roles/{role}")
    public void removeUserRole(@PathVariable String username, @PathVariable String role) {
        usersDao.removeRoleFromUser(username, role);
    }

}

