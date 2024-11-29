package use_case.get_rated_list;

import java.util.List;
import java.util.Map;
/**
 * The Data Access Interface for Get Rated List.
 */

public interface GetRatedListDataAccessInterface {

    /**
     * Retrieves the user's ratings.
     * @param username the username of the user whose ratings are to be retrieved
     * @return a map where keys are categories (e.g., movie genres) and values are lists of rated items
     */
    Map<String, List<String>> getUserRating(String username);
}
