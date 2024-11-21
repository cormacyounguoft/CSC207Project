package use_case.to_logged_in_view;

public class ToLoggedInViewInputData {
    private final String username;

    public ToLoggedInViewInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
