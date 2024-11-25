package use_case.dashboard;
import java.util.List;
import java.util.Map;


public class DashboardOutputData {
    private final double totalHoursWatched;
    private final Map<String, Integer> favoriteGenres;
    private final double averageRating;
    private final Map<String, Double> highestRatedGenres;
    private final List<String> longestMovies;
    private final String username;

    public DashboardOutputData(double totalHoursWatched,
                               Map<String, Integer> favoriteGenres,
                               double averageRating,
                               Map<String, Double> highestRatedGenres,
                               List<String> longestMovies,
                               String username) {
        this.totalHoursWatched = totalHoursWatched;
        this.favoriteGenres = favoriteGenres;
        this.averageRating = averageRating;
        this.highestRatedGenres = highestRatedGenres;
        this.longestMovies = longestMovies;
        this.username = username;
    }
    public double getTotalHoursWatched() {
        return totalHoursWatched;
    }
    public Map<String, Integer> getFavoriteGenres() {
        return favoriteGenres;
    }
    public double getAverageRating() {
        return averageRating;
    }
    public Map<String, Double> getHighestRatedGenres() {
        return highestRatedGenres;
    }
    public List<String> getLongestMovies() {
        return longestMovies;
    }
    public String getUsername() {
        return username;
    }
}
