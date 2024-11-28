package use_case.rated_list;

/**
 * The output data for the rated list use case.
 */
public class RatedListOutputData {
    private final String username;

    public RatedListOutputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
