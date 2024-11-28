package use_case.change_password;

import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.MockDataAccessObject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

public class ChangePasswordInteractorTest {
    MockDataAccessObject dataAccessObject;

    @BeforeEach
    void setUp() {
        dataAccessObject = new MockDataAccessObject();
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Username", "Password123!");
        dataAccessObject.save(user);
    }

    @Test
    void successTest() {
        ChangePasswordInputData inputData = new ChangePasswordInputData("Password1234!", "Username");

        ChangePasswordOutputBoundary presenter = new ChangePasswordOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangePasswordOutputData outputData) {
                assertFalse(outputData.isUseCaseFailed());
                assertEquals("Username", outputData.getUsername());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");

            }
        };
        ChangePasswordInputBoundary interactor = new ChangePasswordInteractor(dataAccessObject, presenter, new CommonUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void failurePasswordMissingTest() {
        ChangePasswordInputData inputData = new ChangePasswordInputData(null, "Username");

        ChangePasswordOutputBoundary presenter = new ChangePasswordOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangePasswordOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Invalid password. Ensure it is at least 8 characters long, "
                        + "contains an uppercase letter, a lowercase letter, a digit, and a special character.", errorMessage);

            }
        };
        ChangePasswordInputBoundary interactor = new ChangePasswordInteractor(dataAccessObject, presenter, new CommonUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void failureBadPasswordTest() {
        ChangePasswordInputData inputData = new ChangePasswordInputData("Pass", "Username");

        ChangePasswordOutputBoundary presenter = new ChangePasswordOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangePasswordOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Invalid password. Ensure it is at least 8 characters long, "
                        + "contains an uppercase letter, a lowercase letter, a digit, and a special character.", errorMessage);

            }
        };
        ChangePasswordInputBoundary interactor = new ChangePasswordInteractor(dataAccessObject, presenter, new CommonUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void failureBadPasswordTest1() {
        ChangePasswordInputData inputData = new ChangePasswordInputData("password!", "Username");

        ChangePasswordOutputBoundary presenter = new ChangePasswordOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangePasswordOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Invalid password. Ensure it is at least 8 characters long, "
                        + "contains an uppercase letter, a lowercase letter, a digit, and a special character.", errorMessage);

            }
        };
        ChangePasswordInputBoundary interactor = new ChangePasswordInteractor(dataAccessObject, presenter, new CommonUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void failureBadPasswordTest2() {
        ChangePasswordInputData inputData = new ChangePasswordInputData("PASSWORD1!", "Username");

        ChangePasswordOutputBoundary presenter = new ChangePasswordOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangePasswordOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Invalid password. Ensure it is at least 8 characters long, "
                        + "contains an uppercase letter, a lowercase letter, a digit, and a special character.", errorMessage);

            }
        };
        ChangePasswordInputBoundary interactor = new ChangePasswordInteractor(dataAccessObject, presenter, new CommonUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void failureBadPasswordTest3() {
        ChangePasswordInputData inputData = new ChangePasswordInputData("Password!", "Username");

        ChangePasswordOutputBoundary presenter = new ChangePasswordOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangePasswordOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Invalid password. Ensure it is at least 8 characters long, "
                        + "contains an uppercase letter, a lowercase letter, a digit, and a special character.", errorMessage);

            }
        };
        ChangePasswordInputBoundary interactor = new ChangePasswordInteractor(dataAccessObject, presenter, new CommonUserFactory());
        interactor.execute(inputData);
    }
}
