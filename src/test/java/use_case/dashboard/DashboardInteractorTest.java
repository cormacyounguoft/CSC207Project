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
}
