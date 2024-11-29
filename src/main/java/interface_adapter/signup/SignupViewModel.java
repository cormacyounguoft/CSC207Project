package interface_adapter.signup;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Signup View.
 */
public class SignupViewModel extends ViewModel<SignupState> {

    public static final String TITLE = "Sign Up View";
    public static final String USERNAME = "Choose username";
    public static final String PASSWORD = "Choose password";
    public static final String REPEAT_PASSWORD = "Enter password again";
    public static final String SIGNUP_BUTTON = "Sign up";
    public static final String CANCEL_BUTTON = "Cancel";
    public static final String TO_LOGIN_BUTTON = "Go to Login";

    public SignupViewModel() {
        super("sign up");
        setState(new SignupState());
    }
}
