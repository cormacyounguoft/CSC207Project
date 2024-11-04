package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String name;
    private final String password;
    private final MovieWatchList watchList;
    private final MovieWatchedList watchedList;
    private final List<UserRating> ratings;

    public CommonUser(String name, String password) {
        this.name = name;
        this.password = password;
        this.watchList = new MovieWatchList();
        this.watchedList = new MovieWatchedList();
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
        return this.watchList.getWatchList();
    }

    @Override
    public List<Movie> getWatchedList() {
        return this.watchedList.getWatchedList();
    }

    @Override
    public List<UserRating> getUserRatings() {
        return this.ratings;
    }

    @Override
    public void addToWatchList(Movie movie) {
        this.watchList.addWatch(movie);
    }

    @Override
    public void removeFromWatchList(Movie movie) {
        this.watchList.removeWatch(movie);
    }

    @Override
    public void addToWatchedList(Movie movie) {
        this.watchedList.addMovie(movie);
    }

    @Override
    public void removeFromWatchedList(Movie movie) {
        this.watchedList.removeMovie(movie);
    }

    @Override
    public void addUserRating(UserRating rating) {
        if (!this.ratings.contains(rating)) {
            this.ratings.add(rating);
        }
    }
}
