package interface_adapter.search_result;

import interface_adapter.ViewModel;

public class SearchResultViewModel extends ViewModel<SearchResultState> {
    public static final String TITLE_LABEL = "Search Result View";

    public SearchResultViewModel() {
        super("search result");
        setState(new SearchResultState());
    }
}
