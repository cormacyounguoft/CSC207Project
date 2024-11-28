package use_case.get_rated_list;

/**
 * The input boundary interface for the Get Rate List.
 */
public interface GetRateListInputBoundary {

    /**
     * Executes getting the rate list.
     * @param getRateListInputData The input data for get rate list.
     */
    void execute(GetRateListInputData getRateListInputData);
}
