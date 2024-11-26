package use_case.get_watched_list;

import data_access.InMemoryUserDataAccessObject;
import data_access.MovieAccessObject;
import entity.CommonUserFactory;
import entity.Movie;
import entity.MovieFactory;
import entity.User;
import entity.UserFactory;
import org.junit.Test;
import use_case.SearchDataAccessInterface;
import use_case.add_to_watched_list.AddToWatchedListInputData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class GetWatchedListInteractorTest {
    @Test
    public void successTest() {
        GetWatchedListInputData inputData = new GetWatchedListInputData("Username");
        InMemoryUserDataAccessObject inMemoryUserDataAccessObject = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Username", "Password123!");
        inMemoryUserDataAccessObject.save(user);

        MovieFactory movieFactory = new MovieFactory();
        Movie movie = movieFactory.create();
        movie.setTitle("Movie");
        movie.setPosterLink("url");

        inMemoryUserDataAccessObject.get("Username").getWatchedList().addMovie(movie);

        GetWatchedListOutputBoundary presenter = new GetWatchedListOutputBoundary() {
            @Override
            public void prepareSuccessView(GetWatchedListOutputData outputData) {
                assertEquals("Username", outputData.getUsername());
                assertEquals(List.of("Movie"), outputData.getWatchedListTitle());
                assertEquals(List.of("url"), outputData.getWatchedListURL());
                assertFalse(outputData.isUseCaseFailed());
            }
        };
        GetWatchedListInputBoundary interactor = new GetWatchedListInteractor(presenter, inMemoryUserDataAccessObject);
        interactor.execute(inputData);
    }
}
