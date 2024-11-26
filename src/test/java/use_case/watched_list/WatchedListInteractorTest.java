package use_case.watched_list;

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

public class WatchedListInteractorTest {
    @Test
    public void successTest() {
        WatchedListInputData inputData = new WatchedListInputData("Username", List.of("Movie"), List.of("url"));
        InMemoryUserDataAccessObject inMemoryUserDataAccessObject = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Username", "Password123!");
        inMemoryUserDataAccessObject.save(user);

        MovieFactory movieFactory = new MovieFactory();
        Movie movie = movieFactory.create();
        movie.setTitle("Movie");
        movie.setPosterLink("url");

        inMemoryUserDataAccessObject.get("Username").getWatchedList().addMovie(movie);

        WatchedListOutputBoundary presenter = new WatchedListOutputBoundary() {
            @Override
            public void prepareSuccessView(WatchedListOutputData outputData) {
                assertEquals("Username", outputData.getUsername());
                assertEquals(List.of("Movie"), outputData.getWatchedListTitle());
                assertEquals(List.of("url"), outputData.getWatchedListURL());
                assertFalse(outputData.isUseCaseFailed());
            }
        };
        WatchedListInputBoundary interactor = new WatchedListInteractor(presenter);
        interactor.execute(inputData);
    }
}
