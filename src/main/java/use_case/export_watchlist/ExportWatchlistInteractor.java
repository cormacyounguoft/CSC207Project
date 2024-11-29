package use_case.export_watchlist;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.List;

import entity.Movie;
import entity.MovieList;
import use_case.get_watchlist.GetWatchlistDataAccessInterface;

public class ExportWatchlistInteractor implements ExportWatchlistInputBoundary {
    private final ExportWatchlistOutputBoundary outputBoundary;
    private final GetWatchlistDataAccessInterface dataAccess;

    public ExportWatchlistInteractor(ExportWatchlistOutputBoundary outputBoundary, GetWatchlistDataAccessInterface dataAccess) {
        this.outputBoundary = outputBoundary;
        this.dataAccess = dataAccess;
    }

    @Override
    public void exportWatchlist(ExportWatchlistInputData inputData) {
        try {
            MovieList watchlist = dataAccess.getWatchlist(inputData.getUserId());
            List<Movie> movies = watchlist.getMovieList();

            StringBuilder content = new StringBuilder();

            content.append(inputData.getUserId().toUpperCase()).append("'S TO WATCH LIST\n\n");

            // Format each movie
            for (Movie movie : movies) {
                content.append(movie.getTitle())
                        .append(" - ")
                        .append("[")
                        .append(String.join(", ", movie.getGenre())) // Assuming genres are a List<String>
                        .append("] - ")
                        .append(movie.getReleaseDate())
                        .append("\n");
            }

            String filePath = "watchlist_" + inputData.getUserId() + ".txt";
            Files.writeString(Path.of(filePath), content.toString());

            outputBoundary.presentExportResult(new ExportWatchlistOutputData(true, filePath));
        } catch (IOException e) {
            outputBoundary.presentExportResult(new ExportWatchlistOutputData(false, null));
        }
    }
}
