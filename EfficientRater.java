
/**
 * Write a description of class Rater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class EfficientRater implements Rater{
    private String myID;
    private HashMap<String,Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String,Rating>();
    }

    public void addRating(String item, double rating) {
        myRatings.put(item,new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        return myRatings.keySet().contains(item);
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        if(myRatings.keySet().contains(item)){
            return myRatings.get(item).getValue();
        }
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>(myRatings.keySet());
        return list;
    }
}
