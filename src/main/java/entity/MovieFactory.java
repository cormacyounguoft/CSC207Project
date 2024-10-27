package entity;

import java.util.List;

/**
 * Factory for creating Movie objects.
 */
public class MovieFactory {
    /**
     * Creates a movie object.
     * @param actors Actors.
     * @param description Description.
     * @param director Director.
     * @param genre Genre.
     * @param releaseDate Date.
     * @param title Title.
     * @param rottenTomatoes Rotten Tomatoes.
     * @return The movie object
     */
    public Movie create(String title, String releaseDate, String description, int rottenTomatoes,
                        List<String> genre, List<String> actors, List<String> director) {
        return new Movie(title, releaseDate, description, rottenTomatoes, genre, actors, director);
    }
}
