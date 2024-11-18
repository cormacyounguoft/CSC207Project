package use_case.get_rated_list;

public class GetRateListInputData {
    private final String username;

    public GetRateListInputData(String username) {
        this.username = username;
    }
    public String getUsername(){
        return this.username;
    }
}
