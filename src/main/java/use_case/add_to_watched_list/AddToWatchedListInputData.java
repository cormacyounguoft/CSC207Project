package use_case.add_to_watched_list;

import entity.Movie;

/**
 * The Input Data for the add to watched list Use Case.
 */
public class AddToWatchedListInputData {
    private final String username;
    private final String movie;

    public AddToWatchedListInputData(String username, String movie) {
        this.username = username;
        this.movie = movie;
    }

    public String getUsername() {
        return username;
    }

    public String getMovie() {
        return movie;
    }
}
