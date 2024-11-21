package use_case.change_password;

import entity.User;
import entity.UserFactory;
import use_case.watched_list.WatchedListInputData;
import use_case.watched_list.WatchedListOutputData;

/**
 * The Change Password Interactor.
 */
public class ChangePasswordInteractor implements ChangePasswordInputBoundary {
    private final ChangePasswordUserDataAccessInterface userDataAccessObject;
    private final ChangePasswordOutputBoundary userPresenter;
    private final UserFactory userFactory;

    public ChangePasswordInteractor(ChangePasswordUserDataAccessInterface changePasswordDataAccessInterface,
                                    ChangePasswordOutputBoundary changePasswordOutputBoundary,
                                    UserFactory userFactory) {
        this.userDataAccessObject = changePasswordDataAccessInterface;
        this.userPresenter = changePasswordOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(ChangePasswordInputData changePasswordInputData) {
        if (!validatePassword(changePasswordInputData.getPassword())) {
            userPresenter.prepareFailView("Invalid password. Ensure it is at least 8 characters long, "
                    + "contains an uppercase letter, a lowercase letter, a digit, and a special character.");
            return;
        }
        final User user = userFactory.create(changePasswordInputData.getUsername(),
                                             changePasswordInputData.getPassword());
        userDataAccessObject.changePassword(user);

        final ChangePasswordOutputData changePasswordOutputData = new ChangePasswordOutputData(user.getName(),
                                                                                  false);
        userPresenter.prepareSuccessView(changePasswordOutputData);
    }

    @Override
    public void switchToLoggedInView(ChangePasswordInputData inputData) {
        final ChangePasswordOutputData outputData = new ChangePasswordOutputData(inputData.getUsername(), false);
        userPresenter.switchToLoggedInView(outputData);
    }

    private boolean validatePassword(String password) {
        return password != null &&
                password.length() >= 8 &&
                password.matches(".*[A-Z].*") && // Contains at least one uppercase letter
                password.matches(".*[a-z].*") && // Contains at least one lowercase letter
                password.matches(".*\\d.*") &&   // Contains at least one digit
                password.matches(".*[(){}\\[\\]'\";:><,./?\\\\|\\+=\\-_\\*&^%$#@!~`].*"); // Contains at least one special character
    }
}
