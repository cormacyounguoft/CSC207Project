package use_case.rate;

/**
 * DAO for the Rate Use Case.
 */
public interface RateUserDataAccessInterface {

    /**
     * Saves the user rating for the rate use case.
     * @param title the title of the movie being rated.
     * @param username the username of the user making the rating.
     * @param rating the rating being given.
     */
    void saveUserRating(String username, String title, int rating);
}
