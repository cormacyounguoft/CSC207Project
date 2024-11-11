package interface_adapter.watched_list;

import interface_adapter.ViewModel;

public class WatchedListViewModel extends ViewModel<WatchedListState> {
    public static final String TITLE_LABEL = "Logged In Search Result View";

    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    public WatchedListViewModel() {
        super("watched list");
        setState(new WatchedListState());
    }
}
