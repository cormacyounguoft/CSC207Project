package interface_adapter.logged_in;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Logged In View.
 */
public class LoggedInViewModel extends ViewModel<LoggedInState> {

    public static final String TITLE = "Logged In View";
    public static final String TO_SEARCH_BUTTON = "Search";
    public static final String TO_WATCHLIST_BUTTON = "Watchlist";
    public static final String TO_WATCHED_LIST_BUTTON = "Watched List";
    public static final String TO_CHANGE_PASSWORD_BUTTON = "Change Password";
    public static final String TO_RATED_LIST_BUTTON = "Rated List";
    public static final String TO_DASHBOARD_BUTTON = "Dashboard";
    public static final String LOGOUT_BUTTON = "Log Out";

    public LoggedInViewModel() {
        super("logged in");
        setState(new LoggedInState());
    }
}
