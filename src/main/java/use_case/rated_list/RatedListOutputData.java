package use_case.rated_list;

import java.util.List;
import java.util.Map;

public class RatedListOutputData {
    private final String username;


    public RatedListOutputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
