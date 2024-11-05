package entity;

/**
 * Factory for creating an empty Movie objects.
 */
public class MovieFactory {
    /**
     * Creates a movie object.
     * @return The movie object
     */
    public Movie create() {
        return new Movie();
    }
}
