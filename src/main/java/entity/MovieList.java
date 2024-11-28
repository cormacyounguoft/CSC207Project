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

    public void addMovie(Movie movie) {
        if (!this.contains(movie)) {
            movieList.add(movie);
        }
    }

    public void removeMovie(String movie) {
        for (Movie m : movieList) {
            if (m.getTitle().equals(movie)) {
                movieList.remove(m);
            }
        }
    }

    public List<String> getMovieTitles() {
        List<String> result = new ArrayList<>();
        for (Movie movie : movieList) {
            result.add(movie.getTitle());
        }
        return result;
    }

    public Movie findMovieByTitle(String title) {
        for (Movie movie : movieList) {
            if (movie.getTitle().equalsIgnoreCase(title)) { // Case-insensitive comparison
                return movie; // Return the matching movie
            }
        }
        return null; // Return null if no movie is found
    }

    public boolean contains(Movie movie) {
        return movieList.contains(movie);
    }

    @Override
    public String toString() {
        List<String> result = new ArrayList<>();
        for (Movie movie : movieList) {
            result.add(movie.getTitle());
        }
        return result.toString();
    }

    public List<String> getPosters() {
        List<String> result = new ArrayList<>();
        for (Movie movie : movieList) {
            result.add(movie.getPosterLink());
        }
        return result;
    }

    public String getPoster(String title){
        for (Movie movie : movieList) {
            if (movie.getTitle().equalsIgnoreCase(title)) {
                return movie.getPosterLink();
            }
        }
        return title;
    }

    public boolean containsTitle(String title) {
        for (Movie movie : movieList) {
            if (movie.getTitle().equalsIgnoreCase(title)) {
                return true;
            }
        }
        return false;
    }


}
