package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * The representation of a list of movies for this program.
 */
public class MovieList {
    private List<Movie> movieList;

    public MovieList() {
        movieList = new ArrayList<>();
    }

    public int getMovieCount() {
        return movieList.size();
    }

    public void addMovie(Movie movie) {
        if (!movieList.contains(movie)) {
            movieList.add(movie);
        }
    }

    public void removeMovie(Movie movie) {
        movieList.remove(movie);
    }

    public List<String> getMovieTitles() {
        List<String> result = new ArrayList<>();
        for (Movie movie : movieList) {
            result.add(movie.getTitle());
        }
        return result;
    }
}
