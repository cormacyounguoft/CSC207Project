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
    private final String comma = ",";
    private final int startRottenTomatoes = 9;
    private final int endRottenTomatoes = 3;

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
        return new ArrayList<>(Arrays.asList(genreString.split(comma)));
    }

    /**
     * Returns the Rotten Tomatoes score for this movie.
     * @param response The response from the API.
     * @return -1 if rating not available.
     */
    private int getRottenTomatoes(JSONObject response) {
        final String rottenTomatoesString = response.get("Ratings").toString();
        final List<String> ratings = Arrays.asList(rottenTomatoesString.split(comma));

        int result = -1;

        if (ratings.contains("{\"Source\":\"Rotten Tomatoes\"")) {
            final String rating = ratings.get(ratings.indexOf("{\"Source\":\"Rotten Tomatoes\"") + 1);

            final StringBuilder sb = new StringBuilder();

            for (int i = startRottenTomatoes; i < rating.length() - endRottenTomatoes; i++) {
                sb.append(rating.charAt(i));
            }
            result = Integer.parseInt(sb.toString());
        }
        return result;
    }

    private int getRuntime(JSONObject response) {
        return Integer.parseInt(response.get("Runtime").toString());
    }

    private List<String> getActors(JSONObject response) {
        final String genreString = response.get("Actors").toString();
        return new ArrayList<>(Arrays.asList(genreString.split(comma)));
    }

    private List<String> getDirector(JSONObject response) {
        final String genreString = response.get("Director").toString();
        return new ArrayList<>(Arrays.asList(genreString.split(comma)));
    }
}
