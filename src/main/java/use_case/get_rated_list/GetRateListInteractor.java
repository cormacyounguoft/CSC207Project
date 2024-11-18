package use_case.get_rated_list;

import entity.UserRating;

import java.util.Map;

public class GetRateListInteractor implements GetRateListInputBoundary {
    private final GetRatedListDataAccessInterface dataAccessInterface;
    private final GetRateListOutputBoundary presenter;

    public GetRateListInteractor(GetRatedListDataAccessInterface dataAccessInterface, GetRateListOutputBoundary presenter) {
        this.dataAccessInterface = dataAccessInterface;
        this.presenter = presenter;
    }

    @Override
    public void execute(GetRateListInputData getRateListInputData) {
        final Map<String, Integer> rating = dataAccessInterface.getUserRating(getRateListInputData.getUsername()).getMovieToRating();
        GetRateListOutputData outputData = new GetRateListOutputData(getRateListInputData.getUsername(), rating, false);
        presenter.prepareSuccessView(outputData);
    }
}
