package com.example.music.dao;


import com.example.music.models.Users;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * User data access object
 */
@Component
public class UsersDao {
    /**
     * JDBC template
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * Password encoder
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor
     *
     * @param dataSource data source
     * @param passwordEncoder password encoder
     */
    public UsersDao(DataSource dataSource, PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Get all users
     *
     * @return list of users
     */
    public List<Users> getAllUsers() {
        return jdbcTemplate.query(
                "SELECT * FROM users order by username",
                this::mapRowToUser
        );
    }

    /**
     * Get user by username
     *
     * @param username username
     * @return user
     */
    public Users getUser(String username) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM users WHERE username = ?",
                    this::mapRowToUser, username
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Create user
     *
     * @param user user
     * @return created user
     */
    public Users createUser(Users user) {
        try {
            jdbcTemplate.update(
                    "INSERT INTO users (username, password) VALUES (?, ?)",
                    user.getUsername(), passwordEncoder.encode(user.getPassword())
            );
            return getUser(user.getUsername());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Update user
     *
     * @param user user
     * @param updatePassword update password
     * @return updated user
     */
    public Users updateUser(Users user, boolean updatePassword) {
        if (updatePassword) {
            jdbcTemplate.update(
                    "UPDATE users SET password = ?, WHERE username = ?",
                    passwordEncoder.encode(user.getPassword()), user.getUsername()
            );
        } else {
            jdbcTemplate.update(
                    "UPDATE users SET username = ? WHERE username = ?",
                    user.getUsername()
            );
        }
        return getUser(user.getUsername());
    }

    /**
     * Delete user
     *
     * @param username username
     */
    public void deleteUser(String username) {
        jdbcTemplate.update(
                "DELETE FROM users WHERE username = ?",
                username
        );
    }

    /**
     * Get roles for user
     *
     * @param username Username
     * @return username
     */
    public List<String> getRolesForUser(String username) {
        return jdbcTemplate.queryForList(
                "SELECT role FROM roles WHERE username = ?",
                String.class, username
        );
    }

    /**
     * Add role to user
     *
     * @param username Username
     * @param role Role of user
     */
    public void addRoleToUser(String username, String role) {
        try {
            jdbcTemplate.update(
                    "INSERT INTO roles (username, role) VALUES (?, ?)",
                    username, role
            );
        } catch (Exception e) {
            // ignore
        }
    }

    /**
     * Remove role from user
     *
     * @param username Username
     * @param role Role of User
     */
    public void removeRoleFromUser(String username, String role) {
        jdbcTemplate.update(
                "DELETE FROM roles WHERE username = ? AND role = ?",
                username, role
        );
    }

    /**
     * Check username and password
     *
     * @param username Username
     * @param password Password of user
     * @return Matches password of user
     */
    public boolean checkUsernamePassword(String username, String password) {
        Users user = getUser(username);
        return passwordEncoder.matches(password, user.getPassword());
    }

    /**
     * Map row to user
     *
     * @param row Row
     * @param rowNum Row number
     * @return User
     * @throws SQLException Row exception
     */
    private Users mapRowToUser(ResultSet row, int rowNum) throws SQLException {
        Users user = new Users();
        user.setUsername(row.getString("username"));
        user.setPassword(row.getString("password"));
        return user;
    }

}
