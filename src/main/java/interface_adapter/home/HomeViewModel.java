package interface_adapter.home;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Home View.
 */
public class HomeViewModel extends ViewModel<HomeState> {
    public static final String TITLE_LABEL = "Home View";

    public static final String TO_LOGIN_BUTTON_LABEL = "Go to Login";
    public static final String TO_SIGNUP_BUTTON_LABEL = "Go to Signup";
    public static final String TO_SEARCH_BUTTON_LABEL = "Go to Search";

    public HomeViewModel() {
        super("home");
        setState(new HomeState());
    }
}
