package interface_adapter.change_password;

import interface_adapter.ViewManagerModel;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.change_password.ChangePasswordOutputData;

/**
 * The Presenter for the Change Password Use Case.
 */
public class ChangePasswordPresenter implements ChangePasswordOutputBoundary {

    private final ChangePasswordViewModel changePasswordViewModel;
    private ViewManagerModel viewManagerModel;

    public ChangePasswordPresenter(ChangePasswordViewModel changePasswordViewModel, ViewManagerModel viewManagerModel) {
        this.changePasswordViewModel = changePasswordViewModel;
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
        final ChangePasswordState currentState = changePasswordViewModel.getState();
        currentState.setPasswordError(error);
        changePasswordViewModel.setState(currentState);
        changePasswordViewModel.firePropertyChanged("passwordError");
    }
}
