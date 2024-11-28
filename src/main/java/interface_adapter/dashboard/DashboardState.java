package interface_adapter.dashboard;

/**
 * The state class representing the data for the Dashboard View.
 */
public class DashboardState {
    private String username;
    private String favoriteMovie;
    private String totalHoursWatched;
    private String favoriteGenre;
    private double averageRating;
    private String longestMovie;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFavoriteMovie() {
        return favoriteMovie;
    }

    public void setFavoriteMovie(String favoriteMovie) {
        this.favoriteMovie = favoriteMovie;
    }

    public String getTotalHoursWatched() {
        return totalHoursWatched;
    }

    public void setTotalHoursWatched(String totalHoursWatched) {
        this.totalHoursWatched = totalHoursWatched;
    }

    public String getFavoriteGenre() {
        return favoriteGenre;
    }

    public void setFavoriteGenre(String favoriteGenre) {
        this.favoriteGenre = favoriteGenre;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public String getLongestMovie() {
        return longestMovie;
    }

    public void setLongestMovie(String longestMovie) {
        this.longestMovie = longestMovie;
    }
}
