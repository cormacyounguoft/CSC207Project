package interface_adapter.watchlist_remove;

import java.util.List;

public class WatchlistRemoveState {
    private String username;
    private List<String> watchlistTitle;
    private List<String> watchlistURL;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getWatchlistTitle() {
        return watchlistTitle;
    }

    public void setWatchlistTitle(List<String> watchlistTitle) {
        this.watchlistTitle = watchlistTitle;
    }

    public List<String> getWatchlistURL() {
        return watchlistURL;
    }

    public void setWatchlistURL(List<String> watchlistURL) {
        this.watchlistURL = watchlistURL;
    }
}
