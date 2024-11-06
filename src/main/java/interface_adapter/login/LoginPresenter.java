package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.ChangePasswordState;
import interface_adapter.change_password.ChangePasswordViewModel;
import interface_adapter.home.HomeViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final ChangePasswordViewModel changePasswordViewModel;
    private final HomeViewModel homeViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          ChangePasswordViewModel changePasswordViewModel,
                          LoginViewModel loginViewModel,
                          HomeViewModel homeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.changePasswordViewModel = changePasswordViewModel;
        this.loginViewModel = loginViewModel;
        this.homeViewModel = homeViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.

        final ChangePasswordState changePasswordState = changePasswordViewModel.getState();
        changePasswordState.setUsername(response.getUsername());
        this.changePasswordViewModel.setState(changePasswordState);
        this.changePasswordViewModel.firePropertyChanged();

        this.viewManagerModel.setState(changePasswordViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }

    @Override
    public void switchToHomeView() {
        viewManagerModel.setState(homeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
