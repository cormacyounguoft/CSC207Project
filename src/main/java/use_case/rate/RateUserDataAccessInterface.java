package use_case.rate;

import entity.Movie;

/**
 * DAO for the Rate Use Case.
 */
public interface RateUserDataAccessInterface {

    void saveUserRating(String username, Movie movie, int rating);
}
