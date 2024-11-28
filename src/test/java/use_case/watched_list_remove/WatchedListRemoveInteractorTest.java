package use_case.watched_list_remove;

import entity.CommonUserFactory;
import entity.Movie;
import entity.MovieFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.MockDataAccessObject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class WatchedListRemoveInteractorTest {
    MockDataAccessObject dataAccessObject;

    @BeforeEach
    void setUp() {
        dataAccessObject = new MockDataAccessObject();
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Username", "Password123!");
        dataAccessObject.save(user);

        MovieFactory movieFactory = new MovieFactory();
        Movie movie = movieFactory.create();
        movie.setTitle("Movie");
        movie.setPosterLink("url");
        dataAccessObject.saveToWatchedList("Username", movie);
    }

    @Test
    void successTest() {
        WatchedListRemoveInputData inputData = new WatchedListRemoveInputData("Username", "Movie");

        WatchedListRemoveOutputBoundary presenter = new WatchedListRemoveOutputBoundary() {
            @Override
            public void prepareSuccessView(WatchedListRemoveOutputData outputData) {
                assertEquals("Username", outputData.getUsername());
                assertFalse(outputData.isUseCaseFailed());
            }
        };

        WatchedListRemoveInputBoundary interactor = new WatchedListRemoveInteractor(presenter, dataAccessObject);
        interactor.execute(inputData);
    }
}
