package interface_adapter.RatedList;

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
    public void prepareSuccessView(RatedListOutputData outputData) {
        ratedListViewModel.firePropertyChanged();
    }

    @Override
    public void switchToLoggedInView(RatedListOutputData outputData) {
        final LoggedInState state = loggedInViewModel.getState();
        state.setUsername(outputData.getUsername());
        loggedInViewModel.setState(state);
        loggedInViewModel.firePropertyChanged();

        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
