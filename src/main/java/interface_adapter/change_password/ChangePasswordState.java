package interface_adapter.change_password;

/**
 * The State information representing the logged-in user.
 */
public class ChangePasswordState {
    private String username = "";

    private String password = "";
    private String passwordError;

    public ChangePasswordState(ChangePasswordState copy) {
        username = copy.username;
        password = copy.password;
        passwordError = copy.passwordError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public ChangePasswordState() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordError() {return passwordError;}
}
