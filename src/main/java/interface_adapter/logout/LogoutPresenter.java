package interface_adapter.logout;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.ChangePasswordState;
import interface_adapter.change_password.ChangePasswordViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.logout.LogoutOutputBoundary;
import use_case.logout.LogoutOutputData;

/**
 * The Presenter for the Logout Use Case.
 */
public class LogoutPresenter implements LogoutOutputBoundary {

    private ChangePasswordViewModel changePasswordViewModel;
    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;
    private SignupViewModel signupViewModel;

    public LogoutPresenter(ViewManagerModel viewManagerModel,
                          ChangePasswordViewModel changePasswordViewModel,
                           LoginViewModel loginViewModel, SignupViewModel signupState) {
        this.changePasswordViewModel = changePasswordViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupState;
    }

    @Override
    public void prepareSuccessView(LogoutOutputData response) {
        // We need to switch to the login view, which should have
        // an empty username and password.

        // We also need to set the username in the LoggedInState to
        // the empty string.

        // 1. get the LoggedInState out of the appropriate View Model,
        final ChangePasswordState changePasswordState = changePasswordViewModel.getState();
        // 2. set the username in the state to the empty string
        changePasswordState.setUsername("");
        // 3. set the state in the LoggedInViewModel to the updated state
        changePasswordViewModel.setState(changePasswordState);
        // 4. firePropertyChanged so that the View that is listening is updated.
        changePasswordViewModel.firePropertyChanged();

        // 5. get the LoginState out of the appropriate View Model,
        final LoginState loginState = loginViewModel.getState();
        // 6. set the username and password in the state to the empty string
        loginState.setUsername("");
        loginState.setPassword("");
        // 7. set the state in the LoginViewModel to the updated state
        loginViewModel.setState(loginState);
        // 8. firePropertyChanged so that the View that is listening is updated.
        loginViewModel.firePropertyChanged();

        final SignupState signupState = signupViewModel.getState();
        signupState.setPassword("");
        signupState.setRepeatPassword("");
        signupState.setUsername("");
        signupViewModel.setState(signupState);
        signupViewModel.firePropertyChanged();

        // This code tells the View Manager to switch to the LoginView.
        this.viewManagerModel.setState(loginViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        // No need to add code here. We'll assume that logout can't fail.
        // Thought question: is this a reasonable assumption?
    }
}
