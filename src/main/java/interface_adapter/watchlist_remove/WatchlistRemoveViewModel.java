package interface_adapter.watchlist_remove;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Watchlist View.
 */
public class WatchlistRemoveViewModel extends ViewModel<WatchlistRemoveState> {

    public static final String TITLE = "Watchlist";
    public static final String CANCEL_BUTTON = "Cancel";
    public static final String REMOVE_BUTTON = "Remove";
    public static final String WATCHED_BUTTON = "Watched";

    public WatchlistRemoveViewModel() {
        super("watchlist");
        setState(new WatchlistRemoveState());
    }
}
