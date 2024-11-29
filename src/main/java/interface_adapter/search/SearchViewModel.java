package interface_adapter.search;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Search View.
 */
public class SearchViewModel extends ViewModel<SearchState> {

    public static final String TITLE = "Search View";
    public static final String SEARCH = "Search movie by title:";
    public static final String SEARCH_BUTTON = "Search";
    public static final String CANCEL = "Cancel";

    public SearchViewModel() {
        super("search");
        setState(new SearchState());
    }
}
