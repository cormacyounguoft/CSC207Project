package interface_adapter.watched_list_remove;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.watched_list_remove.WatchedListRemoveOutputBoundary;
import use_case.watched_list_remove.WatchedListRemoveOutputData;

/**
 * The Presenter for the watched list remove Use Case.
 */
public class WatchedListRemovePresenter implements WatchedListRemoveOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;

    public WatchedListRemovePresenter(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(WatchedListRemoveOutputData outputData) {
        final LoggedInState state = loggedInViewModel.getState();
        state.setUsername(outputData.getUsername());
        this.loggedInViewModel.setState(state);
        this.loggedInViewModel.firePropertyChanged();
        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
