package interface_adapter.ratedList;

import interface_adapter.ViewModel;
import interface_adapter.watched_list.WatchedListState;

public class RatedListViewModel extends ViewModel<RatedListState> {
    public static final String TITLE_LABEL = "Logged In Search Result View";

    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    public RatedListViewModel() {
        super("rated list");
        setState(new RatedListState());
    }
}
