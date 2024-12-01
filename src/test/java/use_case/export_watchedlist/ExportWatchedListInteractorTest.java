package use_case.export_watchedlist;

import entity.Movie;
import entity.MovieFactory;
import entity.CommonUserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.MockDataAccessObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExportWatchedListInteractorTest {

    MockDataAccessObject dataAccessObject;
    ExportWatchedListInteractor interactor;

    @BeforeEach
    void setUp() {
        dataAccessObject = new MockDataAccessObject();
        dataAccessObject.save(new CommonUserFactory().create("TestUser", "Password123!"));

        MovieFactory movieFactory = new MovieFactory();

        // Placeholder movies
        Movie movie1 = movieFactory.create();
        movie1.setTitle("The Matrix");
        movie1.setGenre(List.of("Action", "Sci-Fi"));
        movie1.setReleaseDate("1999");
        dataAccessObject.saveToWatchedList("TestUser", movie1);

        Movie movie2 = movieFactory.create();
        movie2.setTitle("The Godfather");
        movie2.setGenre(List.of("Crime", "Drama"));
        movie2.setReleaseDate("1972");
        dataAccessObject.saveToWatchedList("TestUser", movie2);

        interactor = new ExportWatchedListInteractor(
                new ExportWatchedListOutputBoundary() {
                    @Override
                    public void prepareSuccessView(ExportWatchedListOutputData outputData) {
                        assertTrue(outputData.isSuccess());
                        assertEquals("watchedlist_TestUser.txt", outputData.getFilePath());
                        assertEquals("TestUser", outputData.getUserId());
                    }

                    @Override
                    public void prepareFailView(String errorMessage) {
                        fail("Test should not fail: " + errorMessage);
                    }
                },
                dataAccessObject
        );
    }

    @Test
    void testExportWatchedListSuccess() {
        ExportWatchedListInputData inputData = new ExportWatchedListInputData("TestUser");
        interactor.exportWatchedList(inputData);

        String filePath = "watchedlist_TestUser.txt";
        String expectedContent = """
                TESTUSER'S WATCHED LIST

                The Matrix - [Action, Sci-Fi] - 1999
                The Godfather - [Crime, Drama] - 1972
                """;

        final Path path = Path.of(filePath);
        try {
            String actualContent = Files.readString(path);
            assertEquals(expectedContent, actualContent, "Exported file content should match expected content.");
        } catch (IOException e) {
            fail("Exported file could not be read.");
        } finally {
            try {
                Files.deleteIfExists(path);
            } catch (IOException e) {
                fail("Failed to delete the exported file after the test.");
            }
        }
    }

    @Test
    void testExportWatchedListFailure_EmptyWatchedList() {
        dataAccessObject.save(new CommonUserFactory().create("EmptyUser", "Password123!"));
        interactor = new ExportWatchedListInteractor(
                new ExportWatchedListOutputBoundary() {
                    @Override
                    public void prepareSuccessView(ExportWatchedListOutputData outputData) {
                        fail("Test should not succeed for an empty watched list.");
                    }
                    @Override
                    public void prepareFailView(String errorMessage) {
                        assertEquals("The watched list is empty!", errorMessage);
                    }
                },
                dataAccessObject
        );

        ExportWatchedListInputData inputData = new ExportWatchedListInputData("EmptyUser");
        interactor.exportWatchedList(inputData);
    }
}
