package use_case.dashboard;
import java.util.Map;
import entity.MovieList;

public interface DashboardDataAccessInterface {

    /**
     * Retrieves the list of movies watched by the user.
     *
     * @param username the username of the user
     * @return the list of movies watched by the user
     */
    MovieList getWatchedMovies(String username);

    /**
     * Retrieves the user's ratings for movies.
     *
     * @param username the username of the user
     * @return a map of movie titles to their ratings
     */
    Map<String, Integer> getUserRatings(String username);
}