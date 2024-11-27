# CSC207Project: Movie Application

### Authors and Contributers
- Humza Chaudhry 
- Samir Hendawi 
- Cormac Young 
- Serdar Zhangali 
- Aseer Baset

### Project Purpose
The purpose of this project is to create a movie organization system that allows users to explore and manage a comprehensive database of movies. Users can search for movies by title, view details including the rating, release date, and genre, create and manage watchlists, rate movies, and maintain a history of watched films.

### Table of contents

<!-- TOC -->
* [Authors and Contributers](#authors-and-contributers)
* [Project Purpose](#project-purpose)
* [Features of the software](#features-of-the-software)
* [Installation instructions](#installation-instructions)
* [Usage guide](#usage-guide)
* [License](#license)
* [Feedback](#feedback)
* [Contributions](#contributions)
* [User Stories](#user-stories)
<!-- TOC -->

### Features of the software
- Signup, login, and change password.
- Search for movies and view detailed information, including title, release date, Rotten Tomatoes score, genres, actors, directors, and a description.
- Add and remove movies from a watchlist to keep track of films you want to watch.
- Add and remove movies from a watched list to track films you’ve already seen.
- Rate movies you’ve watched on a scale of 0 to 5.
- View a dashboard with insights including total hours watched, longest movie watched, favorite genre, favorite movie, and your average movie rating.

#### Here's what the Logged-In View looks like:
![img.png](images/LoggedInView.png)

#### Here's what the Logged-In Search Result View looks like:
![img.png](images/SearchedMovie.png)

### Installation instructions
1. Fork the GitHub repository and clone it to your computer. Refer to these guides for assistance: [Set up a Git repository in IntelliJ](https://www.jetbrains.com/help/idea/set-up-a-git-repository.html#clone-repo) and [Fork a repository on GitHub](https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/working-with-forks/fork-a-repo).
2. Visit [OMDb API](www.omdbapi.com) and generate an API key.
3. In IntelliJ, navigate to Run > Edit Configurations > Environment variables.
4. Add your API key in the format: APIKEY=[YOUR_API_KEY_HERE]. It should look like this: 
![image of adding the api key as an environment variable.](images/adding_api_key.png "adding api key")
5. Once the API key is set up, you can run the Main program.

### Usage guide
Users can search for movies, where the release date, rotten tomatoes score, genre, actors, and directors of the movie is displayed.
Users are able to sign up and create an account. In which you set a password that must meet a criteria.
When users sign up, they are able to log into their accounts, using the password and username created at signup.

After logging in, users also have the ability to:
Users have the ability to add a movie to their watched list. This is intended to contain movies that the user has watched.
Users have the ability to add a movie to their watch list, which is intended to contain movies that users want to watch in the future.
Users have the ability to rate movies after they have added the movie to their watched list. To do this users must click on the poster after navigating to their watched list page, which would then prompt you to enter a rating out of 5.
Users can change the password of their account. The changed password must also match the restrictions required for passwords in account creation.
Users can navigate to a dashboard panel, which consists of their username, total hours watched, their favourite movie, their favourite genre, an average of the ratings they have given, and the longest movie that they have watched.
Users can navigate to a rated list panel, which displays the names and posters of movies that they have rated accompanied by the rating they gave each movie.
Users can log out of their accounts.

### License

### Feedback

### Contributions

### User Stories
1. **Team Story:** John wants to log into his account. After logging in, he can search for movies, view his watchlist, view his watched list, and see what movies he has rated.
2. Cormac (cormacyounguoft): Sarah wants to search for a movie, whether she's logged in or not. Upon opening the app, she’s prompted to either log in or search without logging in. She decides to search for _Avengers: Endgame_, and the app displays detailed information about the movie, including its rating, release date, genre, director, and main cast.
3. Humza (humzach): David wants to create a detailed watchlist. After logging into the movie app, he can search for _The Batman_ and adds it to his watchlist. David can view his watchlist and remove movies. The movies are sorted by title.
4. Serdar (seko1112): Mary has just finished watching _Spider-Man: No Way Home_. She logs into the movie app, navigates to the movie, and rates it 4 out of 5 stars. In her profile, Mary has a complete list of all the movies she’s rated. The movies are sorted by rating.
5. Samir (samirhendawi1): Alex wants an in-depth look at his movie-watching habits. He navigates to a dashboard that provides data on his total hours watched, favorite genre, and average rating given. The dashboard also includes insights, such as the longest movies he has watched.
6. Aseer (asrbst): Bob wants to share his watchlist with friends. When he goes to his watchlist, he has the option to export his watchlist. He clicks "export" and can save his curated watchlist as a text file.
