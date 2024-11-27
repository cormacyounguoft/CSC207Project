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
        userDataAccessObject.changePassword(changePasswordInputData.getUsername(),
                changePasswordInputData.getPassword());

        final ChangePasswordOutputData changePasswordOutputData = new ChangePasswordOutputData(user.getName(),
                false);
        userPresenter.prepareSuccessView(changePasswordOutputData);
    }

    private boolean validatePassword(String password) {
        return password != null &&
                password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[a-z].*") &&
                password.matches(".*\\d.*") &&
                password.matches(".*[(){}\\[\\]'\";:><,./?\\\\|\\+=\\-_\\*&^%$#@!~`].*");
    }
}
