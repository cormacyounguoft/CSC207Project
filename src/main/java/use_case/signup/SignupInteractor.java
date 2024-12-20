package use_case.signup;

import entity.User;
import entity.UserFactory;

/**
 * The Signup Interactor.
 */
public class SignupInteractor implements SignupInputBoundary {

    public static final int THREE = 3;
    public static final int EIGHT = 8;
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
        if (signupInputData.getUsername().isEmpty()) {
            userPresenter.prepareFailView("Username cannot be empty.");
        }
        else if (signupInputData.getPassword().isEmpty()) {
            userPresenter.prepareFailView("Password cannot be empty.");
        }
        else if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        }
        else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        }
        else if (validateUsername(signupInputData.getUsername())) {
            userPresenter.prepareFailView("Invalid username. Ensure it contains at least 3 characters and no spaces.");
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

    private boolean validateUsername(String username) {
        return username.length() < THREE || username.contains(" ");
    }

    private boolean validatePassword(String password) {
        return password.length() >= EIGHT
                && password.matches(".*[A-Z].*")
                && password.matches(".*[a-z].*")
                && password.matches(".*\\d.*")
                && password.matches(".*[(){}\\[\\]'\";:><,./?\\\\|\\+=\\-_\\*&^%$#@!~`].*");
    }

    @Override
    public void switchToLoginView() {
        userPresenter.switchToLoginView();
    }
}
