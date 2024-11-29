package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * The representation of a movie in our program.
 */
public class Movie {

    private String title;
    private String releaseDate;
    private String description;
    private int rottenTomatoes;
    private int runtime;
    private List<String> genre;
    private List<String> actors;
    private List<String> director;
    private String posterLink;

    public Movie() {
        this.title = "";
        this.releaseDate = "";
        this.description = "";
        this.rottenTomatoes = -1;
        this.runtime = -1;
        this.genre = new ArrayList<>();
        this.actors = new ArrayList<>();
        this.director = new ArrayList<>();
        this.posterLink = "";
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

    public int getRuntime() {
        return runtime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRottenTomatoes(int rottenTomatoes) {
        this.rottenTomatoes = rottenTomatoes;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public void setDirector(List<String> director) {
        this.director = director;
    }

    public String getPosterLink() {
        return posterLink;
    }

    public void setPosterLink(String posterLink) {
        this.posterLink = posterLink;
    }
}
