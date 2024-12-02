package use_case.add_to_watchlist;

import entity.CommonUserFactory;
import entity.Movie;
import entity.MovieFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.MockDataAccessObject;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


public class AddToWatchlistInteractorTest {
    MockDataAccessObject dataAccessObject;

    @BeforeEach
    void setUp() throws IOException {
        dataAccessObject = new MockDataAccessObject();
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Username", "Password123!");
        dataAccessObject.save(user);

        dataAccessObject.saveToWatchlist("Username", dataAccessObject.search("movie"));
    }

    @Test
    void successTest() {
        AddToWatchlistInputData inputData = new AddToWatchlistInputData("Username", "movie");

        AddToWatchlistOutputBoundary presenter = new AddToWatchlistOutputBoundary() {
            @Override
            public void prepareSuccessView(AddToWatchlistOutputData outputData) {
                assertEquals("Username", outputData.getUsername());
                assertTrue(dataAccessObject.get(outputData.getUsername()).getWatchList().containsTitle("title"));
                assertFalse(outputData.isUseCaseFailed());
                assertEquals("Username", outputData.getUsername());
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
                assertEquals("Error", error);
            }
        };

        AddToWatchlistInputBoundary interactor = new AddToWatchlistInteractor(dataAccessObject, dataAccessObject, presenter);
        interactor.execute(inputData);
    }
}
