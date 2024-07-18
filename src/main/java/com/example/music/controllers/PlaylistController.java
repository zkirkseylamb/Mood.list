package com.example.music.controllers;

import com.example.music.dao.PlaylistDao;
import com.example.music.models.Playlist;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles REST requests to /playlist/*
 */
@RestController
@RequestMapping("/playlist")
public class PlaylistController {
    /**
     * Playlist data access object
     */
    private final PlaylistDao playlistDao;

    /**
     * Creates a new PlaylistController
     *
     * @param playlistDao The playlist data access object
     */
    public PlaylistController(PlaylistDao playlistDao){
        this.playlistDao = playlistDao;
    }

    /**
     * Returns a list of all playlists
     *
     * @return The list of playlists
     */
    @GetMapping("")
    public List<Playlist> listPlaylist() {
        return playlistDao.getPlaylistList();
    }

    /**
     * Returns a playlist by their id
     *
     * @param id The id of the playlist
     * @return The playlist
     */
    @GetMapping("/{id}")
    public Playlist getPlaylist(@PathVariable int id) {
        return playlistDao.getPlaylistById(id);
    }

    /**
     * Adds a new playlist
     *
     * @param playlist the playlist to add
     * @return The added playlist
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Playlist addPlaylist(@Valid @RequestBody Playlist playlist) {
        return playlistDao.createPlaylist(playlist);
    }

    /**
     * Updates a playlist
     *
     * @param id The id of the playlist
     * @return The updated playlist
     */
    @PutMapping("/{id}")
    public Playlist updatePlaylistTrack(@PathVariable int id, @Valid @RequestBody Playlist playlist) {
        playlist.setPlaylist_id(id);
        return playlistDao.updatePlaylist(playlist);
    }

    /**
     * Deletes a playlist
     *
     * @param id The id of the playlist
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePlaylistTrack(@PathVariable int id) {
        playlistDao.deletePlaylistById(id);
    }

}