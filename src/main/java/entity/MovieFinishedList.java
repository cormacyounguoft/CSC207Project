package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * The representation of a list of movies the user wants to watch.
 */

public class MovieFinishedList {
    private List<Movie> watchedList;


    public MovieFinishedList() {
        watchedList = new ArrayList<Movie>();
    }

    public List<Movie> getFinishedList() {
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