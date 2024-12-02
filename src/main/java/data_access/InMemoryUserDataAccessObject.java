package data_access;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import entity.Movie;
import entity.MovieList;
import entity.User;
import entity.UserRating;
import use_case.MovieListDataAccessInterface;
import use_case.add_to_watched_list.AddToWatchedListDataAccessInterface;
import use_case.add_to_watchlist.AddToWatchlistDataAccessInterface;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.dashboard.DashboardDataAccessInterface;
import use_case.get_rated_list.GetRatedListDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.rate.RateUserDataAccessInterface;
import use_case.rated_list.RatedListDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.watched_list_remove.WatchedListRemoveDataAccessInterface;
import use_case.watchlist_remove.WatchlistRemoveDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface,
        AddToWatchlistDataAccessInterface,
        AddToWatchedListDataAccessInterface,
        RateUserDataAccessInterface,
        MovieListDataAccessInterface,
        GetRatedListDataAccessInterface,
        RatedListDataAccessInterface,
        DashboardDataAccessInterface,
        WatchlistRemoveDataAccessInterface,
        WatchedListRemoveDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();
    private String currentUsername;

    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public void save(User user) {
        users.put(user.getName(), user);
    }

    @Override
    public User get(String username) {
        return users.get(username);
    }

    @Override
    public void changePassword(String username, String password) {
        this.get(username).setUserPassword(password);
    }

    @Override
    public void setCurrentUsername(String name) {
        this.currentUsername = name;
    }

    @Override
    public String getCurrentUsername() {
        return this.currentUsername;
    }

    @Override
    public void saveToWatchedList(String username, Movie movie) {
        this.get(username).getWatchedList().addMovie(movie);
    }

    @Override
    public void saveToWatchlist(String username, Movie movie) {
        this.get(username).getWatchList().addMovie(movie);
    }

    @Override
    public void removeFromWatchedlist(String username, String title) {
        this.get(username).getWatchedList().removeMovie(this.get(username).getWatchedList().findMovieByTitle(title));
        this.removeUserRating(username, title);
    }

    @Override
    public void saveUserRating(String username, String title, int rating) {
        this.get(username).getUserRatings().addRating(this.get(username).getWatchedList()
                .findMovieByTitle(title), rating);
    }

    @Override
    public void removeUserRating(String username, String title) {
        this.get(username).getUserRatings().remove(title);

    }

    @Override
    public void removeFromWatchlist(String username, String movieTitle) {
        this.get(username).getWatchList().removeMovie(this.get(username).getWatchList().findMovieByTitle(movieTitle));
    }

    @Override
    public MovieList getWatchedList(String username) {
        return this.get(username).getWatchedList();
    }

    @Override
    public MovieList getWatchlist(String username) {
        return this.get(username).getWatchList();
    }

    @Override
    public Map<String, List<String>> getUserRating(String username) {
        final Map<String, Integer> ratings = this.get(username).getUserRatings().getMovieToRating();
        final MovieList movieList = this.get(username).getWatchedList();

        final Map<String, List<String>> result = new HashMap<>();

        // Populate result map with movie title to list of rating, poster
        ratings.forEach((title, rating) -> {
            final String poster = movieList.getPoster(title);
            result.put(title, Arrays.asList(String.valueOf(rating), poster));
        });

        // Sort entries by rating and return as LinkedHashMap
        return result.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.comparing(list -> Integer.parseInt(list.get(0)))))
                .collect(
                        LinkedHashMap::new,
                        (map, entry) -> map.put(entry.getKey(), entry.getValue()),
                        LinkedHashMap::putAll
                );
    }

    @Override
    public MovieList getWatchedMovies(String username) {
        return this.get(username).getWatchedList();
    }

    @Override
    public UserRating getUserRatings(String username) {
        return this.get(username).getUserRatings();
    }
}
