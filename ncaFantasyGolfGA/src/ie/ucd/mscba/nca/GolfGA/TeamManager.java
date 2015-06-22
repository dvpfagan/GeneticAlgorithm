package ie.ucd.mscba.nca.GolfGA;

import java.util.ArrayList;

public class TeamManager {

    // Holds our cities
    private static ArrayList golfers = new ArrayList<Golfer>();

    // Adds a destination city
    public static void addGolfer(Golfer golfer) {
        golfers.add(golfer);
    }
    
    // Get a city
    public static Golfer getGolfer(int index){
        return (Golfer)golfers.get(index);
    }
    
    // Get the number of destination cities
    public static int numberOfGolfers(){
        return golfers.size();
    }
}
