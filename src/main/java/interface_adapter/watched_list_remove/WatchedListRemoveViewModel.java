package interface_adapter.watched_list_remove;

import interface_adapter.ViewModel;

public class WatchedListRemoveViewModel extends ViewModel<WatchedListRemoveState> {
    public static final String TITLE_LABEL = "Watched List";

    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    public WatchedListRemoveViewModel() {
        super("watched list");
        setState(new WatchedListRemoveState());
    }
}
