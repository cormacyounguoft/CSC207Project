package use_case.add_to_watched_list;

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


public class AddToWatchedListInteractorTest {
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
        AddToWatchedListInputData inputData = new AddToWatchedListInputData("Username", "movie");

        AddToWatchedListOutputBoundary presenter = new AddToWatchedListOutputBoundary() {
            @Override
            public void prepareSuccessView(AddToWatchedListOutputData outputData) {
                assertEquals("Username", outputData.getUsername());
                assertTrue(dataAccessObject.get(outputData.getUsername()).getWatchedList().containsTitle("title"));
                assertFalse(outputData.isUseCaseFailed());
                assertEquals("Username", outputData.getUsername());
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
                assertEquals("Error", error);
            }
        };

        AddToWatchedListInputBoundary interactor = new AddToWatchedListInteractor(dataAccessObject, dataAccessObject, presenter);
        interactor.execute(inputData);
    }
}
