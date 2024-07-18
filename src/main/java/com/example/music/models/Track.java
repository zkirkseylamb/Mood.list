package com.example.music.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a track of a song
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Track {
    /**
     * The serial id of the track (populated by postgres)
     */
    int track_id;
    /**
     * The name of the track
     */
    @NotBlank(message = "Track name is mandatory")
    String title;
    /**
     * The name of the artist
     */
    @NotBlank(message = "Artist name is mandatory")
    String artist;
    /**
     * A description of the song
     */
    String description;
    /**
     * The energy level associated with a mood (0-10) 0 being lowest and 10 being highest
     */
    @Min(value = 0)
    @Max(value = 10)
    int energy;
    /**
     * The valence (positive feelings) level associated with a mood (0-10) 0 being lowest and 10 being highest
     */
    @Min(value = 0)
    @Max(value = 10)
    int valence;
}
