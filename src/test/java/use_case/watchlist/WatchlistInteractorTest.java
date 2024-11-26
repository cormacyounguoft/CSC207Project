package use_case.watchlist;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.Movie;
import entity.MovieFactory;
import entity.User;
import entity.UserFactory;
import org.junit.Test;
import use_case.watched_list.WatchedListInputBoundary;
import use_case.watched_list.WatchedListInputData;
import use_case.watched_list.WatchedListInteractor;
import use_case.watched_list.WatchedListOutputBoundary;
import use_case.watched_list.WatchedListOutputData;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class WatchlistInteractorTest {
    @Test
    public void successTest() {
        WatchlistInputData inputData = new WatchlistInputData("Username", List.of("Movie"), List.of("url"));
        InMemoryUserDataAccessObject inMemoryUserDataAccessObject = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Username", "Password123!");
        inMemoryUserDataAccessObject.save(user);

        MovieFactory movieFactory = new MovieFactory();
        Movie movie = movieFactory.create();
        movie.setTitle("Movie");
        movie.setPosterLink("url");

        inMemoryUserDataAccessObject.get("Username").getWatchedList().addMovie(movie);

        WatchlistOutputBoundary presenter = new WatchlistOutputBoundary() {
            @Override
            public void prepareSuccessView(WatchlistOutputData outputData) {
                assertEquals("Username", outputData.getUsername());
                assertEquals(List.of("Movie"), outputData.getWatchlistTitle());
                assertEquals(List.of("url"), outputData.getWatchlistURL());
                assertFalse(outputData.isUseCaseFailed());
            }
        };
        WatchlistInputBoundary interactor = new WatchlistInteractor(presenter);
        interactor.execute(inputData);
    }
}
