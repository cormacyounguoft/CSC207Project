package use_case.watched_list;

import entity.MovieList;

public class WatchedListOutputData {
    private final String username;
    private final MovieList watchedList;
    private final boolean useCaseFailed;

    public WatchedListOutputData(String username, MovieList watchedList, boolean useCaseFailed) {
        this.username = username;
        this.watchedList = watchedList;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public MovieList getWatchedList() {
        return watchedList;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
