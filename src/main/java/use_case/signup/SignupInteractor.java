package use_case.signup;

import entity.User;
import entity.UserFactory;

/**
 * The Signup Interactor.
 */
public class SignupInteractor implements SignupInputBoundary {
    private final SignupUserDataAccessInterface userDataAccessObject;
    private final SignupOutputBoundary userPresenter;
    private final UserFactory userFactory;

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        }
        else if (!validateUsername(signupInputData.getUsername())) {
            userPresenter.prepareFailView("Invalid username. Ensure it contains at least 3 characters and no spaces.");
        }
        else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        }
        else if (!validatePassword(signupInputData.getPassword())) {
            userPresenter.prepareFailView("Invalid password. Ensure it is at least 8 characters long, "
                    + "contains an uppercase letter, a lowercase letter, a digit, and a special character.");
        }
        else {
            final User user = userFactory.create(signupInputData.getUsername(), signupInputData.getPassword());
            userDataAccessObject.save(user);

            final SignupOutputData signupOutputData = new SignupOutputData(user.getName(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }

    @Override
    public void switchToLoginView() {
        userPresenter.switchToLoginView();
    }


    @Override
    public void switchToHomeView() {
        userPresenter.switchToHomeView();
    }
    /**
     * Validate the username.
     */
    private boolean validateUsername(String username) {
        return username != null && username.length() >= 3 && !username.contains(" ");
    }

    /**
     * Validate the password.
     */
    private boolean validatePassword(String password) {
        return password != null &&
                password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[a-z].*") &&
                password.matches(".*\\d.*") &&
                password.matches(".*[(){}\\[\\]'\";:><,./?\\\\|\\+=\\-_\\*&^%$#@!~`].*");
    }

}
