package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeViewModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final HomeViewModel homeViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoggedInViewModel loggedInViewModel,
                          LoginViewModel loginViewModel,
                          HomeViewModel homeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
        this.homeViewModel = homeViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.

        final LoggedInState changePasswordState = loggedInViewModel.getState();
        changePasswordState.setUsername(response.getUsername());
        this.loggedInViewModel.setState(changePasswordState);
        this.loggedInViewModel.firePropertyChanged();

        this.viewManagerModel.setState(loggedInViewModel.getViewName());
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
