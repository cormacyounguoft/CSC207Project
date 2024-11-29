package use_case.to_logged_in_view;

/**
 * Input data for to logged in view.
 */
public class ToLoggedInViewInputData {
    private final String username;

    public ToLoggedInViewInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
