package use_case.dashboard;

import entity.Movie;
import entity.MovieList;
import use_case.get_watched_list.GetWatchedListDataAccessInterface;
import use_case.get_watched_list.GetWatchedListInputData;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The interactor for the Dashboard Use Case, using the WatchedList as the data source.
 */
public class DashboardInteractor implements DashboardInputBoundary {
    private final GetWatchedListDataAccessInterface watchedListDataAccess;
    private final DashboardOutputBoundary dashboardPresenter;

    public DashboardInteractor(GetWatchedListDataAccessInterface watchedListDataAccess,
                               DashboardOutputBoundary dashboardPresenter) {
        this.watchedListDataAccess = watchedListDataAccess;
        this.dashboardPresenter = dashboardPresenter;
    }

    @Override
    public void execute(DashboardInputData inputData) {
        String username = inputData.getUsername();

        MovieList watchedList = watchedListDataAccess.getWatchedList(username);
        List<Movie> movies = watchedList.getMovieList();

        double totalHoursWatched = calculateTotalHoursWatched(movies);
        Map<String, Integer> favoriteGenres = calculateFavoriteGenres(movies);
        double averageRating = calculateAverageRating(movies);
        Map<String, Double> highestRatedGenres = calculateHighestRatedGenres(movies);
        List<String> longestMovies = findLongestMovies(movies);

        DashboardOutputData outputData = new DashboardOutputData(
                totalHoursWatched,
                favoriteGenres,
                averageRating,
                highestRatedGenres,
                longestMovies,
                username
        );

        dashboardPresenter.prepareSuccessView(outputData);
    }

    @Override
    public void switchToLoggedInView(DashboardInputData inputData) {
        DashboardOutputData outputData = new DashboardOutputData(
                0, // No need for metrics when switching views
                new HashMap<>(),
                0.0,
                new HashMap<>(),
                new ArrayList<>(),
                inputData.getUsername()
        );
        dashboardPresenter.switchToLoggedInView(outputData);
    }

    private double calculateTotalHoursWatched(List<Movie> movies) {
        return movies.stream()
                .mapToDouble(Movie::getRuntime)
                .sum() / 60.0;
    }

    private Map<String, Integer> calculateFavoriteGenres(List<Movie> movies) {
        Map<String, Integer> genreCounts = new HashMap<>();
        for (Movie movie : movies) {
            for (String genre : movie.getGenre()) {
                genreCounts.put(genre, genreCounts.getOrDefault(genre, 0) + 1);
            }
        }
        return genreCounts;
    }

    private double calculateAverageRating(List<Movie> movies) {
        return movies.stream()
                .mapToDouble(Movie::getRottenTomatoes)
                .filter(rating -> rating != -1) // Exclude movies with no rating
                .average()
                .orElse(0.0);
    }

    private Map<String, Double> calculateHighestRatedGenres(List<Movie> movies) {
        Map<String, List<Integer>> genreRatings = new HashMap<>();
        for (Movie movie : movies) {
            for (String genre : movie.getGenre()) {
                genreRatings.computeIfAbsent(genre, k -> new ArrayList<>())
                        .add(movie.getRottenTomatoes());
            }
        }
        return genreRatings.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .filter(rating -> rating != -1) // Exclude movies with no rating
                                .mapToInt(Integer::intValue)
                                .average()
                                .orElse(0.0)
                ));
    }

    private List<String> findLongestMovies(List<Movie> movies) {
        return movies.stream()
                .sorted(Comparator.comparing(Movie::getRuntime).reversed())
                .limit(5) // Get top 5 longest movies
                .map(Movie::getTitle)
                .collect(Collectors.toList());
    }
}
