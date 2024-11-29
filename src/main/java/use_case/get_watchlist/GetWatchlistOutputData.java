package use_case.get_watchlist;

import java.util.List;

/**
 * Output data for get watch list.
 */
public class GetWatchlistOutputData {

    private final String username;
    private final List<String> watchlistTitle;
    private final List<String> watchlistUrl;
    private final boolean useCaseFailed;

    public GetWatchlistOutputData(String username, List<String> watchlistTitle, List<String> watchlistUrl,
                                  boolean useCaseFailed) {
        this.username = username;
        this.watchlistTitle = watchlistTitle;
        this.watchlistUrl = watchlistUrl;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getWatchlistTitle() {
        return watchlistTitle;
    }

    public List<String> getWatchlistUrl() {
        return watchlistUrl;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
