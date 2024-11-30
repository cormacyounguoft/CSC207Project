package interface_adapter.get_rated_list;

import use_case.get_rated_list.GetRateListInputBoundary;
import use_case.get_rated_list.GetRateListInputData;

/**
 * The controller for the get rated list use case.
 */
public class GetRatedListController {

    private final GetRateListInputBoundary interactor;

    public GetRatedListController(GetRateListInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Executes the get rated list Controller.
     * @param username username of the User.
     */
    public void execute(String username) {
        final GetRateListInputData inputData = new GetRateListInputData(username);
        interactor.execute(inputData);
    }
}
