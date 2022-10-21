
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerWithFilters {
    public void printAverageRatings (){
        ThirdRatings sec = new ThirdRatings("./data/ratings_short.csv");
        System.out.println(sec.getRaterSize());
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println(MovieDatabase.size());
        AllFilters f = new AllFilters();
        Filter f1 = new YearAfterFilter(1980);
        Filter f2 = new GenreFilter("Romance");
        Filter f3 = new MinutesFilter(30,170);
        Filter f4 = new DirectorsFilter("Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola");
        f.addFilter(f3);
        f.addFilter(f4);
        ArrayList<Rating> movies = sec.getAverageRatingsByFilter(1,f);
        System.out.println(movies.size());
        for(Rating rating :movies){
            System.out.println(rating.getValue()+" "+"Time: "+MovieDatabase.getMinutes(rating.getItem())+" "+MovieDatabase.getTitle(rating.getItem()));
            //System.out.println("     "+MovieDatabase.getGenres(rating.getItem()));
            System.out.println("     "+MovieDatabase.getDirector(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByYear(){
        ThirdRatings sec = new ThirdRatings("./data/ratings_short.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        Filter f = new YearAfterFilter(1980);
        ArrayList<Rating> movies = sec.getAverageRatingsByFilter(1,f);
        System.out.println(movies.size());
        for(Rating rating :movies){
            System.out.println(rating.getValue()+" "+MovieDatabase.getYear(rating.getItem())+" "+MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByGenre (){
        ThirdRatings sec = new ThirdRatings("./data/ratings_short.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        Filter f = new GenreFilter("Crime");
        ArrayList<Rating> movies = sec.getAverageRatingsByFilter(1,f);
        System.out.println(movies.size());
        for(Rating rating :movies){
            System.out.println(rating.getValue() +" "+MovieDatabase.getTitle(rating.getItem()));
            System.out.println("     "+MovieDatabase.getGenres(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByMinutes (){
        ThirdRatings sec = new ThirdRatings("./data/ratings_short.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        Filter f = new MinutesFilter(110,170);
        ArrayList<Rating> movies = sec.getAverageRatingsByFilter(1,f);
        System.out.println(movies.size());
        for(Rating rating :movies){
            System.out.println(rating.getValue() +"Time: "+MovieDatabase.getMinutes(rating.getItem())+" "+MovieDatabase.getTitle(rating.getItem()));
        }
    }
    public void printAverageRatingsByDirectors (){
        ThirdRatings sec = new ThirdRatings("./data/ratings_short.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        Filter f = new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze");
        ArrayList<Rating> movies = sec.getAverageRatingsByFilter(1,f);
        System.out.println(movies.size());
        for(Rating rating :movies){
            System.out.println(rating.getValue()+" "+MovieDatabase.getTitle(rating.getItem()));
            System.out.println("     "+MovieDatabase.getDirector(rating.getItem()));
        }
    }
}