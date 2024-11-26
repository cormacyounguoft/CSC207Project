package use_case.watched_list;

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

    @AfterEach
    void tearDown() {
        dataAccessObject.clear();
    }

    @Test
    void successTest() {
        WatchedListInputData inputData = new WatchedListInputData("Username", List.of("Movie"), List.of("url"));

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
