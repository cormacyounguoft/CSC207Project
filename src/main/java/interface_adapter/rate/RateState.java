package interface_adapter.rate;

import entity.Movie;

public class RateState {
    private String username = "";
    private String movie;
    private int rate;
    private String rateError;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getRateError() {
        return rateError;
    }

    public void setRateError(String rateError) {
        this.rateError = rateError;
    }
}
