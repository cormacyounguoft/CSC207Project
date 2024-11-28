package interface_adapter.watchlistremove;

import interface_adapter.ViewModel;
import interface_adapter.watchlist.WatchlistState;

public class WatchlistViewModel extends ViewModel<WatchlistState> {
    public static final String TITLE_LABEL = "Logged In Search Result View";

    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    public WatchlistViewModel() {
        super("watchlist");
        setState(new WatchlistState());
    }
}
