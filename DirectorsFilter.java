
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class DirectorsFilter implements Filter {
    private String[] myDirs;

    public DirectorsFilter(String dirs) {
        myDirs = dirs.split(",");
    }

    @Override
    public boolean satisfies(String id) {
        for (String dir : myDirs){
            if(MovieDatabase.getDirector(id).contains(dir)){
                return true;
            }
        }
        return false;
    }

}
