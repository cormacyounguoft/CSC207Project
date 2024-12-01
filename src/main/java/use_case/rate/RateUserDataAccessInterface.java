package use_case.rate;

import entity.MovieList;

/**
 * DAO for the Rate Use Case.
 */
public interface RateUserDataAccessInterface {

    /**
     * Save user rating.
     * @param username the username.
     * @param title the movie tile.
     * @param rating the rating.
     */
    void saveUserRating(String username, String title, int rating);

    /**
     * Returns the watched list for the user
     * @param username the username.
     * @return the MovieList
     */
    public MovieList getWatchedList(String username);
}
