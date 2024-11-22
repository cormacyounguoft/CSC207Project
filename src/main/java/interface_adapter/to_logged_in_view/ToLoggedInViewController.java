package interface_adapter.to_logged_in_view;

import use_case.login.LoginInputBoundary;
import use_case.to_logged_in_view.ToLoggedInViewInputBoundary;
import use_case.to_logged_in_view.ToLoggedInViewInputData;

public class ToLoggedInViewController {

    private final ToLoggedInViewInputBoundary toLoggedInViewUseCaseInteractor;

    public ToLoggedInViewController(ToLoggedInViewInputBoundary toLoggedInViewUseCaseInteractor) {
        this.toLoggedInViewUseCaseInteractor = toLoggedInViewUseCaseInteractor;
    }

    public void toLoggedInView(String username) {
        final ToLoggedInViewInputData toLoggedInViewInputData = new ToLoggedInViewInputData(username);
        toLoggedInViewUseCaseInteractor.toLoggedInView(toLoggedInViewInputData);
    }
}
