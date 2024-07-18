package com.example.music.dao;

import com.example.music.models.Track;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Data access object for the track table
 */
@Component
public class TrackDao {
    /**
     * JdbcTemplate instance
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * Creates a new TrackDao
     *
     * @param dataSource The datasource to connect to
     */
    public TrackDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     *
     * @return the list of tracks
     */
    public List<Track> getTrackList() {
        return jdbcTemplate.query( "SELECT * FROM track",
                this::mapRowtoTrack
        );
    }

    /**
     *
     * @param id The id of the track
     * @return The track
     */
    public Track getTrackBy(int id) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM track WHERE track_id = ?",
                    this::mapRowtoTrack,
                    id
            );
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     *
     * @param track The track to create
     * @return The created track
     */
    public Track createTrack(Track track) {
        Integer id = jdbcTemplate.queryForObject(
                "INSERT INTO track (title,description,artist,energy,valence) VALUES (?,?,?,?,?) RETURNING id",
                Integer.class,
                track.getTitle(),
                track.getDescription(),
                track.getArtist(),
                track.getEnergy(),
                track.getValence()
        );
        if (id != null) {
            return getTrackBy(id);
        }else {
            return null;
        }
    }

    /**
     *
     * @param id The title id
     * @return The number of affected rows
     */
    public int deleteTitle(int id) {
        return jdbcTemplate.update(
                "DELETE FROM title WHERE id = ?",
                id
        );
    }

    /**
     *
     * @param track The track to update
     * @return The updated track
     */
    public Track updateTrack(Track track) {
        int affectedRows = jdbcTemplate.update(
                "UPDATE track SET title = ?, description = ?, artist = ?, energy = ?, valence = ? WHERE id = ?",
                track.getTitle(),
                track.getDescription(),
                track.getArtist(),
                track.getEnergy(),
                track.getValence()
        );
        if (affectedRows > 0) {
            return  getTrackBy(track.getTrack_id());
        }else {
            return null;
        }
    }

    /**
     *
     * @param row The result set
     * @param rowNumber The row number
     * @return The track
     * @throws SQLException If an error occurs
     */
    private Track mapRowtoTrack(ResultSet row, int rowNumber) throws SQLException {
        Track track = new Track();
        track.setTrack_id(row.getInt("track_id"));
        track.setTitle(row.getString("title"));
        track.setDescription(row.getString("description"));
        track.setArtist(row.getString("artist"));
        track.setEnergy(row.getInt("energy"));
        track.setValence(row.getInt("valence"));
        return track;
    }

}
