package interface_adapter.to_logged_in_view;

import use_case.to_logged_in_view.ToLoggedInViewInputBoundary;
import use_case.to_logged_in_view.ToLoggedInViewInputData;

/**
 * The Controller for the ToLoggedInView Use Case.
 */
public class ToLoggedInViewController {

    private final ToLoggedInViewInputBoundary toLoggedInViewUseCaseInteractor;

    public ToLoggedInViewController(ToLoggedInViewInputBoundary toLoggedInViewUseCaseInteractor) {
        this.toLoggedInViewUseCaseInteractor = toLoggedInViewUseCaseInteractor;
    }

    /**
     * Takes the user to the logged in view.
     * @param username the username of the User.
     */
    public void toLoggedInView(String username) {
        final ToLoggedInViewInputData toLoggedInViewInputData = new ToLoggedInViewInputData(username);
        toLoggedInViewUseCaseInteractor.toLoggedInView(toLoggedInViewInputData);
    }
}
