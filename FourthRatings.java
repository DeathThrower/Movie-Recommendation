
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class FourthRatings {
    public double getAverageByID (String movieID , int minimalRaters){
        double raternum=0;
        double totalrating=0;
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
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

    private int dotProduct (Rater me, Rater r){
        int value =0;
        ArrayList<Double> Me = new ArrayList<Double>();
        ArrayList<Double> R = new ArrayList<Double>();
        for (String movie : me.getItemsRated()){
            if(r.hasRating(movie)){
                Me.add(me.getRating(movie)-5);
                R.add(r.getRating(movie)-5);
            }
        }
        for (int i=0;i<Me.size();i++){
            value+= (int) (Me.get(i)*R.get(i));
        }
        return value;
    }

    private ArrayList<Rating> getSimilarities (String id){
        ArrayList<Rating> list = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        for(Rater r : RaterDatabase.getRaters()){
            if(!r.getID().equals(me.getID())){
                int value = dotProduct(me,r);
                if(value > 0){
                    list.add(new Rating(r.getID(),value));
                }
            }
        }
        Collections.sort(list,Collections.reverseOrder());
        return list;
    }

    public double calweightedaverage(String movie,ArrayList<Rating> raters,int minimalRaters){
        double raternum=0;
        double totalrating=0;
        for(Rating rater : raters){
            if(RaterDatabase.getRater(rater.getItem()).getItemsRated().contains(movie)){
                raternum++;
                totalrating+=RaterDatabase.getRater(rater.getItem()).getRating(movie)*rater.getValue();
            }
        }
        if(raternum>=minimalRaters){
            return totalrating/raternum;
        }else{
            return 0.0;
        }
    }

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        ArrayList<Rating> raters = new ArrayList<Rating>();
        ArrayList<Rating> sim = getSimilarities(id);
        for(int i=0;i<numSimilarRaters;i++){
            raters.add(sim.get(i));
        }
        ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> movies = new ArrayList<Rating>();
        for (String movie : myMovies){
            double avgrating = calweightedaverage(movie,raters,minimalRaters);
            if(avgrating!=0.0){
                movies.add(new Rating(movie,avgrating));
            }
        }
        Collections.sort(movies,Collections.reverseOrder());
        return movies;
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> raters = new ArrayList<Rating>();
        ArrayList<Rating> sim = getSimilarities(id);
        if(sim.size()>numSimilarRaters){
            for(int i=0;i<numSimilarRaters;i++){
                raters.add(sim.get(i));
            }
        }else{
            for(int i=0;i<sim.size();i++){
                raters.add(sim.get(i));
            }
        }
        ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> movies = new ArrayList<Rating>();
        for (String movie : myMovies){
            double avgrating = calweightedaverage(movie,raters,minimalRaters);
            if(avgrating!=0.0 && filterCriteria.satisfies(movie)){
                movies.add(new Rating(movie,avgrating));
            }
        }
        Collections.sort(movies,Collections.reverseOrder());
        return movies;
    }
}
