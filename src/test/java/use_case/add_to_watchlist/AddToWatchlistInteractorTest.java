package use_case.add_to_watchlist;

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


public class AddToWatchlistInteractorTest {
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
        AddToWatchlistInputData inputData = new AddToWatchlistInputData("Username", "movie");

        AddToWatchlistOutputBoundary presenter = new AddToWatchlistOutputBoundary() {
            @Override
            public void prepareSuccessView(AddToWatchlistOutputData outputData) {
                Assertions.assertEquals("Username", outputData.getUsername());
                Assertions.assertTrue(dataAccessObject.get(outputData.getUsername()).getWatchList().containsTitle("title"));
                Assertions.assertFalse(outputData.isUseCaseFailed());
                Assertions.assertEquals("Username", outputData.getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        AddToWatchlistInputBoundary interactor = new AddToWatchlistInteractor(dataAccessObject, dataAccessObject, presenter);
        interactor.execute(inputData);
    }

    @Test
    void failTest() {
        AddToWatchlistInputData inputData = new AddToWatchlistInputData("Username", "movie not found");

        AddToWatchlistOutputBoundary presenter = new AddToWatchlistOutputBoundary() {
            @Override
            public void prepareSuccessView(AddToWatchlistOutputData outputData) {
                fail("Use case failure is expected.");
            }

            @Override
            public void prepareFailView(String error) {
                Assertions.assertEquals("Error", error);
            }
        };

        AddToWatchlistInputBoundary interactor = new AddToWatchlistInteractor(dataAccessObject, dataAccessObject, presenter);
        interactor.execute(inputData);
    }
}
