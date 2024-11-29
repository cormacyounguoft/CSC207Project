package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * The representation of a list of movies for this program.
 */
public class MovieList {
    private final List<Movie> movieList;

    public MovieList() {
        movieList = new ArrayList<>();
    }

    public int getMovieCount() {
        return movieList.size();
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    /**
     * Adds a movie to the movie list.
     * @param movie The movie to be added
     */
    public void addMovie(Movie movie) {
        if (!this.contains(movie)) {
            movieList.add(movie);
        }
    }

    /**
     * Remove a movie from the movie list.
     * @param movie The movie to be removed.
     */
    public void removeMovie(Movie movie) {
        movieList.remove(movie);
    }

    /**
     * Finds the movie by the title.
     * @param title The title of the movie
     * @return returns movie if found and null returns null if no movie is found
     */
    public Movie findMovieByTitle(String title) {
        Movie foundMovie = null;
        for (Movie movie : movieList) {
            if (movie.getTitle().equalsIgnoreCase(title)) {
                foundMovie = movie;
                break;
            }
        }
        return foundMovie;
    }

    /**
     * Looks for if there is a specific movie in a movie list.
     * @param movie The movie looked for in the list.
     * @return If a movie is found.
     */
    public boolean contains(Movie movie) {
        return movieList.contains(movie);
    }

    /**
     * Gets the poster link for a movie.
     * @param title the title of the movie.
     * @return returns the movie poster link if found
     */
    public String getPoster(String title) {
        String posterLink = title;
        for (Movie movie : movieList) {
            if (movie.getTitle().equalsIgnoreCase(title)) {
                posterLink = movie.getPosterLink();
            }
        }
        return posterLink;
    }

    /**
     * Checks if a movie with the given title exists in movieList.
     * @param title the title of the movie.
     * @return returns if the movie title exists in movieList.
     */
    public boolean containsTitle(String title) {
        boolean found = false;
        for (Movie movie : movieList) {
            if (movie.getTitle().equalsIgnoreCase(title)) {
                found = true;
                break;
            }
        }
        return found;
    }

}
