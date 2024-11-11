package use_case.add_to_watchlist;

/**
 * The add to watchlist Interactor.
 */
public class AddToWatchlistInteractor implements AddToWatchlistInputBoundary {
    private AddToWatchlistDataAccessInterface userDataAccessObject;
    private AddToWatchlistOutputBoundary presenter;

    public AddToWatchlistInteractor(AddToWatchlistDataAccessInterface userDataAccessObject,
                                    AddToWatchlistOutputBoundary presenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.presenter = presenter;
    }

    @Override
    public void execute(AddToWatchlistInputData inputData) {
        userDataAccessObject.saveToWatchlist(inputData.getUsername(), inputData.getMovie());
        final AddToWatchlistOutputData outputData = new AddToWatchlistOutputData(inputData.getUsername(), false);
        presenter.prepareSuccessView(outputData);
    }
}
