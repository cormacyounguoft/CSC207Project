package use_case.get_rated_list;

/**
 * The output boundary for Get Rate List.
 */
public interface GetRateListOutputBoundary {

    /**
     * Prepare the success view for get rate list.
     * @param getRateListOutputData the output data for get rate list.
     */
    void prepareSuccessView(GetRateListOutputData getRateListOutputData);
}
