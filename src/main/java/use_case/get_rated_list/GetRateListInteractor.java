package use_case.get_rated_list;

import java.util.List;
import java.util.Map;

/**
 * The interactor for get rate list.
 */
public class GetRateListInteractor implements GetRateListInputBoundary {
    private final GetRatedListDataAccessInterface dataAccessInterface;
    private final GetRateListOutputBoundary presenter;

    public GetRateListInteractor(GetRatedListDataAccessInterface dataAccessInterface, GetRateListOutputBoundary
            presenter) {
        this.dataAccessInterface = dataAccessInterface;
        this.presenter = presenter;
    }

    @Override
    public void execute(GetRateListInputData getRateListInputData) {
        final Map<String, List<String>> rating = dataAccessInterface.getUserRating(getRateListInputData.getUsername());
        final GetRateListOutputData outputData = new GetRateListOutputData(getRateListInputData.getUsername(), rating,
                false);
        presenter.prepareSuccessView(outputData);
    }
}
