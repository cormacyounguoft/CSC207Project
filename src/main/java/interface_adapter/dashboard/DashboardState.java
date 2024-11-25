package interface_adapter.dashboard;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

/**
 * The state class representing the data for the Dashboard View.
 */
public class DashboardState {
    private String username;
    private double totalHoursWatched;
    private Map<String, Integer> favoriteGenres;
    private double averageRating;
    private Map<String, Double> highestRatedGenres;
    private List<String> longestMovies;

    public DashboardState() {
        this.username = "";
        this.totalHoursWatched = 0.0;
        this.favoriteGenres = new HashMap<>(); // Initialize to avoid null
        this.averageRating = 0.0;
        this.highestRatedGenres = new HashMap<>(); // Initialize to avoid null
        this.longestMovies = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getTotalHoursWatched() {
        return totalHoursWatched;
    }

    public void setTotalHoursWatched(double totalHoursWatched) {
        this.totalHoursWatched = totalHoursWatched;
    }

    public Map<String, Integer> getFavoriteGenres() {
        return favoriteGenres;
    }

    public void setFavoriteGenres(Map<String, Integer> favoriteGenres) {
        this.favoriteGenres = favoriteGenres;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public Map<String, Double> getHighestRatedGenres() {
        return highestRatedGenres;
    }

    public void setHighestRatedGenres(Map<String, Double> highestRatedGenres) {
        this.highestRatedGenres = highestRatedGenres;
    }

    public List<String> getLongestMovies() {
        return longestMovies;
    }

    public void setLongestMovies(List<String> longestMovies) {
        this.longestMovies = longestMovies;
    }
}
