package use_case.watchlist;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.Movie;
import entity.MovieFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.MockDataAccessObject;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

class WatchlistInteractorTest {
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

    @AfterEach
    void tearDown() {
        dataAccessObject.clear();
    }

    @Test
    void successTest() {
        WatchlistInputData inputData = new WatchlistInputData("Username", List.of("Movie"), List.of("url"));

        WatchlistOutputBoundary presenter = new WatchlistOutputBoundary() {
            @Override
            public void prepareSuccessView(WatchlistOutputData outputData) {
                Assertions.assertEquals("Username", outputData.getUsername());
                Assertions.assertEquals(List.of("Movie"), outputData.getWatchlistTitle());
                Assertions.assertEquals(List.of("url"), outputData.getWatchlistURL());
                Assertions.assertFalse(outputData.isUseCaseFailed());
            }
        };

        WatchlistInputBoundary interactor = new WatchlistInteractor(presenter);
        interactor.execute(inputData);
    }
}
