package interface_adapter.ratedList;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.watched_list.WatchedListViewModel;
import use_case.rated_list.RatedListOutputBoundary;
import use_case.rated_list.RatedListOutputData;
import use_case.watched_list.WatchedListOutputData;

public class RatedListPresenter implements RatedListOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;
    private final RatedListViewModel ratedListViewModel;

    public RatedListPresenter(ViewManagerModel viewManagerModel,
                              LoggedInViewModel loggedInViewModel,
                              RatedListViewModel ratedListViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.ratedListViewModel = ratedListViewModel;
    }


    @Override
    public void prepareSuccessView() {
        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }


}
