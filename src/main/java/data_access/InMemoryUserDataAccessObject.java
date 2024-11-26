package data_access;

import entity.Movie;
import entity.MovieList;
import entity.User;
import entity.UserRating;
import use_case.add_to_watched_list.AddToWatchedListDataAccessInterface;
import use_case.add_to_watchlist.AddToWatchlistDataAccessInterface;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.get_rated_list.GetRatedListDataAccessInterface;
import use_case.get_watched_list.GetWatchedListDataAccessInterface;
import use_case.get_watchlist.GetWatchlistDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.rate.RateUserDataAccessInterface;
import use_case.rated_list.RatedListDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.util.*;

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
        GetWatchedListDataAccessInterface,
        GetRatedListDataAccessInterface,
        RatedListDataAccessInterface
{

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
        User user = this.get(username);
        user.setUserPassword(password);
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
    public void saveUserRating(String username, String title, int rating) {
        MovieList list = this.get(username).getWatchedList();
        Movie movie = list.findMovieByTitle(title);
        this.get(username).getUserRatings().addRating(movie, rating);
    }

    @Override
    public void removeUserRating(String username, String title) {
        UserRating userRating = this.get(username).getUserRatings();
        userRating.getMovieToRating().remove(title);

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
        User user = this.get(username);
        Map<String, Integer> ratings = user.getUserRatings().getMovieToRating();
        MovieList movieList = user.getWatchedList();

        Map<String, List<String>> result = new HashMap<>();
        ratings.forEach((title, rating) -> {
            String poster = movieList.getPoster(title);
            result.put(title, Arrays.asList(String.valueOf(rating), poster));
        });

        return result.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.comparing(list -> Integer.parseInt(list.get(0)))))
                .collect(
                        LinkedHashMap::new,
                        (map, entry) -> map.put(entry.getKey(), entry.getValue()),
                        LinkedHashMap::putAll
                );
    }
    }

