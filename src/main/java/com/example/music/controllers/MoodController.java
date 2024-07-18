package com.example.music.controllers;


import com.example.music.dao.MoodDao;
import com.example.music.models.Mood;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Handles REST requests to /mood/*
 */
@RestController
@RequestMapping("/mood")
public class MoodController {
    /**
     * mood data access object
     */
    private final MoodDao moodDao;

    /**
     * Creates a new moodController
     *
     * @param moodDao The mood data access object
     */
    public MoodController (MoodDao moodDao){
        this.moodDao = moodDao;
    }
    /**
     * Returns a list of all moods
     *
     * @return The list of moods
     */
    @GetMapping("")
    public List<Mood> moodList() {
        return moodDao.getMoodList();
    }

    /**
     * Returns a mood by their id
     *
     * @param id The id of the student
     * @return The mood
     */
    @GetMapping("/{id}")
    public Mood getMoodById(@PathVariable int id){
        return moodDao.getMoodById(id);
    }

    /**
     * Adds a new student
     *
     * @param mood The mood to add
     * @return The added mood
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Mood addMood(@Valid @RequestBody Mood mood){
        return moodDao.createMood(mood);
    }

    /**
     * Updates a mood
     *
     * @param id The id of the mood
     * @param mood the mood to update
     * @return The updated mood
     */
    @PutMapping("/{id}")
    public Mood updateMood(@PathVariable int id,@Valid @RequestBody Mood mood) {
        mood.setMood_id(id);
        return moodDao.updateMood(mood);
    }

    /**
     * Deletes a mood
     *
     * @param id The id of the mood
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteMood(@PathVariable int id) {
        moodDao.deleteMoodById(id);
    }
}

