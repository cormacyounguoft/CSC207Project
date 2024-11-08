package use_case.add_to_watched_list;

/**
 * The add to watchlist Interactor.
 */
public class AddToWatchedListInteractor implements AddToWatchedListInputBoundary {
    private AddToWatchedListDataAccessInterface userDataAccessObject;
    private AddToWatchedListOutputBoundary presenter;

    public AddToWatchedListInteractor(AddToWatchedListDataAccessInterface userDataAccessObject,
                                      AddToWatchedListOutputBoundary presenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.presenter = presenter;
    }

    @Override
    public void execute(AddToWatchedListInputData inputData) {
        userDataAccessObject.saveToWatchedList(inputData.getUsername(), inputData.getMovie());
        final AddToWatchedListOutputData outputData = new AddToWatchedListOutputData(inputData.getUsername(), false);
        presenter.prepareSuccessView(outputData);
    }
}
