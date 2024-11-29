package use_case.get_watched_list;

/**
 * Input data for get watched list.
 */
public class GetWatchedListInputData {

    private final String username;

    public GetWatchedListInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
