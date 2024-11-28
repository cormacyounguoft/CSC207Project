package use_case.rated_list;

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

class RatedListInteractorTest {
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
        dataAccessObject.saveUserRating("Username", "Movie", 1);
    }

    @Test
    public void successTest() {
        RatedListInputData inputData = new RatedListInputData("Username", "Movie");

        RatedListOutputBoundary presenter = new RatedListOutputBoundary() {
            @Override
            public void prepareSuccessView(RatedListOutputData outputData) {
                assertEquals(outputData.getUsername(), "Username");
                assertFalse(outputData.isUseCaseFailed());
            }
        };

        RatedListInputBoundary interactor = new RatedListInteractor(presenter, dataAccessObject);
        interactor.execute(inputData);

    }
}

