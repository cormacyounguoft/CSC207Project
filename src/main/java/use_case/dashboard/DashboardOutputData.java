package use_case.dashboard;

/**
 * The output data for the dashboard.
 */
public class DashboardOutputData {
    private final String totalHoursWatched;
    private final String favoriteMovie;
    private final String favoriteGenre;
    private final double averageRating;
    private final String longestMovie;
    private final String username;

    public DashboardOutputData(String totalHoursWatched, String favoriteMovie, String favoriteGenre,
                               double averageRating, String longestMovie, String username) {
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

    public String getTotalHoursWatched() {
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
