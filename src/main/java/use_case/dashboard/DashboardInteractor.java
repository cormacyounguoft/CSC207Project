package use_case.dashboard;

import entity.Movie;

/**
 * The interactor for the Dashboard Use Case, using the WatchedList as the data source.
 */
public class DashboardInteractor implements DashboardInputBoundary {
    private static final int MINUTES_IN_AN_HOUR = 60;
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

        final int hours = totalMinutes / MINUTES_IN_AN_HOUR;
        final int minutes = totalMinutes % MINUTES_IN_AN_HOUR;

        final String timeWatched;
        if (hours == 0) {
            if (minutes == 0) {
                timeWatched = "No time watched";
            }
            else {
                timeWatched = minutes + " minutes";
            }
        }
        else {
            timeWatched = hours + " hours and " + minutes + " minutes";
        }
        return timeWatched;
    }

    private String getFavoriteGenre(String username) {
        final String favoriteMovie = getFavoriteMovie(username);
        String result = "";

        for (Movie movie : dashboaedListDataAccess.getWatchedMovies(username).getMovieList()) {
            if (movie.getTitle().equals(favoriteMovie)) {
                result = movie.getGenre().toString();
            }
        }
        final String finalResult;
        if (!result.isEmpty()) {
            finalResult = result;
        }
        else {
            finalResult = "No favourite genre";
        }
        return finalResult;
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
        if (result.isEmpty()) {
            result = "No favourite movie";
        }
        return result;
    }

    private double getAverageRating(String username) {
        int totalNum = 0;
        int totalRating = 0;

        for (String key : dashboaedListDataAccess.getUserRatings(username).getMovieToRating().keySet()) {
            totalRating += dashboaedListDataAccess.getUserRatings(username).getMovieToRating().get(key);
            totalNum++;
        }
        final double result;
        if (totalNum == 0) {
            result = 0;
        }
        else {
            result = (double) totalRating / totalNum;
        }
        return result;
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
        final String finalResult;
        if (!result.isEmpty()) {
            finalResult = result;
        }
        else {
            finalResult = "No movies watched";
        }
        return finalResult;
    }
}
