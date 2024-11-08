package interface_adapter.logged_in;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Logged In View.
 */
public class LoggedInViewModel extends ViewModel<LoggedInState> {
    public static final String TITLE_LABEL = "Logged In View";

    public static final String TO_SEARCH_BUTTON_LABEL = "Go to Search";
    public static final String TO_WATCHLIST_BUTTON_LABEL = "Go to Watchlist";
    public static final String TO_WATCHED_LIST_BUTTON_LABEL = "Go to Watched List";
    public static final String TO_CHANGE_PASSWORD_BUTTON_LABEL = "Go to Change Password";
    public static final String LOGOUT_BUTTON_LABEL = "Log Out";

    public LoggedInViewModel() {
        super("logged in");
        setState(new LoggedInState());
    }
}
