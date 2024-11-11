package use_case.get_watched_list;

import entity.MovieList;

public class GetWatchedListOutputData {
    private final String username;
    private final MovieList watchedList;
    private final boolean useCaseFailed;

    public GetWatchedListOutputData(String username, MovieList watchedList, boolean useCaseFailed) {
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
