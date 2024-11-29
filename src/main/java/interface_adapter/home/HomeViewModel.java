package interface_adapter.home;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Home View.
 */
public class HomeViewModel extends ViewModel<HomeState> {

    public static final String TITLE = "Home View";
    public static final String TO_LOGIN_BUTTON = "Go to Login";
    public static final String TO_SIGNUP_BUTTON = "Go to Signup";
    public static final String TO_SEARCH_BUTTON = "Go to Search";

    public HomeViewModel() {
        super("home");
        setState(new HomeState());
    }
}
