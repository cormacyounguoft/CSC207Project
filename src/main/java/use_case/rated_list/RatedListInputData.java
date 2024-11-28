package use_case.rated_list;

/**
 * The input data for the rated list use case.
 */
public class RatedListInputData {
    private final String username;
    private final String title;

    public RatedListInputData(String username, String title) {
        this.username = username;
        this.title = title;
    }

    public String getUsername() {
        return this.username;
    }

    public String getTitle() {
        return title;
    }
}
