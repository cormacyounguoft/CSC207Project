package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * The representation of a list of movies the user has watched.
 */

public class MovieWatchedList {
    private List<Movie> watchedList;


    public MovieWatchedList() {
        watchedList = new ArrayList<Movie>();
    }

    public List<Movie> getWatchedList() {
        return watchedList;
    }

    public void addMovie(Movie movie) {
        if (!watchedList.contains(movie)) {
            watchedList.add(movie);
        }
    }

    public void removeMovie(Movie movie) {
        watchedList.remove(movie);
    }
}