package com.example.music.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a mood for a playlist and song
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mood {
    /**
     * The serial id of the mood (populated by postgres)
     */
    private int mood_id;
    /**
     * The name of the mood
     */
    @NotBlank (message = "Mood is mandatory")
    private String mood;
    /**
     * The energy level associated with a mood (0-10) 0 being lowest and 10 being highest
     */
    @Min(value = 0)
    @Max(value = 10)
    private int energy;
    /**
     * The valence (positive feelings) level associated with a mood (0-10) 0 being lowest and 10 being highest
     */
    @Min(value = 0)
    @Max(value = 10)
    private int valence;


}
