package use_case.rated_list;

import java.util.Map;

public class RatedListInputData {
    private final String username;
    private final String title;

    public RatedListInputData(String username, String title) {
        this.username = username;
        this.title = title;
    }

    public String getUsername(){
        return this.username;
    }


    public String getTitle() {
        return title;
    }
}
