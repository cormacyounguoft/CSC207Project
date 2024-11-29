package interface_adapter.watchlist_remove;

import java.util.List;

public class WatchlistRemoveState {
    private String username;
    private List<String> watchlistTitle;
    private List<String> watchlistURL;
    private String error = null;
    private String success = null;

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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setExport(String success) {
        this.success = success;
    }

    public String getExport() {
        return success;
    }
}
