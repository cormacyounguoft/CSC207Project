package use_case.dashboard;
import entity.Movie;

/**
 * The interactor for the Dashboard Use Case, using the WatchedList as the data source.
 */
public class DashboardInteractor implements DashboardInputBoundary {
    private final DashboardDataAccessInterface dashboaedListDataAccess;
    private final DashboardOutputBoundary dashboardPresenter;

    public DashboardInteractor(DashboardDataAccessInterface dashboaedListDataAccess,
                               DashboardOutputBoundary dashboardPresenter) {
        this.dashboaedListDataAccess = dashboaedListDataAccess;
        this.dashboardPresenter = dashboardPresenter;
    }

    @Override
    public void execute(DashboardInputData inputData) {
        final DashboardOutputData outputData = new DashboardOutputData(
                getTotalHoursWatched(inputData.getUsername()),
                getFavoriteMovie(inputData.getUsername()),
                getFavoriteGenre(inputData.getUsername()),
                getAverageRating(inputData.getUsername()),
                getLongestMovie(inputData.getUsername()),
                inputData.getUsername());
        dashboardPresenter.prepareSuccessView(outputData);
    }

    private String getTotalHoursWatched(String username) {
        int totalMinutes = 0;

        for (Movie movie : dashboaedListDataAccess.getWatchedMovies(username).getMovieList()) {
            totalMinutes += movie.getRuntime();
        }
        int hours = totalMinutes / 60;
        int minutes = totalMinutes % 60;

        if (hours == 0){
            if (minutes == 0){
                return "No time watched";
            }
            return minutes + " minutes";
        }
        else
            return hours + " hours and " + minutes + " minutes";
    }

    private String getFavoriteGenre(String username) {
        String favoriteMovie = getFavoriteMovie(username);
        String result = "";

        for (Movie movie : dashboaedListDataAccess.getWatchedMovies(username).getMovieList()) {
            if (movie.getTitle().equals(favoriteMovie)) {
                result = movie.getGenre().toString();
            }
        }
        if (!result.isEmpty()) {
            return result;
        } else {
            return "No favourite genre";
        }
    }

    private String getFavoriteMovie(String username) {
        String result = "";
        int highestRating = 0;

        for (String key : dashboaedListDataAccess.getUserRatings(username).getMovieToRating().keySet()) {
            if (dashboaedListDataAccess.getUserRatings(username).getMovieToRating().get(key) > highestRating) {
                result = key;
                highestRating = dashboaedListDataAccess.getUserRatings(username).getMovieToRating().get(key);
            }
        }
        if (!result.isEmpty()) {
            return result;
        } else {
            return "No favourite movie";
        }
    }

    private double getAverageRating(String username) {
        int totalNum = 0;
        int totalRating = 0;

        for (String key : dashboaedListDataAccess.getUserRatings(username).getMovieToRating().keySet()) {
            totalRating += dashboaedListDataAccess.getUserRatings(username).getMovieToRating().get(key);
            totalNum ++;
            }
        if (totalNum == 0) {
            return 0;
        }
        else {
            return (double) totalRating / totalNum;
        }
    }

    private String getLongestMovie(String username) {
        int longestMovie = 0;
        String result = "";

        for (Movie movie : dashboaedListDataAccess.getWatchedMovies(username).getMovieList()) {
            if (movie.getRuntime() > longestMovie) {
                longestMovie = movie.getRuntime();
                result = movie.getTitle();
            }
        }
        if (!result.isEmpty()) {
            return result;
        } else {
            return "No movies watched";
        }
    }
}
