package data_access;

import entity.Movie;
import entity.MovieList;
import entity.User;
import use_case.add_to_watched_list.AddToWatchedListDataAccessInterface;
import use_case.add_to_watchlist.AddToWatchlistDataAccessInterface;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.get_watched_list.GetWatchedListDataAccessInterface;
import use_case.get_watchlist.GetWatchlistDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.rate.RateUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

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
        GetWatchlistDataAccessInterface,
        GetWatchedListDataAccessInterface {

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
    public void changePassword(User user) {
        // Replace the old entry with the new password
        users.put(user.getName(), user);
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
    public void saveUserRating(String username, Movie movie, int rating) {
        this.get(username).getUserRatings().addRating(movie, rating);
    }

    @Override
    public MovieList getWatchedList(String username) {
        return this.get(username).getWatchedList();
    }

    @Override
    public MovieList getWatchlist(String username) {
        return this.get(username).getWatchList();
    }
}
