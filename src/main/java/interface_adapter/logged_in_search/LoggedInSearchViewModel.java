package interface_adapter.logged_in_search;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Logged In Search View.
 */
public class LoggedInSearchViewModel extends ViewModel<LoggedInSearchState> {
    public static final String TITLE_LABEL = "Logged In View";
    public static final String SEARCH_LABEL = "Search movie by title:";
    public static final String SEARCH_BUTTON_LABEL = "Search";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    public LoggedInSearchViewModel() {
        super("logged in search");
        setState(new LoggedInSearchState());
    }
}
