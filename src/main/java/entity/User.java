package entity;

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
    MovieList getWatchList();

    /**
     * Returns the watched list of the user.
     * @return the watched list of the user.
     */
    MovieList getWatchedList();

    /**
     * Returns all the of the user's rating.
     * @return all the of the user's rating.
     */
    UserRating getUserRatings();

    /**
     * Returns all the of the user's rating.
     * @param password The password set.
     */
    void setUserPassword(String password);
}
