package use_case.rate;

/**
 * Input data for actions which are related to rating.
 */
public class RateInputData {

    private final String username;
    private final String title;
    private final int rating;

    public RateInputData(String username, String title, int rating) {
        this.username = username;
        this.title = title;
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public int getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }
}
