package interface_adapter.search_result;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Search Result View.
 */
public class SearchResultViewModel extends ViewModel<SearchResultState> {
    public static final String TITLE_LABEL = "Search Result View";
    public static final String TO_HOME_BUTTON_LABEL = "Go to Home";
    public static final String SEARCH_RESULT_LABEL = "Movie search result:";

    public SearchResultViewModel() {
        super("search result");
        setState(new SearchResultState());
    }
}
