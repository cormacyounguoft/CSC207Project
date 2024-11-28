package use_case;

import entity.Movie;

import java.io.IOException;

/**
 * Data access interface for searching for movies using the API.
 */
public interface SearchDataAccessInterface {
    /**
     * Return the movie that was searched for.
     * @param title the name of the movie to search for.
     * @return the movie result.
     * @throws IOException if the movie is not found.
     */
    Movie search(String title) throws IOException;
}
