package interface_adapter.search_result;

/**
 * The state for the Search Result View Model.
 */
public class SearchResultState {
    private String movieError;
    private String title;
    private String releaseDate;
    private String description;
    private String rottenTomatoes;
    private String runtime;
    private String genre;
    private String actors;
    private String director;
    private String poster;

    public String getMovieError() {
        return movieError;
    }

    public void setMovieError(String movieError) {
        this.movieError = movieError;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPoster() {
        return poster;
    }

    public void setPosterLink(String poster) {
        this.poster = poster;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getRottenTomatoes() {
        return rottenTomatoes;
    }

    public void setRottenTomatoes(String rottenTomatoes) {
        this.rottenTomatoes = rottenTomatoes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
