
/**
 * Write a description of g here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerSimilarRatings {
    public void printAverageRatings (){
        FourthRatings sec = new FourthRatings();
        RaterDatabase.initialize("ratings_short.csv");
        System.out.println(RaterDatabase.size());
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
        FourthRatings sec = new FourthRatings();
        RaterDatabase.initialize("ratings_short.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        Filter f = new YearAfterFilter(1980);
        ArrayList<Rating> movies = sec.getAverageRatingsByFilter(1,f);
        System.out.println(movies.size());
        for(Rating rating :movies){
            System.out.println(rating.getValue()+" "+MovieDatabase.getYear(rating.getItem())+" "+MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printAverageRatingsByGenre (){
        FourthRatings sec = new FourthRatings();
        RaterDatabase.initialize("ratings_short.csv");
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
        FourthRatings sec = new FourthRatings();
        RaterDatabase.initialize("ratings_short.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        Filter f = new MinutesFilter(110,170);
        ArrayList<Rating> movies = sec.getAverageRatingsByFilter(1,f);
        System.out.println(movies.size());
        for(Rating rating :movies){
            System.out.println(rating.getValue() +"Time: "+MovieDatabase.getMinutes(rating.getItem())+" "+MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printAverageRatingsByDirectors (){
        FourthRatings sec = new FourthRatings();
        RaterDatabase.initialize("ratings_short.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        Filter f = new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze");
        ArrayList<Rating> movies = sec.getAverageRatingsByFilter(1,f);
        System.out.println(movies.size());
        for(Rating rating :movies){
            System.out.println(rating.getValue()+" "+MovieDatabase.getTitle(rating.getItem()));
            System.out.println("     "+MovieDatabase.getDirector(rating.getItem()));
        }
    }

    public void printSimilarRatings (){
        FourthRatings sec = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        AllFilters f = new AllFilters();
        Filter f1 = new YearAfterFilter(1975);
        Filter f2 = new GenreFilter("Drama");
        Filter f3 = new MinutesFilter(70,200);
        Filter f4 = new DirectorsFilter("Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola");
        f.addFilter(f3);
        f.addFilter(f1);
        //ArrayList<Rating> movies = sec.getSimilarRatings("71",20,5);
        ArrayList<Rating> movies = sec.getSimilarRatingsByFilter("314",10,5,f);
        System.out.println(movies.size());
        for(Rating rating :movies){
            System.out.println(rating.getValue()+" "+MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printSimilarRatingsByGenre (){
        FourthRatings sec = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        Filter f = new GenreFilter("Mystery");
        ArrayList<Rating> movies = sec.getSimilarRatings("964",20,5);
        System.out.println(movies.size());
        for(Rating rating :movies){
            System.out.println(rating.getValue() +" "+MovieDatabase.getTitle(rating.getItem()));
            //System.out.println("     "+MovieDatabase.getGenres(rating.getItem()));
        }
    }

    public void printSimilarRatingsByDirector (){
        FourthRatings sec = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        Filter f = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        ArrayList<Rating> movies = sec.getSimilarRatings("120",10,2);
        System.out.println(movies.size());
        for(Rating rating :movies){
            System.out.print(rating.getValue()+" "+MovieDatabase.getTitle(rating.getItem())+"----");
            //System.out.println("     "+MovieDatabase.getDirector(rating.getItem()));
        }
    }
}