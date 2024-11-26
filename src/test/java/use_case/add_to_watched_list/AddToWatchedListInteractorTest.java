package use_case.add_to_watched_list;

import data_access.InMemoryUserDataAccessObject;
import data_access.MovieAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import use_case.SearchDataAccessInterface;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


public class AddToWatchedListInteractorTest {
    @Test
    void successTest() {
        AddToWatchedListInputData inputData = new AddToWatchedListInputData("Username", "Spider-man");


        InMemoryUserDataAccessObject inMemoryUserDataAccessObject = new InMemoryUserDataAccessObject();
        SearchDataAccessInterface searchDataAccessInterface = new MovieAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Username", "Password123!");
        inMemoryUserDataAccessObject.save(user);

        AddToWatchedListOutputBoundary presenter = new AddToWatchedListOutputBoundary() {
            @Override
            public void prepareSuccessView(AddToWatchedListOutputData outputData) {
                assertEquals("Username", outputData.getUsername());
                Assertions.assertTrue(inMemoryUserDataAccessObject.get(outputData.getUsername()).getWatchedList().containsTitle("Spider-Man"));
                Assertions.assertFalse(outputData.isUseCaseFailed());
                assertEquals("Username", outputData.getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

        };

        AddToWatchedListInputBoundary interactor = new AddToWatchedListInteractor(inMemoryUserDataAccessObject, searchDataAccessInterface, presenter);
        interactor.execute(inputData);
    }

    @Test
    void failTest() {
        AddToWatchedListInputData inputData = new AddToWatchedListInputData("Username", "123456");
        InMemoryUserDataAccessObject inMemoryUserDataAccessObject = new InMemoryUserDataAccessObject();
        SearchDataAccessInterface searchDataAccessInterface = new MovieAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Username", "Password123!");
        inMemoryUserDataAccessObject.save(user);

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

        AddToWatchedListInputBoundary interactor = new AddToWatchedListInteractor(inMemoryUserDataAccessObject, searchDataAccessInterface, presenter);
        interactor.execute(inputData);
    }
}
