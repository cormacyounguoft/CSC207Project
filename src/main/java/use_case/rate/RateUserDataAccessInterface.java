package use_case.rate;

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
}
