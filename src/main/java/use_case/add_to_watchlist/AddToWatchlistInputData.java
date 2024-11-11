package use_case.add_to_watchlist;

import entity.Movie;

/**
 * The Input Data for the add to watchlist Use Case.
 */
public class AddToWatchlistInputData {
    private final String username;
    private final Movie movie;

    public AddToWatchlistInputData(String username, Movie movie) {
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
