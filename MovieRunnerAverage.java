
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerAverage {
    public void printAverageRatings (){
        //////double begin = System.nanoTime();
        SecondRatings sec = new SecondRatings("./data/ratedmovies_short.csv","./data/ratings_short.csv");
        //System.out.println(sec.getMovieSize() + "  "+sec.getRaterSize());
        for(Rating rating :sec.getAverageRatings(3)){
            System.out.println(rating.getValue()+"  "+sec.getTitle(rating.getItem()));
        }
        System.out.println(sec.getAverageByID(sec.getID("The Godfather"),3));
        //////double end = System.nanoTime();
        //////double stime = (end-begin)/1e9;
        //////System.out.println(stime);
    }
}
