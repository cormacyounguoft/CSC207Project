package interface_adapter.watched_list_remove;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Watched List View.
 */
public class WatchedListRemoveViewModel extends ViewModel<WatchedListRemoveState> {

    public static final String TITLE = "Watched List";
    public static final String CANCEL_BUTTON = "Cancel";
    public static final String REMOVE_BUTTON = "Remove";
    public static final String RATE_BUTTON = "Rate";

    public WatchedListRemoveViewModel() {
        super("watched list");
        setState(new WatchedListRemoveState());
    }
}
