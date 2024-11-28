package interface_adapter.watched_list;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.watched_list.WatchedListOutputBoundary;
import use_case.watched_list.WatchedListOutputData;

/**
 * The Presenter for the watched list Use Case.
 */
public class WatchedListPresenter implements WatchedListOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;

    public WatchedListPresenter(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(WatchedListOutputData outputData) {
        final LoggedInState state = loggedInViewModel.getState();
        state.setUsername(outputData.getUsername());
        this.loggedInViewModel.setState(state);
        this.loggedInViewModel.firePropertyChanged();
        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
