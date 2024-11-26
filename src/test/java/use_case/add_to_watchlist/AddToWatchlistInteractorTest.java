package use_case.add_to_watchlist;

import data_access.InMemoryUserDataAccessObject;
import data_access.MovieAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import use_case.SearchDataAccessInterface;
import use_case.add_to_watched_list.AddToWatchedListInputBoundary;
import use_case.add_to_watched_list.AddToWatchedListInputData;
import use_case.add_to_watched_list.AddToWatchedListInteractor;
import use_case.add_to_watched_list.AddToWatchedListOutputBoundary;
import use_case.add_to_watched_list.AddToWatchedListOutputData;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class AddToWatchlistInteractorTest {
    @Test
    void successTest() {
        AddToWatchlistInputData inputData = new AddToWatchlistInputData("Username", "Spider-man");


        InMemoryUserDataAccessObject inMemoryUserDataAccessObject = new InMemoryUserDataAccessObject();
        SearchDataAccessInterface searchDataAccessInterface = new MovieAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Username", "Password123!");
        inMemoryUserDataAccessObject.save(user);

        AddToWatchlistOutputBoundary presenter = new AddToWatchlistOutputBoundary() {
            @Override
            public void prepareSuccessView(AddToWatchlistOutputData outputData) {
                assertEquals("Username", outputData.getUsername());
                Assertions.assertTrue(inMemoryUserDataAccessObject.get(outputData.getUsername()).getWatchList().containsTitle("Spider-Man"));
                Assertions.assertFalse(outputData.isUseCaseFailed());
                assertEquals("Username", outputData.getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

        };

        AddToWatchlistInputBoundary interactor = new AddToWatchlistInteractor(inMemoryUserDataAccessObject, searchDataAccessInterface, presenter);
        interactor.execute(inputData);
    }

    @Test
    void failTest() {
        AddToWatchlistInputData inputData = new AddToWatchlistInputData("Username", "123456");
        InMemoryUserDataAccessObject inMemoryUserDataAccessObject = new InMemoryUserDataAccessObject();
        SearchDataAccessInterface searchDataAccessInterface = new MovieAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Username", "Password123!");
        inMemoryUserDataAccessObject.save(user);

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

        AddToWatchlistInputBoundary interactor = new AddToWatchlistInteractor(inMemoryUserDataAccessObject, searchDataAccessInterface, presenter);
        interactor.execute(inputData);
    }
}
