package data_access;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entity.Movie;
import entity.MovieFactory;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import use_case.SearchDataAccessInterface;

/**
 * DAO for searching for movies using the API.
 * @Null
 */
public class MovieAccessObject implements SearchDataAccessInterface {

    public static final String N_A = "N/A";
    public static final String COMMA = ",";
    public static final String COMMA_SPACE = ", ";

    @Override
    public Movie search(String title) throws IOException {
        final String url = getUrl(title);
        final JSONObject response = getResponse(url);

        if (response.get("Response").equals("True")) {
            final Movie movie = new MovieFactory().create();

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
        return "http://www.omdbapi.com/?t=" + URLEncoder.encode(title, StandardCharsets.UTF_8)
                + "&apikey=" + System.getenv("APIKEY");
    }

    private JSONObject getResponse(String url) {
        return Unirest.get(url).asJson().getBody().getObject();
    }

    private String getTitle(JSONObject response) {
        return response.get("Title").toString();
    }

    private String getReleaseDate(JSONObject response) {
        String result = "";
        if (!N_A.equals(response.get("Released").toString())) {
            result = response.get("Released").toString();
        }
        return result;
    }

    private String getDescription(JSONObject response) {
        String result = "";
        if (!N_A.equals(response.get("Plot").toString())) {
            result = response.get("Plot").toString();
        }
        return result;
    }

    private List<String> getGenre(JSONObject response) {
        List<String> result = new ArrayList<>();
        if (!N_A.equals(response.get("Genre").toString())) {
            result = new ArrayList<>(Arrays.asList(response.get("Genre").toString().split(COMMA_SPACE)));
        }
        return result;
    }

    /**
     * Returns the Rotten Tomatoes score for this movie.
     * @param response The response from the API.
     * @return -1 if rating not available.
     */
    private int getRottenTomatoes(JSONObject response) {
        final List<String> ratings = Arrays.asList(response.get("Ratings").toString().split(COMMA));
        int result = -1;

        if (ratings.contains("{\"Source\":\"Rotten Tomatoes\"")) {
            final String rating = ratings.get(ratings.indexOf("{\"Source\":\"Rotten Tomatoes\"") + 1)
                    .replaceAll("\\D", "");
            result = Integer.parseInt(rating);
        }
        return result;
    }

    private List<String> getActors(JSONObject response) {
        List<String> result = new ArrayList<>();
        if (!N_A.equals(response.get("Actors").toString())) {
            result = new ArrayList<>(Arrays.asList(response.get("Actors").toString().split(COMMA_SPACE)));
        }
        return result;
    }

    private List<String> getDirector(JSONObject response) {
        List<String> result = new ArrayList<>();
        if (!N_A.equals(response.get("Director").toString())) {
            result = new ArrayList<>(Arrays.asList(response.get("Director").toString().split(COMMA_SPACE)));
        }
        return result;
    }

    private int getRuntime(JSONObject response) {
        int result = -1;
        if (!N_A.equals(response.get("Runtime").toString())) {
            result = Integer.parseInt(response.get("Runtime").toString().replaceAll("\\D", ""));
        }
        return result;
    }

    private String getPosterLink(JSONObject response) {
        String result = "";
        if (!N_A.equals(response.get("Poster").toString())) {
            result = response.get("Poster").toString();
        }
        return result;
    }
}
