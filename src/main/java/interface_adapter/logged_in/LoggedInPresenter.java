package interface_adapter.logged_in;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.ChangePasswordState;
import interface_adapter.change_password.ChangePasswordViewModel;
import interface_adapter.logged_in_search.LoggedInSearchState;
import interface_adapter.logged_in_search.LoggedInSearchViewModel;
import use_case.logged_in.LoggedInOutputBoundary;
import use_case.logged_in.LoggedInOutputData;

/**
 * The Presenter for the Logged In Use Case.
 */
public class LoggedInPresenter implements LoggedInOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final ChangePasswordViewModel changePasswordViewModel;
    private final LoggedInSearchViewModel loggedInSearchViewModel;

    public LoggedInPresenter(ViewManagerModel viewManagerModel,
                             ChangePasswordViewModel changePasswordViewModel,
                             LoggedInSearchViewModel loggedInSearchViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.changePasswordViewModel = changePasswordViewModel;
        this.loggedInSearchViewModel = loggedInSearchViewModel;
    }

    @Override
    public void switchToChangePasswordView(LoggedInOutputData loggedInOutputData) {
        final ChangePasswordState changePasswordState = changePasswordViewModel.getState();
        changePasswordState.setUsername(loggedInOutputData.getUsername());
        changePasswordState.setPasswordError(null);
        changePasswordViewModel.setState(changePasswordState);
        changePasswordViewModel.firePropertyChanged();

        viewManagerModel.setState(changePasswordViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToLoggedInSearchView(LoggedInOutputData loggedInOutputData) {
        final LoggedInSearchState loggedInSearchState = loggedInSearchViewModel.getState();
        loggedInSearchState.setUsername(loggedInOutputData.getUsername());
        loggedInSearchViewModel.setState(loggedInSearchState);
        loggedInSearchViewModel.firePropertyChanged();

        viewManagerModel.setState(loggedInSearchViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
