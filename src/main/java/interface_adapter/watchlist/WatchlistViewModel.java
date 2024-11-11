package interface_adapter.watchlist;

import interface_adapter.ViewModel;

public class WatchlistViewModel extends ViewModel<WatchlistState> {
    public static final String TITLE_LABEL = "Logged In Search Result View";

    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    public WatchlistViewModel() {
        super("watchlist");
        setState(new WatchlistState());
    }
}
