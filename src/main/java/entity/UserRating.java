package entity;

import java.util.HashMap;
import java.util.Map;

/**
 * The representation of all of a user's ratings in our program.
 */
public class UserRating {

    private final Map<String, Integer> movieToRating;

    public UserRating() {
        this.movieToRating = new HashMap<>();
    }

    /**
     * Add a rating to the User profile.
     * @param movie The movie rated.
     * @param rating The rating given.
     */
    public void addRating(Movie movie, int rating) {
        this.movieToRating.put(movie.getTitle(), rating);
    }

    /**
     * Remove a rating from the User profile.
     * @param movie The movie rated.
     */
    public void remove(String movie) {
        this.movieToRating.remove(movie);
    }

    /**
     * Get the movie rating.
     * @return the rating of the movie.
     */
    public Map<String, Integer> getMovieToRating() {
        return this.movieToRating;
    }
}
