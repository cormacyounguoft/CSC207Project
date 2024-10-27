package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String name;
    private final String password;
    private final List<Movie> watchList;
    private final List<Movie> watchedList;
    private final List<UserRating> ratings;

    public CommonUser(String name, String password) {
        this.name = name;
        this.password = password;
        this.watchList = new ArrayList<>();
        this.watchedList = new ArrayList<>();
        this.ratings = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public List<Movie> getWatchList() {
        return this.watchList;
    }

    @Override
    public List<Movie> getWatchedList() {
        return this.watchedList;
    }

    @Override
    public List<UserRating> getUserRatings() {
        return this.ratings;
    }

    @Override
    public void addToWatchList(Movie movie) {
        if (!this.watchList.contains(movie)) {
            this.watchList.add(movie);
        }
    }

    @Override
    public void removeFromWatchList(Movie movie) {
        this.watchList.remove(movie);
    }

    @Override
    public void addToWatchedList(Movie movie) {
        if (!this.watchedList.contains(movie)) {
            this.watchedList.add(movie);
        }
    }

    @Override
    public void removeFromWatchedList(Movie movie) {
        this.watchedList.remove(movie);
    }

    @Override
    public void addUserRating(UserRating rating) {
        if (!this.ratings.contains(rating)) {
            this.ratings.add(rating);
        }
    }
}
