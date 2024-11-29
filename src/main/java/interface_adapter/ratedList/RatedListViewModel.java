package interface_adapter.ratedList;

import interface_adapter.ViewModel;

/**
 * The View Model for the rated list.
 */
public class RatedListViewModel extends ViewModel<RatedListState> {

    public static final String TITLE = "Rated List";
    public static final String CANCEL_BUTTON = "Cancel";
    public static final String REMOVE_BUTTON = "Remove";

    public RatedListViewModel() {
        super("rated list");
        setState(new RatedListState());
    }
}
