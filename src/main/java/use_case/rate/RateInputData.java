package use_case.rate;

import entity.Movie;

public class RateInputData {

    private final String username;
    private final String movie;
    private final int rating;

    public RateInputData(String username, String movie, int rating) {
        this.username = username;
        this.movie = movie;
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public String getMovie() {
        return movie;
    }

    public int getRating() {
        return rating;
    }
}
