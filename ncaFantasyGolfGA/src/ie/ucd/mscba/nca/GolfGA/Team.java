package ie.ucd.mscba.nca.GolfGA;

import java.util.ArrayList;
import java.util.Collections;

public class Team{

    // Holds our tour of cities
    private ArrayList team = new ArrayList<Golfer>();
    // Cache
    private double fitness = 0;
    private double totalValue = 0;
    private double totalPPM = 0;
    //private int numGolfers = 111;	// GA1
    //private int numGolfers = 102;   // GA2
    private int numGolfers = 108;   // GA3
    private boolean containsGolfer = false;
    
    private int distance = 0;
    
    // Constructs a blank tour
    public Team(){
        for (int i = 0; i < 6; i++) {
            team.add(null);
        }
    }
    
    public Team(ArrayList team){
        this.team = team;
    }

    // Creates a random individual
    public void generateIndividual() {
    	int selector = 0;
    	totalValue = 100;
    	while (totalValue > 10) {
    		// Loop through all our golfers and add them to our team
    		totalValue = 0;
            for (int i = 0; i < 6; i++) {
            	boolean individualSet = false;
    	        while (individualSet == false) {
    	        	selector = (int)(Math.random()*numGolfers);
    	        	for (int j = 0; j < 6; j++) {
    	        		Golfer g = getGolfer(j);
    	        		if (g == TeamManager.getGolfer(selector)) {
    	        			individualSet = false;
    	        			selector = (int)(Math.random()*numGolfers);
    	        			j = -1;
    	        		}
    	        		else {
    	        			individualSet = true;
    	        			
    	        		}
    	        			
    	        	}
    	        	
    	        	setGolfer(i, TeamManager.getGolfer(selector));
    	        	totalValue += TeamManager.getGolfer(selector).getGolferValue();
    	        	// setGolfer(i, TeamManager.getGolfer(selector);
    	        		
    	        }
            }
    	}
        
        // Randomly reorder the tour
        //Collections.shuffle(team);
    }

    // Gets a city from the tour
    public Golfer getGolfer(int teamPosition) {
        return (Golfer)team.get(teamPosition);
    }

    // Sets a city in a certain position within a tour
    public void setGolfer(int teamPosition, Golfer golfer) {
        team.set(teamPosition, golfer);
        // If the tours been altered we need to reset the fitness and distance
        fitness = 0;
        distance = 0;
    }
    
    // Gets the tours fitness
    public double getFitness() {
        if (fitness >= 0) {
            fitness = getTotalPPM();
        }
        return fitness;
    }
    
    // Get the total value of the team
    public double getTotalValue() {
    	if (totalValue >= 0) {
    		double teamValue = 0;
    		// loop through players
    		for (int playerIndex =0; playerIndex < teamSize(); playerIndex++){
    			Golfer g = getGolfer(playerIndex);
    			teamValue += g.getGolferValue();
    		}
    		totalValue = teamValue;
    	}
    	return totalValue;
    }
    
    public double getTotalPPM() {
    	if (totalPPM >= 0) {
    		double teamPPM = 0;
    		// loop through players
    		for (int playerIndex = 0; playerIndex < teamSize(); playerIndex++){
    			Golfer g = getGolfer(playerIndex);
    			teamPPM += g.getGolferPPM();
    		}
    		totalPPM = teamPPM;
    	}
    	return totalPPM;
    }
    
    

    // Get number of cities on our tour
    public int teamSize() {
        return team.size();
    }
    
    // Check if the tour contains a city
    public boolean containsGolfer(Golfer golfer){
        return team.contains(golfer);
    }
    
    @Override
    public String toString() {
        String geneString = "|";
        for (int i = 0; i < teamSize(); i++) {
            geneString += getGolfer(i)+"|";
        }
        return geneString;
    }

	public void setTotalPPM(double totalPPM) {
		this.totalPPM = totalPPM;
	}
    
}
