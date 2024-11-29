package use_case.to_logged_in_view;

/**
 * Output data for to logged in view.
 */
public class ToLoggedInViewOutputData {
    private final String username;

    public ToLoggedInViewOutputData(String username) {
        this.username = username;

    }

    public String getUsername() {
        return username;
    }

}
