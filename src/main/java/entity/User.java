package entity;

import java.util.List;

/**
 * The representation of a user in our program.
 */
public interface User {

    /**
     * Returns the username of the user.
     * @return the username of the user.
     */
    String getName();

    /**
     * Returns the password of the user.
     * @return the password of the user.
     */
    String getPassword();

    /**
     * Returns the watchlist of the user.
     * @return the watchlist of the user.
     */
    List<Movie> getWatchList();

    /**
     * Returns the watched list of the user.
     * @return the watched list of the user.
     */
    List<Movie> getWatchedList();

    /**
     * Returns the user ratings that the user has made.
     * @return the list of ratings the user has made.
     */
    List<UserRating> getUserRatings();

    /**
     * Adds the movie to the watchlist of the user.
     * @param movie The movie.
     */
    void addToWatchList(Movie movie);

    /**
     * Removes the movie from the watchlist of the user.
     * @param movie The movie.
     */
    void removeFromWatchList(Movie movie);

    /**
     * Adds the movie to the watched list of the user.
     * @param movie The movie.
     */
    void addToWatchedList(Movie movie);

    /**
     * Removes the movie from the watched list of the user.
     * @param movie The movie.
     */
    void removeFromWatchedList(Movie movie);

    /**
     * Adds the rating to the user's list of ratings.
     * @param rating The rating.
     */
    void addUserRating(UserRating rating);

}
