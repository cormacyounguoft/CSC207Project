package data_access;

import entity.Movie;
import entity.MovieFactory;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import use_case.logged_in_search.LoggedInSearchDataAccessInterface;
import use_case.search.SearchDataAccessInterface;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * DAO for searching for movies using the API.
 * @Null
 */
public class MovieAccessObject implements SearchDataAccessInterface,
        LoggedInSearchDataAccessInterface {
    public static final String N_A = "N/A";
    public static final String COMMA = ",";
    public static final String COMMA_SPACE = ", ";

    @Override
    public Movie search(String title) throws IOException {
        final String url = getUrl(title);
        final JSONObject response = getResponse(url);

        if (response.get("Response").equals("True")) {
            final MovieFactory movieFactory = new MovieFactory();
            final Movie movie = movieFactory.create();

            movie.setTitle(getTitle(response));
            movie.setReleaseDate(getReleaseDate(response));
            movie.setDescription(getDescription(response));
            movie.setRottenTomatoes(getRottenTomatoes(response));
            movie.setRuntime(getRuntime(response));
            movie.setGenre(getGenre(response));
            movie.setActors(getActors(response));
            movie.setDirector(getDirector(response));
            movie.setPosterLink(getPosterLink(response));

            return movie;
        }
        else {
            throw new IOException();
        }
    }

    private String getUrl(String title) {
        return "http://www.omdbapi.com/?t=" + URLEncoder.encode(title, StandardCharsets.UTF_8) + "&apikey=52049eeb";
    }

    private JSONObject getResponse(String url) {
        return Unirest.get(url).asJson().getBody().getObject();
    }

    private String getTitle(JSONObject response) {
        return response.get("Title").toString();
    }

    private String getReleaseDate(JSONObject response) {
        final String releaseDate = response.get("Released").toString();
        String result = "";
        if (!N_A.equals(releaseDate)) {
            result = releaseDate;
        }
        return result;
    }

    private String getDescription(JSONObject response) {
        final String releasePlot = response.get("Plot").toString();
        String result = "";
        if (!N_A.equals(releasePlot)) {
            result = releasePlot;
        }
        return result;
    }

    private List<String> getGenre(JSONObject response) {
        final String genreString = response.get("Genre").toString();
        List<String> result = new ArrayList<>();
        if (!N_A.equals(genreString)) {
            result = new ArrayList<>(Arrays.asList(genreString.split(COMMA_SPACE)));
        }
        return result;
    }

    /**
     * Returns the Rotten Tomatoes score for this movie.
     * @param response The response from the API.
     * @return -1 if rating not available.
     */
    private int getRottenTomatoes(JSONObject response) {
        final String rottenTomatoesString = response.get("Ratings").toString();
        final List<String> ratings = Arrays.asList(rottenTomatoesString.split(COMMA));

        int result = -1;

        if (ratings.contains("{\"Source\":\"Rotten Tomatoes\"")) {
            final String rating = ratings.get(ratings.indexOf("{\"Source\":\"Rotten Tomatoes\"") + 1)
                    .replaceAll("\\D", "");

            result = Integer.parseInt(rating);
        }
        return result;
    }

    private List<String> getActors(JSONObject response) {
        final String actorsString = response.get("Actors").toString();
        List<String> result = new ArrayList<>();
        if (!N_A.equals(actorsString)) {
            result = new ArrayList<>(Arrays.asList(actorsString.split(COMMA_SPACE)));
        }
        return result;
    }

    private List<String> getDirector(JSONObject response) {
        final String directorString = response.get("Director").toString();
        List<String> result = new ArrayList<>();
        if (!N_A.equals(directorString)) {
            result = new ArrayList<>(Arrays.asList(directorString.split(COMMA_SPACE)));
        }
        return result;
    }

    private int getRuntime(JSONObject response) {
        final String runtimeString = response.get("Runtime").toString();
        int result = -1;
        if (!N_A.equals(runtimeString)) {
            result = Integer.parseInt(runtimeString.replaceAll("\\D", ""));
        }
        return result;
    }

    private String getPosterLink(JSONObject response) {
        final String posterLinkString = response.get("Poster").toString();
        String result = "";
        if (!N_A.equals(posterLinkString)) {
            result = posterLinkString;
        }
        return result;
    }
}
