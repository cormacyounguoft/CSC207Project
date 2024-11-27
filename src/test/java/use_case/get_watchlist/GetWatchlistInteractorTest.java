package use_case.get_watchlist;

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

class GetWatchlistInteractorTest {
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
    void successTest() {
        GetWatchlistInputData inputData = new GetWatchlistInputData("Username");

        GetWatchlistOutputBoundary presenter = new GetWatchlistOutputBoundary() {
            @Override
            public void prepareSuccessView(GetWatchlistOutputData outputData) {
                assertEquals("Username", outputData.getUsername());
                assertEquals(List.of("Movie"), outputData.getWatchlistTitle());
                assertEquals(List.of("url"), outputData.getWatchlistURL());
                assertFalse(outputData.isUseCaseFailed());
            }
        };

        GetWatchlistInputBoundary interactor = new GetWatchlistInteractor(presenter, dataAccessObject);
        interactor.execute(inputData);
    }
}
