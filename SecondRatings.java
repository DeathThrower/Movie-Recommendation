
/**
 * Write a description of d here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;

    public SecondRatings() {
        myMovies= FirstRatings.loadMovies("./data/ratedmoviesfull.csv");
        myRaters = FirstRatings.loadRaters("./data/ratings.csv");
    }

    public SecondRatings(String moviefile ,String ratingsfile) {
        myMovies= FirstRatings.loadMovies(moviefile);
        myRaters = FirstRatings.loadRaters(ratingsfile);
    }

    public int getMovieSize(){
        return myMovies.size();
    }

    public int getRaterSize(){
        return myRaters.size();
    }

    public double getAverageByID (String movieID , int minimalRaters){
        int raternum=0;
        int totalrating=0;
        for(Rater rater : myRaters){
            if(rater.getItemsRated().contains(movieID)){
                raternum++;
                totalrating+=rater.getRating(movieID);
            }
        }
        if(raternum>=minimalRaters){
            return totalrating/raternum;
        }else{
            return 0.0;
        }
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> movies = new ArrayList<Rating>();
        for (Movie movie : myMovies){
            double avgrating = getAverageByID(movie.getID(),minimalRaters);
            if(avgrating!=0.0){
                movies.add(new Rating(movie.getID(),avgrating));
            }
        }
        return movies;
    }
    
    public String getTitle (String movieID){
        for (Movie movie : myMovies){
            if(movie.getID().equals(movieID)){
                return movie.getTitle();
            }
        }
        return "the ID was not found.";
    }
    
    public String getID (String movieTitle){
        for (Movie movie : myMovies){
            if(movie.getTitle().equals(movieTitle)){
                return movie.getID();
            }
        }
        return "NO SUCH TITLE.";
    }
}
