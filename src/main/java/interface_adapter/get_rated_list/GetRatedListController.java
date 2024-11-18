package interface_adapter.get_rated_list;

import use_case.get_rated_list.GetRateListInputBoundary;
import use_case.get_rated_list.GetRateListInputData;

public class GetRatedListController {
    GetRateListInputBoundary interactor;

    public GetRatedListController(GetRateListInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(String username) {
        final GetRateListInputData inputData = new GetRateListInputData(username);
        interactor.execute(inputData);
    }
}
