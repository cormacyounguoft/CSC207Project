package use_case.get_watched_list;

import entity.MovieList;

public class GetWatchedListInteractor implements GetWatchedListInputBoundary {
    private final GetWatchedListOutputBoundary presenter;
    private final GetWatchedListDataAccessInterface dataAccess;

    public GetWatchedListInteractor(GetWatchedListOutputBoundary presenter, GetWatchedListDataAccessInterface dataAccess) {
        this.presenter = presenter;
        this.dataAccess = dataAccess;
    }


    @Override
    public void execute(GetWatchedListInputData inputData) {
        final MovieList watchedList = dataAccess.getWatchedList(inputData.getUsername());
        final GetWatchedListOutputData outputData = new GetWatchedListOutputData(inputData.getUsername(),
                watchedList, false);
        presenter.prepareSuccessView(outputData);
    }
}
