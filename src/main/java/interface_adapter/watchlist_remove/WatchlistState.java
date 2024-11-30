package interface_adapter.watchlist_remove;

import java.util.List;

/**
 * The state for the watchlist remove and export Use Case.
 */
public class WatchlistState {

    private String username;
    private List<String> watchlistTitle;
    private List<String> watchlistUrl;
    private String error;
    private String export;

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

    public List<String> getWatchlistUrl() {
        return watchlistUrl;
    }

    public void setWatchlistUrl(List<String> watchlistUrl) {
        this.watchlistUrl = watchlistUrl;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setExport(String success) {
        this.export = success;
    }

    public String getExport() {
        return export;
    }
}
