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

    public void addRating(Movie movie, int rating) {
        this.movieToRating.put(movie.getTitle(), rating);
    }

    public void removeRating(Movie movie) {
        this.movieToRating.remove(movie.getTitle());
    }

    public int getRating(Movie movie) {
        return this.movieToRating.getOrDefault(movie.getTitle(), -1);
    }

    public int getRatingCount() {
        return this.movieToRating.size();
    }
}
