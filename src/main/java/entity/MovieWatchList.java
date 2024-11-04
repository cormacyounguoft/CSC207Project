package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * The representation of a list of movies the user wants to watch.
 */

public class MovieWatchList {
    private List<Movie> watchList;


    public MovieWatchList() {
        watchList = new ArrayList<Movie>();
    }

    public List<Movie> getWatchList() {
        return watchList;
    }

    public void addWatch(Movie movie) {
        if (!watchList.contains(movie)) {
            watchList.add(movie);
        }
    }

    public void removeWatch(Movie movie) {
        watchList.remove(movie);
    }
}
