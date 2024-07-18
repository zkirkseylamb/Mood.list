package com.example.music.dao;

import com.example.music.models.Playlist;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Data access object for the playlist table
 */
@Component
public class PlaylistDao {
    /**
     * JdbcTemplate instance
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * Creates a new PlaylistDao
     *
     * @param  dataSource The datasource connect
     */
    public PlaylistDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Returns a list of all playlists
     *
     * @return  The list of playlists
     */
    public List<Playlist> getPlaylistList() {
        return jdbcTemplate.query( "SELECT * FROM playlist",
                this::mapPlaylist
        );
    }

    /**
     *
     * @param id The id of the playlist
     * @return The playlist
     */
    public Playlist getPlaylistById(int id) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM playlist WHERE playlist_id = ?",
                    this::mapPlaylist,
                    id
            );
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     *
     * @param playlist The playlist to create
     * @return The created playlist
     */
    public Playlist createPlaylist(Playlist playlist) {
        Integer id = jdbcTemplate.queryForObject(
                "INSERT INTO playlist (track_id,mood_id,mood) VALUES (?,?,?) RETURNING id",
                Integer.class,
                playlist.getTrack_id(),
                playlist.getMood_id(),
                playlist.getMood()
        );
        if (id != null) {
            return getPlaylistById(id);
        }else {
            return null;
        }
    }

    /**
     *
     * @param  playlist The Playlist to update
     * @return The updated playlist
     */
    public Playlist updatePlaylist(Playlist playlist) {
        int affectedRows = jdbcTemplate.update(
                "UPDATE playlist SET track_id = ?, mood_id = ?, mood = ? WHERE id = ?",
                playlist.getTrack_id(),
                playlist.getMood_id(),
                playlist.getMood()
        );
        if (affectedRows > 0) {
            return  getPlaylistById(playlist.getPlaylist_id());
        }else {
            return null;
        }
    }

    /**
     *
     * @param id The playlist
     * @return The number of affected rows
     */
    public int deletePlaylistById(int id) {
        return jdbcTemplate.update(
                "DELETE FROM playlist WHERE id = ?",
                id
        );
    }

    /**
     *
     * @param row The result set
     * @param rowNumber The row number
     * @return The playlist
     * @throws SQLException If an error occurs
     */
    private Playlist mapPlaylist(ResultSet row, int rowNumber) throws SQLException {
        Playlist playlist = new Playlist();
        playlist.setPlaylist_id(row.getInt("playlist_id"));
        playlist.setTrack_id(row.getInt("track_id"));
        playlist.setMood_id(row.getInt("mood_id"));
        playlist.setMood(row.getString("mood"));
        return playlist;
    }

}
