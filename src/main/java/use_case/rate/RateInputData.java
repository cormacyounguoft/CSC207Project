package use_case.rate;

import entity.Movie;

public class RateInputData {

    private final String username;
    private final Movie movie;
    private final int rating;

    public RateInputData(String username, Movie movie, int rating) {
        this.username = username;
        this.movie = movie;
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getRating() {
        return rating;
    }
}
