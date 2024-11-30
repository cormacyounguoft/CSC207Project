package use_case.get_watched_list;

import java.util.List;

/**
 * Output data for get watched list.
 */
public class GetWatchedListOutputData {

    private final String username;
    private final List<String> watchedListTitle;
    private final List<String> watchedListUrl;
    private final boolean useCaseFailed;

    public GetWatchedListOutputData(String username, List<String> watchedListTitle, List<String> watchedListUrl,
                                    boolean useCaseFailed) {
        this.username = username;
        this.watchedListTitle = watchedListTitle;
        this.watchedListUrl = watchedListUrl;
        this.useCaseFailed = useCaseFailed;
    }

    public List<String> getWatchedListTitle() {
        return watchedListTitle;
    }

    public List<String> getWatchedListUrl() {
        return watchedListUrl;
    }

    public String getUsername() {
        return username;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
