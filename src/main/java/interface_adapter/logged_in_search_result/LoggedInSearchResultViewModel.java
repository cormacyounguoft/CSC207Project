package interface_adapter.logged_in_search_result;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Logged In Search Result View.
 */
public class LoggedInSearchResultViewModel extends ViewModel<LoggedInSearchResultState> {
    public static final String TITLE_LABEL = "Logged In Search Result View";

    public static final String ADD_TO_WATCHLIST_BUTTON_LABEL = "Add to watchlist";
    public static final String ADD_TO_WATCHED_LIST_BUTTON_LABEL = "Add to watched list";
    public static final String RATE_BUTTON_LABEL = "Rate movie";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    public LoggedInSearchResultViewModel() {
        super("logged in search result");
        setState(new LoggedInSearchResultState());
    }
}
