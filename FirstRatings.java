
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
public class FirstRatings {
    public static ArrayList<Movie> loadMovies (String fr){
        ArrayList<Movie> movies = new ArrayList<Movie>();
        FileResource f = new FileResource(fr);
        CSVParser parser = f.getCSVParser();
        for(CSVRecord line : parser){
            Movie movie = new Movie(line.get(0),line.get(1),line.get(2),line.get(3),line.get(4),line.get(5),
                    Integer.parseInt(line.get(6)),line.get(7));
            movies.add(movie);
        }
        return movies;
    }
    
    public HashMap<String,Integer> makedirmap (ArrayList<Movie> array){
        HashMap<String,Integer> dirmap = new HashMap<String,Integer>();
        for (Movie movie : array){
            if(movie.getDirector().contains(",")){
                for(String dir : movie.getDirector().split(",")){
                    if(!dirmap.keySet().contains(dir.trim())){
                        dirmap.put(dir.trim(),1);
                    }else{
                        dirmap.put(dir.trim(),dirmap.get(dir.trim())+1);
                    }
                }
            }else{
                if(!dirmap.keySet().contains(movie.getDirector().trim())){
                        dirmap.put(movie.getDirector().trim(),1);
                    }else{
                        dirmap.put(movie.getDirector().trim(),dirmap.get(movie.getDirector().trim())+1);
                    }
            }
        }
        return dirmap;
    }
    
    public void testloadMovies (){
        ArrayList<Movie> array = loadMovies("./data/ratedmovies_short.csv");
        int countgenre=0;
        int countmin=0;
        for (Movie movie : array){
            if(movie.getGenres().contains("Comedy")){
                countgenre++;
            }
            if(movie.getMinutes()>150){
                countmin++;
            }
        }
        System.out.println("count genre: "+countgenre);
        System.out.println("count min: "+countmin);
        HashMap<String,Integer> dirmap = makedirmap (array);
        String maxdir="";
        int max =0;
        for (String dir : dirmap.keySet()){
            if(max<dirmap.get(dir)){
                max=dirmap.get(dir);
            }
        }
        for (String dir : dirmap.keySet()){
            if(max==dirmap.get(dir)){
                maxdir+=" - "+dir;
            }
        }
        System.out.println("the maximum number of movies by any director: "+max);
        System.out.println("the name of dirs : "+maxdir);
    }

    public static ArrayList<Rater> loadRaters (String fr){
        ArrayList<Rater> raters = new ArrayList<Rater>();
        FileResource f = new FileResource(fr);
        CSVParser parser = f.getCSVParser();
        for(CSVRecord line : parser){
            int index=-1;
            boolean con=true ;
            for (int i=0;i<raters.size();i++){
                Rater rater = raters.get(i);
                if (rater.getID().equals(line.get(0))){
                    con=false;
                    index = i;
                    break;
                }
            }
            if(con){
                Rater rater= new PlainRater(line.get(0));
                rater.addRating(line.get(1),Integer.parseInt(line.get(2)));
                raters.add(rater);
            }else{
                Rater rater= raters.get(index);
                rater.addRating(line.get(1),Integer.parseInt(line.get(2)));
            }
        }
        return raters;
    }

    public void testloadRaters(){
        ArrayList<Rater> array = loadRaters("./data/ratings.csv");
        System.out.println(array.size());
        //ArrayList<Rater> array = loadRaters("./data/ratings_short.csv");
        //System.out.println(array.size());
        ArrayList<String> movies =new ArrayList<String>();
        int max=0;
        String count="";
        for(Rater rater : array){
            //System.out.println("raterid :"+rater.getID()+"   rating num:"+rater.numRatings());
            for (String movie : rater.getItemsRated()){
                //System.out.println(movie+" "+rater.getRating(movie));
                if(!movies.contains(movie)){
                    movies.add(movie);
                }
            }
            if(max<rater.numRatings()){
                max=rater.numRatings();
            }
        }
        for(Rater rater : array){
            if(rater.getID().equals("193")){
                System.out.println("the numRatings of id 193 "+rater.numRatings());
            }
            if(rater.numRatings()==max){
                count+=" - "+rater.getID();
            }
        }
        System.out.println("max numRating " +max);
        System.out.println("the ids that has the max numRating "+ count); 
        int countm=0;
        for(Rater rater : array){
            if(rater.getItemsRated().contains("1798709")){
                countm++;
            }
        }
        System.out.println("the num of rating 1798709 has is "+countm);
        System.out.println("the num of movies is "+ movies.size());
    }
}