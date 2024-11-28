package use_case.watchlist_remove;

import entity.CommonUserFactory;
import entity.Movie;
import entity.MovieFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.MockDataAccessObject;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Watchlist_removeInteractorTest {
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
        dataAccessObject.saveToWatchlist("Username", movie);
    }

    @Test
    void testRemoveWatchlist() {
        WatchlistRemoveInputData input = new WatchlistRemoveInputData("Username", "Movie");

        WatchlistRemoveOutputBoundary presenter = new WatchlistRemoveOutputBoundary() {
            @Override
            public void prepareSuccessView(WatchlistRemoveOutputData outputData) {
                assertEquals("Username", outputData.getUsername());
                assertFalse(dataAccessObject.getWatchlist("Username").containsTitle("Movie"));

            }
        };
        WatchlistRemoveInputBoundary interactor = new WatchlistRemoveInteractor(presenter, dataAccessObject);
        interactor.execute(input);
    }
}
