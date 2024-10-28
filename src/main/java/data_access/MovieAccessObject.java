package data_access;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entity.Movie;
import entity.MovieFactory;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import use_case.search.SearchDataAccessInterface;

/**
 * DAO for searching for movies using the API.
 * @Null
 */
public class MovieAccessObject implements SearchDataAccessInterface {
    public static final String N_A = "N/A";
    public static final String COMMA = ",";

    @Override
    public Movie search(String title) {
        final String url = getUrl(title);
        final JSONObject response = getResponse(url);

        if (response.get("Response").equals("True")) {
            final MovieFactory movieFactory = new MovieFactory();
            return movieFactory.create(
                    getTitle(response),
                    getReleaseDate(response),
                    getDescription(response),
                    getRottenTomatoes(response),
                    getGenre(response),
                    getActors(response),
                    getDirector(response)
            );

        }
        else {
            throw new RuntimeException();
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
        return response.get("Released").toString();
    }

    private String getDescription(JSONObject response) {
        return response.get("Plot").toString();
    }

    private List<String> getGenre(JSONObject response) {
        final String genreString = response.get("Genre").toString();
        List<String> result = new ArrayList<>();
        if (!N_A.equals(genreString)) {
            result = new ArrayList<>(Arrays.asList(genreString.split(COMMA)));
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
            result = new ArrayList<>(Arrays.asList(actorsString.split(COMMA)));
        }
        return result;
    }

    private List<String> getDirector(JSONObject response) {
        final String directorString = response.get("Director").toString();
        List<String> result = new ArrayList<>();
        if (!N_A.equals(directorString)) {
            result = new ArrayList<>(Arrays.asList(directorString.split(COMMA)));
        }
        return result;
    }
}
