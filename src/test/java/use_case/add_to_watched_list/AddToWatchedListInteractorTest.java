package use_case.add_to_watched_list;

import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.MockDataAccessObject;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class AddToWatchedListInteractorTest {
    MockDataAccessObject dataAccessObject;

    @BeforeEach
    void setUp() {
        dataAccessObject = new MockDataAccessObject();
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Username", "Password123!");
        dataAccessObject.save(user);
    }

    @AfterEach
    void tearDown() {
        dataAccessObject.clear();
    }

    @Test
    void successTest() {
        AddToWatchedListInputData inputData = new AddToWatchedListInputData("Username", "movie");

        AddToWatchedListOutputBoundary presenter = new AddToWatchedListOutputBoundary() {
            @Override
            public void prepareSuccessView(AddToWatchedListOutputData outputData) {
                Assertions.assertEquals("Username", outputData.getUsername());
                Assertions.assertTrue(dataAccessObject.get(outputData.getUsername()).getWatchedList().containsTitle("title"));
                Assertions.assertFalse(outputData.isUseCaseFailed());
                Assertions.assertEquals("Username", outputData.getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        AddToWatchedListInputBoundary interactor = new AddToWatchedListInteractor(dataAccessObject, dataAccessObject, presenter);
        interactor.execute(inputData);
    }

    @Test
    void failTest() {
        AddToWatchedListInputData inputData = new AddToWatchedListInputData("Username", "movie not found");

        AddToWatchedListOutputBoundary presenter = new AddToWatchedListOutputBoundary() {
            @Override
            public void prepareSuccessView(AddToWatchedListOutputData outputData) {
                fail("Use case failure is expected.");
            }

            @Override
            public void prepareFailView(String error) {
                Assertions.assertEquals("Error", error);
            }
        };

        AddToWatchedListInputBoundary interactor = new AddToWatchedListInteractor(dataAccessObject, dataAccessObject, presenter);
        interactor.execute(inputData);
    }
}
