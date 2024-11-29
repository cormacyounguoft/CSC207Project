package interface_adapter.search_result;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Search Result View.
 */
public class SearchResultViewModel extends ViewModel<SearchResultState> {

    public static final String TITLE = "Search Result View";
    public static final String TO_HOME_BUTTON = "Go to Home";

    public SearchResultViewModel() {
        super("search result");
        setState(new SearchResultState());
    }
}
