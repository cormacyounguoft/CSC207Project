package interface_adapter.watchlist_remove;

import interface_adapter.ViewModel;

public class WatchlistRemoveViewModel extends ViewModel<WatchlistRemoveState> {
    public static final String TITLE_LABEL = "Logged In Search Result View";

    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    public WatchlistRemoveViewModel() {
        super("watchlist");
        setState(new WatchlistRemoveState());
    }
}
