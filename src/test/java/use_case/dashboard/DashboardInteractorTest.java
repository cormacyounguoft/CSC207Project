package use_case.dashboard;

import entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.MockDataAccessObject;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DashboardInteractorTest {

    private MockDataAccessObject dataAccessObject;

    @BeforeEach
    void setUp() {
        dataAccessObject = new MockDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Username", "Password123!");
        dataAccessObject.save(user);

        Movie movie1 = new Movie();
        movie1.setTitle("Movie1");
        movie1.setRuntime(120);
        movie1.setGenre(List.of("Action"));
        dataAccessObject.saveToWatchedList("Username", movie1);
        dataAccessObject.saveUserRating("Username", "Movie1", 5);

        Movie movie2 = new Movie();
        movie2.setTitle("Movie2");
        movie2.setRuntime(150);
        movie2.setGenre(List.of("Drama"));
        dataAccessObject.saveToWatchedList("Username", movie2);
        dataAccessObject.saveUserRating("Username", "Movie2", 4);
    }

    @Test
    void successTest() {
        DashboardInputData inputData = new DashboardInputData("Username");

        DashboardOutputBoundary presenter = new DashboardOutputBoundary() {
            @Override
            public void prepareSuccessView(DashboardOutputData outputData) {
                assertEquals("Username", outputData.getUsername());
                assertEquals("4 hours and 30 minutes", outputData.getTotalHoursWatched());
                assertEquals("Movie1", outputData.getFavoriteMovie());
                assertEquals("[Action]", outputData.getFavoriteGenre());
                assertEquals(4.5, outputData.getAverageRating());
                assertEquals("Movie2", outputData.getLongestMovie());
            }
        };

        DashboardInputBoundary interactor = new DashboardInteractor(dataAccessObject, presenter);

        interactor.execute(inputData);
    }
    @Test
    void noMoviesWatchedTest() {
        UserFactory factory = new CommonUserFactory();
        User newUser = factory.create("NewUser", "Password123!");
        dataAccessObject.save(newUser);
        DashboardInputData inputData = new DashboardInputData("NewUser");

        DashboardOutputBoundary presenter = new DashboardOutputBoundary() {
            @Override
            public void prepareSuccessView(DashboardOutputData outputData) {
                assertEquals("NewUser", outputData.getUsername());
                assertEquals("No time watched", outputData.getTotalHoursWatched());
                assertEquals("No favourite movie", outputData.getFavoriteMovie());
                assertEquals("No favourite genre", outputData.getFavoriteGenre());
                assertEquals(0, outputData.getAverageRating());
                assertEquals("No movies watched", outputData.getLongestMovie());
            }
        };

        DashboardInputBoundary interactor = new DashboardInteractor(dataAccessObject, presenter);

        interactor.execute(inputData);
    }
    @Test
    void tiedRatingsTest() {
        dataAccessObject.saveUserRating("Username", "Movie2", 5); // Tie in ratings

        DashboardInputData inputData = new DashboardInputData("Username");

        DashboardOutputBoundary presenter = new DashboardOutputBoundary() {
            @Override
            public void prepareSuccessView(DashboardOutputData outputData) {
                assertEquals("Movie1", outputData.getFavoriteMovie()); // Expected: first movie with highest rating
            }
        };

        DashboardInputBoundary interactor = new DashboardInteractor(dataAccessObject, presenter);

        interactor.execute(inputData);
    }
    @Test
    void extremeValuesTest() {
        Movie movie3 = new Movie();
        movie3.setTitle("Epic Movie");
        movie3.setRuntime(10000); // Extreme runtime
        movie3.setGenre(List.of("Fantasy"));
        dataAccessObject.saveToWatchedList("Username", movie3);
        dataAccessObject.saveUserRating("Username", "Epic Movie", 1);

        DashboardInputData inputData = new DashboardInputData("Username");

        DashboardOutputBoundary presenter = new DashboardOutputBoundary() {
            @Override
            public void prepareSuccessView(DashboardOutputData outputData) {
                assertEquals("Epic Movie", outputData.getLongestMovie());
                assertEquals(3.33, outputData.getAverageRating(), 0.01); // Average considering all ratings
            }
        };

        DashboardInputBoundary interactor = new DashboardInteractor(dataAccessObject, presenter);

        interactor.execute(inputData);
    }
    @Test
    void userWithNoRatingsTest() {
        // Add a new user and movies, but do not add ratings
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("RatinglessUser", "NoRatings123!");
        dataAccessObject.save(user);

        Movie movie1 = new Movie();
        movie1.setTitle("Unrated Movie");
        movie1.setRuntime(90);
        movie1.setGenre(List.of("Comedy"));
        dataAccessObject.saveToWatchedList("RatinglessUser", movie1);

        DashboardInputData inputData = new DashboardInputData("RatinglessUser");

        DashboardOutputBoundary presenter = new DashboardOutputBoundary() {
            @Override
            public void prepareSuccessView(DashboardOutputData outputData) {
                assertEquals("RatinglessUser", outputData.getUsername());
                assertEquals("1 hour and 30 minutes", outputData.getTotalHoursWatched());
                assertEquals("No favourite movie", outputData.getFavoriteMovie());
                assertEquals("No favourite genre", outputData.getFavoriteGenre());
                assertEquals(0, outputData.getAverageRating());
                assertEquals("Unrated Movie", outputData.getLongestMovie());
            }
        };

        DashboardInputBoundary interactor = new DashboardInteractor(dataAccessObject, presenter);
        interactor.execute(inputData);
    }
}
