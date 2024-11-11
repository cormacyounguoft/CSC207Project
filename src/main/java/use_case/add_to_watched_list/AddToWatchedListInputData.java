package use_case.add_to_watched_list;

import entity.Movie;

/**
 * The Input Data for the add to watched list Use Case.
 */
public class AddToWatchedListInputData {
    private final String username;
    private final Movie movie;

    public AddToWatchedListInputData(String username, Movie movie) {
        this.username = username;
        this.movie = movie;
    }

    public String getUsername() {
        return username;
    }

    public Movie getMovie() {
        return movie;
    }
}
