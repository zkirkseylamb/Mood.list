package com.example.music.models;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a user of the mood app
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    /**
     * The name of the user
     */
    @Min(value = 4, message = "Username must be at least 4 characters")
    String username;
    /**
     * The users password if applicable
     */
    @Min(value = 6, message = "Username must be at least 6 characters")
    String password;
}
