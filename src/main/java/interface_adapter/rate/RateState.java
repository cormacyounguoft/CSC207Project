package interface_adapter.rate;

/**
 * The state of the Rate Use Case.
 */
public class RateState {

    private String username = "";
    private String title;
    private int rate;
    private String rateError;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getRateError() {
        return rateError;
    }

    public void setRateError(String rateError) {
        this.rateError = rateError;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
