package use_case.logged_in_search;

import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.MockDataAccessObject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

class LoggedInSearchInteractorTest {
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
        LoggedInSearchInputData inputData = new LoggedInSearchInputData("Username", "movie");

        LoggedInSearchOutputBoundary successPresenter = new LoggedInSearchOutputBoundary() {
            @Override
            public void prepareSuccessView(LoggedInSearchOutputData outputData) {
                assertEquals("title", outputData.getTitle());
                assertEquals("date", outputData.getReleaseDate());
                assertEquals("description", outputData.getDescription());
                assertEquals("0", outputData.getRottenTomatoes());
                assertEquals("0", outputData.getRuntime());
                assertEquals("[genre]", outputData.getGenre());
                assertEquals("[actor]", outputData.getActors());
                assertEquals("[director]", outputData.getDirector());
                assertEquals("url", outputData.getPosterLink());
                assertEquals("Username", outputData.getUsername());
                assertFalse(outputData.isUseCaseFailed());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        LoggedInSearchInputBoundary interactor = new LoggedInSearchInteractor(successPresenter, dataAccessObject);
        interactor.execute(inputData);
    }

    @Test
    void failTest() {
        LoggedInSearchInputData inputData = new LoggedInSearchInputData("Username", "movie not found");

        LoggedInSearchOutputBoundary successPresenter = new LoggedInSearchOutputBoundary() {
            @Override
            public void prepareSuccessView(LoggedInSearchOutputData outputData) {
                fail("Use case failure is expected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Movie not found!", error);
            }
        };

        LoggedInSearchInputBoundary interactor = new LoggedInSearchInteractor(successPresenter, dataAccessObject);
        interactor.execute(inputData);
    }

    @Test
    void successTestEmpty() {
        LoggedInSearchInputData inputData = new LoggedInSearchInputData("Username", "blank");

        LoggedInSearchOutputBoundary successPresenter = new LoggedInSearchOutputBoundary() {
            @Override
            public void prepareSuccessView(LoggedInSearchOutputData outputData) {
                assertEquals("title", outputData.getTitle());
                assertEquals("Release date not available.", outputData.getReleaseDate());
                assertEquals("Description not available.", outputData.getDescription());
                assertEquals("Rotten Tomatoes not available.", outputData.getRottenTomatoes());
                assertEquals("Runtime not available.", outputData.getRuntime());
                assertEquals("Genres not available.", outputData.getGenre());
                assertEquals("Actors not available.", outputData.getActors());
                assertEquals("Directors not available.", outputData.getDirector());
                assertEquals("Poster not available.", outputData.getPosterLink());
                assertEquals("Username", outputData.getUsername());
                assertFalse(outputData.isUseCaseFailed());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        LoggedInSearchInputBoundary interactor = new LoggedInSearchInteractor(successPresenter, dataAccessObject);
        interactor.execute(inputData);
    }
}