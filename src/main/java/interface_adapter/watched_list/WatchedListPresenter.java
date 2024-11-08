package interface_adapter.watched_list;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.watched_list.WatchedListOutputBoundary;
import use_case.watched_list.WatchedListOutputData;

public class WatchedListPresenter implements WatchedListOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;
    private final WatchedListViewModel watchedListViewModel;

    public WatchedListPresenter(ViewManagerModel viewManagerModel,
                                LoggedInViewModel loggedInViewModel,
                                WatchedListViewModel watchedListViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.watchedListViewModel = watchedListViewModel;
    }


    @Override
    public void prepareSuccessView(WatchedListOutputData outputData) {
        watchedListViewModel.firePropertyChanged();
    }

    @Override
    public void switchToLoggedInView(WatchedListOutputData outputData) {
        final LoggedInState state = loggedInViewModel.getState();
        state.setUsername(outputData.getUsername());
        loggedInViewModel.setState(state);
        loggedInViewModel.firePropertyChanged();

        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}