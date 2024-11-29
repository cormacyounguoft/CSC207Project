package use_case.rated_list;

/**
 * The rated list data access interface.
 */
public interface RatedListDataAccessInterface {

    /**
     * The state for the rated list use case.
     * @param username the username of the user.
     * @param title The title of the movie.
     */
    void removeUserRating(String username, String title);
}
