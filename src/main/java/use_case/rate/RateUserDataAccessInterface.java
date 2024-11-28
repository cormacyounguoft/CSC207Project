package use_case.rate;

/**
 * DAO for the Rate Use Case.
 */
public interface RateUserDataAccessInterface {

    void saveUserRating(String username, String title, int rating);
}
