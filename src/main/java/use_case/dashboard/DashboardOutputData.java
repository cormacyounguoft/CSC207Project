package use_case.dashboard;
import java.util.List;
import java.util.Map;


public class DashboardOutputData {
    private final int totalHoursWatched;
    private final String favoriteMovie;
    private final String favoriteGenre;
    private final double averageRating;
    private final String longestMovie;
    private final String username;

    public DashboardOutputData(int totalHoursWatched, String favoriteMovie, String favoriteGenre, double averageRating, String longestMovie, String username) {
        this.totalHoursWatched = totalHoursWatched;
        this.favoriteMovie = favoriteMovie;
        this.favoriteGenre = favoriteGenre;
        this.averageRating = averageRating;
        this.longestMovie = longestMovie;
        this.username = username;
    }

    public String getFavoriteMovie() {
        return favoriteMovie;
    }

    public int getTotalHoursWatched() {
        return totalHoursWatched;
    }

    public String getFavoriteGenre() {
        return favoriteGenre;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public String getLongestMovie() {
        return longestMovie;
    }

    public String getUsername() {
        return username;
    }
}
