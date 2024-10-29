package use_case.search;

import java.io.IOException;

import entity.Movie;

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
