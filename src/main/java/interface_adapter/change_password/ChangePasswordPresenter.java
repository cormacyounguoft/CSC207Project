package interface_adapter.change_password;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.change_password.ChangePasswordOutputData;
import use_case.watchlist.WatchlistOutputData;

/**
 * The Presenter for the Change Password Use Case.
 */
public class ChangePasswordPresenter implements ChangePasswordOutputBoundary {

    private final ChangePasswordViewModel changePasswordViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;

    public ChangePasswordPresenter(ChangePasswordViewModel changePasswordViewModel, LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel) {
        this.changePasswordViewModel = changePasswordViewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ChangePasswordOutputData outputData) {
        // currently there isn't anything to change based on the output data,
        // since the output data only contains the username, which remains the same.
        // We still fire the property changed event, but just to let the view know that
        // it can alert the user that their password was changed successfully..
        changePasswordViewModel.firePropertyChanged("password");

    }

    @Override
    public void prepareFailView(String error) {
        // note: this use case currently can't fail
    }

    public void switchToLoggedInView(ChangePasswordOutputData outputData) {
        final LoggedInState state = loggedInViewModel.getState();
        state.setUsername(outputData.getUsername());
        loggedInViewModel.setState(state);
        loggedInViewModel.firePropertyChanged();

        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
