package use_case.get_watchlist;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.Movie;
import entity.MovieFactory;
import entity.User;
import entity.UserFactory;
import org.junit.Test;
import use_case.get_watched_list.GetWatchedListInputBoundary;
import use_case.get_watched_list.GetWatchedListInputData;
import use_case.get_watched_list.GetWatchedListInteractor;
import use_case.get_watched_list.GetWatchedListOutputBoundary;
import use_case.get_watched_list.GetWatchedListOutputData;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class GetWatchlistInteractorTest {
    @Test
    public void successTest() {
        GetWatchlistInputData inputData = new GetWatchlistInputData("Username");
        InMemoryUserDataAccessObject inMemoryUserDataAccessObject = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Username", "Password123!");
        inMemoryUserDataAccessObject.save(user);

        MovieFactory movieFactory = new MovieFactory();
        Movie movie = movieFactory.create();
        movie.setTitle("Movie");
        movie.setPosterLink("url");

        inMemoryUserDataAccessObject.get("Username").getWatchList().addMovie(movie);

        GetWatchlistOutputBoundary presenter = new GetWatchlistOutputBoundary() {
            @Override
            public void prepareSuccessView(GetWatchlistOutputData outputData) {
                assertEquals("Username", outputData.getUsername());
                assertEquals(List.of("Movie"), outputData.getWatchlistTitle());
                assertEquals(List.of("url"), outputData.getWatchlistURL());
                assertFalse(outputData.isUseCaseFailed());
            }
        };
        GetWatchlistInputBoundary interactor = new GetWatchlistInteractor(presenter, inMemoryUserDataAccessObject);
        interactor.execute(inputData);
    }
}
