package use_case.logged_in_search;

/**
 * Output Data for the Logged In Search Use Case.
 */
public class LoggedInSearchOutputData {

    private final String username;
    private final String title;
    private final String releaseDate;
    private final String description;
    private final String rottenTomatoes;
    private final String runtime;
    private final String genre;
    private final String actors;
    private final String director;
    private final String posterLink;
    private final boolean useCaseFailed;

    public LoggedInSearchOutputData(String username,
                                    String title,
                                    String releaseDate,
                                    String description,
                                    String rottenTomatoes,
                                    String runtime,
                                    String genre,
                                    String actors,
                                    String director,
                                    String posterLink,
                                    boolean useCaseFailed) {
        this.username = username;
        this.title = title;
        this.releaseDate = releaseDate;
        this.description = description;
        this.rottenTomatoes = rottenTomatoes;
        this.runtime = runtime;
        this.genre = genre;
        this.actors = actors;
        this.director = director;
        this.posterLink = posterLink;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
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

    public String getRottenTomatoes() {
        return rottenTomatoes;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getGenre() {
        return genre;
    }

    public String getActors() {
        return actors;
    }

    public String getDirector() {
        return director;
    }

    public String getPosterLink() {
        return posterLink;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
