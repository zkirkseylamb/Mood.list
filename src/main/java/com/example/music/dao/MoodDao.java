package com.example.music.dao;

import com.example.music.models.Mood;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Data access object for the mood table
 */
@Component
public class MoodDao {
    /**
     * JdbcTemplate instance
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * Creates a new MoodDao
     *
     * @param dataSource The datasource to connect to
     */
    public MoodDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     *
     * @return the list of moods
     */
    public List<Mood> getMoodList() {
       return jdbcTemplate.query( "SELECT * FROM mood",
               this::mapRowtoMood
               );
    }

    /**
     *
     * @param id The id of the mood
     * @return The mood
     */
    public Mood getMoodById(int id) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM mood WHERE mood_id = ?",
                    this::mapRowtoMood,
                    id
            );
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     *
     * @param mood The mood to create
     * @return The created mood
     */
    public Mood createMood(Mood mood) {
       Integer id = jdbcTemplate.queryForObject(
               "INSERT INTO mood (mood,energy,valence) VALUES (?,?,?) RETURNING id",
               Integer.class,
               mood.getMood(),
               mood.getEnergy(),
               mood.getValence()
       );
       if (id != null) {
           return getMoodById(id);
       }else {
           return null;
       }
    }

    /**
     *
     * @param mood The mood to update
     * @return The updated mood
     */
    public Mood updateMood(Mood mood) {
      int affectedRows = jdbcTemplate.update(
              "UPDATE mood SET mood = ?, energy = ?, valence = ? WHERE id = ?",
              mood.getMood(),
              mood.getEnergy(),
              mood.getValence()
      );
      if (affectedRows > 0) {
          return  getMoodById(mood.getMood_id());
      }else {
          return null;
      }
    }

    /**
     *
     * @param id The mood id
     * @return The number of affected rows
     */
    public int deleteMoodById(int id) {
        return jdbcTemplate.update(
                "DELETE FROM mood WHERE id = ?",
                id
        );
    }

    /**
     *
     * @param row The result set
     * @param rowNumber The row number
     * @return The mood
     * @throws SQLException If an error occurs
     */
    private Mood mapRowtoMood(ResultSet row, int rowNumber) throws SQLException {
        Mood mood = new Mood();
        mood.setMood_id(row.getInt("mood_id"));
        mood.setMood(row.getString("mood"));
        mood.setEnergy(row.getInt("energy"));
        mood.setValence(row.getInt("valence"));
        return mood;
    }

}
