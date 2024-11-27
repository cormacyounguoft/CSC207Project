package use_case.get_rated_list;

import entity.CommonUserFactory;
import entity.Movie;
import entity.MovieFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.MockDataAccessObject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetRatedListInteractorTest {
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
        dataAccessObject.saveUserRating("Username", "Movie", 0);
    }

    @Test
    void successTest() {
        GetRateListInputData inputData = new GetRateListInputData("Username");

        GetRateListOutputBoundary presenter = new GetRateListOutputBoundary() {
            @Override
            public void prepareSuccessView(GetRateListOutputData outputData) {
                assertEquals("Username", outputData.getUsername());
                assertTrue(outputData.getUserRating().containsKey("Movie"));
                assertEquals(outputData.getUserRating().get("Movie").get(0), "0");
                assertEquals(outputData.getUserRating().get("Movie").get(1), "url");
                assertFalse(outputData.isUseCaseFailed());
            }
        };

        GetRateListInputBoundary interactor = new GetRateListInteractor(dataAccessObject, presenter);
        interactor.execute(inputData);
    }
}
