package use_case.change_password;

/**
 * The interface of the DAO for the Change Password Use Case.
 */
public interface ChangePasswordUserDataAccessInterface {

    /**
     * Updates the system to record this user's password.
     * @param password the user whose password is to be updated
     */
    void changePassword(String username, String password);
}
