package entity;

/**
 * Factory for creating MovieList objects.
 */
public class MovieListFactory {
    /**
     * Creates a new MovieList.
     * @return a new movie list.
     */
    public static MovieList create() {
        return new MovieList();
    }
}
