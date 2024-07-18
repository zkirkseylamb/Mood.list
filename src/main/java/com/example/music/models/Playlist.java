package com.example.music.models;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a playlist
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Playlist {
    /**
     * The serial id of the playlist (populated by postgres)
     */
    int playlist_id;
    /**
     * The serial id of the track (populated by postgres)
     * (referenced by the playlist table)
     */
    int track_id;
    /**
     * The serial id of the mood (populated by postgres)
     * (referenced by the playlist table)
     */
    int mood_id;
    /**
     * The name of the mood (referenced by the playlist table)
     */
    @NotBlank(message = "Mood is mandatory")
    String mood;
}
