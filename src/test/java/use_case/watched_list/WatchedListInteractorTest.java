package use_case.watched_list;

import entity.CommonUserFactory;
import entity.Movie;
import entity.MovieFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.MockDataAccessObject;
import use_case.watched_list_remove.WatchedListRemoveInputBoundary;
import use_case.watched_list_remove.WatchedListRemoveInputData;
import use_case.watched_list_remove.WatchedListRemoveInteractor;
import use_case.watched_list_remove.WatchedListRemoveOutputBoundary;
import use_case.watched_list_remove.WatchedListRemoveOutputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class WatchedListInteractorTest {
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

            }
        };

        WatchedListRemoveInputBoundary interactor = new WatchedListRemoveInteractor(presenter, dataAccessObject);
        interactor.execute(inputData);
    }
}
