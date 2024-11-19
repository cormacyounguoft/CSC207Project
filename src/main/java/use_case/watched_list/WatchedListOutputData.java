package use_case.watched_list;

import entity.MovieList;

import java.util.List;

public class WatchedListOutputData {
    private final String username;
    private final List<String> watchedListTitle;
    private final List<String> watchedListURL;
    private final boolean useCaseFailed;

    public WatchedListOutputData(String username, List<String> watchedListTitle, List<String> watchedListURL,
                                 boolean useCaseFailed) {
        this.username = username;
        this.watchedListTitle = watchedListTitle;
        this.watchedListURL = watchedListURL;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getWatchedListTitle() {
        return watchedListTitle;
    }

    public List<String> getWatchedListURL() {
        return watchedListURL;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
