package use_case.signup;

import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.MockDataAccessObject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class SignupInteractorTest {
    MockDataAccessObject dataAccessObject;

    @BeforeEach
    void setUp() {
        dataAccessObject = new MockDataAccessObject();
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("UsernameThatExistsAlready", "Password1234!");
        dataAccessObject.save(user);
    }

    @Test
    void successTest() {
        SignupInputData inputData = new SignupInputData("Paul", "Password123!", "Password123!");

        // This creates a successPresenter that tests whether the test case is as we expect.
        SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                assertEquals("Paul", user.getUsername());
                assertTrue(dataAccessObject.existsByName("Paul"));
                assertFalse(user.isUseCaseFailed());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToLoginView() {
                // This is expected
            }

        };

        SignupInputBoundary interactor = new SignupInteractor(dataAccessObject, successPresenter, new CommonUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void failurePasswordMismatchTest() {
        SignupInputData inputData = new SignupInputData("Paul", "Password123!", "wrong");

        // This creates a presenter that tests whether the test case is as we expect.
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Passwords don't match.", error);
            }

            @Override
            public void switchToLoginView() {
                // This is expected
            }

        };

        SignupInputBoundary interactor = new SignupInteractor(dataAccessObject, failurePresenter, new CommonUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void failurePasswordMissingTest() {
        SignupInputData inputData = new SignupInputData("User", "", "");

        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Password cannot be empty.", error);
            }

            @Override
            public void switchToLoginView() {
            }

        };

        SignupInputBoundary interactor = new SignupInteractor(dataAccessObject, failurePresenter, new CommonUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void failureUsernameMissingTest() {
        SignupInputData inputData = new SignupInputData("", "Password123!", "Password123!");

        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Username cannot be empty.", error);
            }

            @Override
            public void switchToLoginView() {
            }

        };

        SignupInputBoundary interactor = new SignupInteractor(dataAccessObject, failurePresenter, new CommonUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void failureEmptyUsernameTest() {
        SignupInputData inputData = new SignupInputData(" ", "Password123!", "Password123!");

        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Invalid username. Ensure it contains at least 3 characters and no spaces.", error);
            }

            @Override
            public void switchToLoginView() {
            }

        };

        SignupInputBoundary interactor = new SignupInteractor(dataAccessObject, failurePresenter, new CommonUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void failureUserExistsTest() {
        SignupInputData inputData = new SignupInputData("UsernameThatExistsAlready", "Password123!", "wrong");


        // This creates a presenter that tests whether the test case is as we expect.
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("User already exists.", error);
            }

            @Override
            public void switchToLoginView() {
                // This is expected
            }

        };

        SignupInputBoundary interactor = new SignupInteractor(dataAccessObject, failurePresenter, new CommonUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void failureBadUsernameTest() {
        SignupInputData inputData = new SignupInputData("ab", "Password123!", "Password123!");

        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Invalid username. Ensure it contains at least 3 characters and no spaces.", error);
            }

            @Override
            public void switchToLoginView() {
            }

        };

        SignupInputBoundary interactor = new SignupInteractor(dataAccessObject, failurePresenter, new CommonUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void failureBadPasswordTest() {
        SignupInputData inputData = new SignupInputData("Username", "pass", "pass");

        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Invalid password. Ensure it is at least 8 characters long, contains an uppercase letter, a lowercase letter, a digit, and a special character.", error);
            }

            @Override
            public void switchToLoginView() {
            }

        };

        SignupInputBoundary interactor = new SignupInteractor(dataAccessObject, failurePresenter, new CommonUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void failureBadPasswordTest1() {
        SignupInputData inputData = new SignupInputData("Username", "Password!", "Password!");

        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Invalid password. Ensure it is at least 8 characters long, contains an uppercase letter, a lowercase letter, a digit, and a special character.", error);
            }

            @Override
            public void switchToLoginView() {
            }

        };

        SignupInputBoundary interactor = new SignupInteractor(dataAccessObject, failurePresenter, new CommonUserFactory());
        interactor.execute(inputData);
    }


    @Test
    void failureBadPasswordTest2() {
        SignupInputData inputData = new SignupInputData("Username", "password!1", "password!1");

        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Invalid password. Ensure it is at least 8 characters long, contains an uppercase letter, a lowercase letter, a digit, and a special character.", error);
            }

            @Override
            public void switchToLoginView() {
            }

        };

        SignupInputBoundary interactor = new SignupInteractor(dataAccessObject, failurePresenter, new CommonUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void failureBadPasswordTest3() {
        SignupInputData inputData = new SignupInputData("Username", "PASSWORD!1", "PASSWORD!1");

        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Invalid password. Ensure it is at least 8 characters long, contains an uppercase letter, a lowercase letter, a digit, and a special character.", error);
            }

            @Override
            public void switchToLoginView() {
            }

        };

        SignupInputBoundary interactor = new SignupInteractor(dataAccessObject, failurePresenter, new CommonUserFactory());
        interactor.execute(inputData);
    }





    @Test
    void toLoginView() {

        // This creates a successPresenter that tests whether the test case is as we expect.
        SignupOutputBoundary toLoginPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToLoginView() {
                assertTrue(true);
            }

        };
        SignupInputBoundary interactor = new SignupInteractor(dataAccessObject, toLoginPresenter, new CommonUserFactory());
        interactor.switchToLoginView();
    }
}