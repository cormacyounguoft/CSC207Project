package interface_adapter.watched_list_remove;

import java.util.List;

/**
 * The state for the watched list remove Use Case.
 */
public class WatchedListRemoveState {

    private String username;
    private List<String> watchedListTitle;
    private List<String> watchedListUrl;
    private String error;
    private String export;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getWatchedListTitle() {
        return watchedListTitle;
    }

    public void setWatchedListTitle(List<String> watchedListTitle) {
        this.watchedListTitle = watchedListTitle;
    }

    public List<String> getWatchedListUrl() {
        return watchedListUrl;
    }

    public void setWatchedListUrl(List<String> watchedListUrl) {
        this.watchedListUrl = watchedListUrl;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getExport() {
        return export;
    }

    public void setExport(String export) {
        this.export = export;
    }
}
