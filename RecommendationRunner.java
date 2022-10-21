
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class RecommendationRunner implements Recommender {
    public ArrayList<String> getItemsToRate (){
        ArrayList<String> moviesIDs = new ArrayList<String>();
        FourthRatings runner = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        AllFilters f = new AllFilters();
        Filter f1 = new YearAfterFilter(2015);
        Filter f2 = new GenreFilter("Romance");
        Filter f3 = new MinutesFilter(100,150);
        f.addFilter(f1);
        f.addFilter(f2);
        f.addFilter(f3);
        ArrayList<Rating> movies = runner.getAverageRatingsByFilter(1,f);
        for(Rating movie : movies){
            moviesIDs.add(movie.getItem());
        }
        return moviesIDs;
    }

    public void printRecommendationsFor (String webRaterID){
        FourthRatings runner = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        AllFilters f = new AllFilters();
        Filter f1 = new YearAfterFilter(2010);
        Filter f2 = new GenreFilter("Romance");
        Filter f3 = new MinutesFilter(90,180);
        f.addFilter(f1);
        f.addFilter(f3);
        //"<style>table, th, td { border: 1px solid black;}</style>
        String g = "style=border: 2px solid black;";
        ArrayList<Rating> movies = runner.getSimilarRatingsByFilter(webRaterID,15,3,f);
        if(movies.size()==0){
            System.out.print("there is no movies to be recommended");
        }
        else{
            String movs="";
            for(Rating rating :movies){
                String id = rating.getItem();
                movs+="<tr><td>"+MovieDatabase.getYear(id)+"  "+MovieDatabase.getTitle(id)+"</td><td>"+"Time: "+MovieDatabase.getMinutes(id)+"  "+"Genres: "+MovieDatabase.getGenres(id)+"</td></tr>";
            }
            System.out.print("<!DOCTYPE html><html><head>"+"<style>table, th, td { border: 2px solid black;}</style></head><body><table>");
            System.out.print("<tr><th>Movie</th><th>Info</th></tr>");
            System.out.print(movs);
            System.out.print("</table></body></html>");
        }
    }
}
