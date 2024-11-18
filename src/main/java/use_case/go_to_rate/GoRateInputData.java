package use_case.go_to_rate;

import entity.Movie;

public class GoRateInputData {
    private final String username;
    private final Movie movie;
    public GoRateInputData(String username, Movie movie) {
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
