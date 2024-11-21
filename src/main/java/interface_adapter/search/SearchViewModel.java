package interface_adapter.search;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Search View.
 */
public class SearchViewModel extends ViewModel<SearchState> {

    public static final String TITLE_LABEL = "Search View";

    public static final String SEARCH_LABEL = "Search movie by title:";

    public static final String SEARCH_BUTTON_LABEL = "Search";

    public static final String CANCEL_LABEL = "Cancel";

    public SearchViewModel() {
        super("search");
        setState(new SearchState());
    }
}
