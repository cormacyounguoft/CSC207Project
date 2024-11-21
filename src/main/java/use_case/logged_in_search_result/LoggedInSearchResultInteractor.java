package use_case.logged_in_search_result;

/**
 * The Logged In Search Result Interactor.
 */
public class LoggedInSearchResultInteractor implements LoggedInSearchResultInputBoundary {
    private final LoggedInSearchResultOutputBoundary presenter;

    public LoggedInSearchResultInteractor(LoggedInSearchResultOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute(LoggedInSearchResultInputData inputData) {
        final LoggedInSearchResultOutputData outputData = new LoggedInSearchResultOutputData(
                inputData.getUsername(), inputData.getMovie(), false);
        presenter.prepareSuccessView(outputData);
    }
}
