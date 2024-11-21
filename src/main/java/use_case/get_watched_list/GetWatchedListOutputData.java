package use_case.get_watched_list;

import entity.MovieList;

import java.util.List;

public class GetWatchedListOutputData {
    private final String username;
    private final List<String> watchedListTitle;
    private final List<String> watchedListURL;
    private final boolean useCaseFailed;

    public GetWatchedListOutputData(String username, List<String> watchedListTitle, List<String> watchedListURL, boolean useCaseFailed) {
        this.username = username;
        this.watchedListTitle = watchedListTitle;
        this.watchedListURL = watchedListURL;
        this.useCaseFailed = useCaseFailed;
    }

    public List<String> getWatchedListTitle() {
        return watchedListTitle;
    }

    public List<String> getWatchedListURL() {
        return watchedListURL;
    }

    public String getUsername() {
        return username;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
