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


    public RatedListPresenter(ViewManagerModel viewManagerModel,
                              LoggedInViewModel loggedInViewModel
                              ) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;

    }


    @Override
    public void prepareSuccessView(RatedListOutputData outputData) {
        final LoggedInState state = loggedInViewModel.getState();
        state.setUsername(outputData.getUsername());
        this.loggedInViewModel.setState(state);
        this.loggedInViewModel.firePropertyChanged();
        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }


}
