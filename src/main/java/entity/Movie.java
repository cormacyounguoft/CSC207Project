package entity;

import java.util.List;

/**
 * The representation of a movie in our program.
 */
public class Movie {
    private final String title;
    private final String releaseDate;
    private final String description;
    private final int rottenTomatoes;
    private final List<String> genre;
    private final List<String> actors;
    private final List<String> director;

    public Movie(String title, String releaseDate, String description, int rottenTomatoes,
                 List<String> genre, List<String> actors, List<String> director) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.description = description;
        this.rottenTomatoes = rottenTomatoes;
        this.genre = genre;
        this.actors = actors;
        this.director = director;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public int getRottenTomatoes() {
        return rottenTomatoes;
    }

    public List<String> getGenre() {
        return genre;
    }

    public List<String> getActors() {
        return actors;
    }

    public List<String> getDirector() {
        return director;
    }

    @Override
    public String toString() {
        return "Movie{"
                + "title='" + title + '\''
                + ", releaseDate='" + releaseDate + '\''
                + ", description='" + description + '\''
                + ", rottenTomatoes=" + rottenTomatoes
                + ", genre=" + genre
                + ", actors=" + actors
                + ", director=" + director
                + '}';
    }
}
