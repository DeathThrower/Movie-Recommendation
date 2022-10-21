
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;

    public ThirdRatings() {
        myRaters = FirstRatings.loadRaters("./data/ratings.csv");
    }

    public ThirdRatings(String ratingsfile) {
        myRaters = FirstRatings.loadRaters(ratingsfile);
    }

    public int getRaterSize(){
        return myRaters.size();
    }

    public double getAverageByID (String movieID , int minimalRaters){
        double raternum=0;
        double totalrating=0;
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
        ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> movies = new ArrayList<Rating>();
        for (String movie : myMovies){
            double avgrating = getAverageByID(movie,minimalRaters);
            if(avgrating!=0.0){
                movies.add(new Rating(movie,avgrating));
            }
        }
        return movies;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter (int minimalRaters,Filter filterCriteria){
        ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> movies = new ArrayList<Rating>();
        for (String movie : myMovies){
            double avgrating = getAverageByID(movie,minimalRaters);
            if(avgrating!=0.0 && filterCriteria.satisfies(movie)){
                movies.add(new Rating(movie,avgrating));
            }
        }
        return movies;
    }
    
}
