package ie.ucd.mscba.nca.GolfGA;

public class Population {

    // Holds population of tours
    Team[] teams;

    // Construct a population
    public Population(int populationSize, boolean initialise) {
        teams = new Team[populationSize];
        // If we need to initialise a population of tours do so
        if (initialise) {
            // Loop and create individuals
            for (int i = 0; i < populationSize(); i++) {
                Team newTeam = new Team();
                newTeam.generateIndividual();
                saveTeam(i, newTeam);
            }
        }
        //System.out.println(teams.length);
        /*for (int i = 0; i < teams.length; i++) {
        	System.out.println("Initial Population: " + teams[i]);
        }*/
        
    }
    
    // Saves a team
    public void saveTeam(int index, Team team) {
        teams[index] = team;
    }
    
    // Gets a tour from population
    public Team getTeam(int index) {
        return teams[index];
    }

    // Gets the best tour in the population
    public Team getFittest() {
        Team fittest = teams[0];
        int fittestInd = 0;
        int i = 0;
        // Loop through individuals to find fittest
        for (i = 1; i < populationSize(); i++) {
            if (fittest.getFitness() <= getTeam(i).getFitness()) {
                fittest = getTeam(i);
                fittestInd = i;
            }
        }
        swapFittest(0, fittestInd);
        return fittest;
    }
    
    public Team getFittestAfterInd(int ind) {
        Team fittest = teams[ind];
        int fittestInd = 0;
        int i = 0;
        // Loop through individuals to find fittest
        for (i = ind; i < populationSize(); i++) {
            if (fittest.getFitness() <= getTeam(i).getFitness()) {
                fittest = getTeam(i);
                fittestInd = ind;
            }
        }
        swapFittest(0, ind);
        return fittest;
    }
    
    public double getAverageFitness() {
    	double averageFitness = 0;
    	for (int i =0; i < populationSize(); i++) {
    		averageFitness += getTeam(i).getTotalPPM();
    	}
    	averageFitness = averageFitness/populationSize();
    	return averageFitness;
    }

    // Gets population size
    public int populationSize() {
        return teams.length;
    }
    
    public void swapFittest(int i, int fittest) {
    	Team temp = new Team();
    	temp = teams[i];
    	teams[i] = teams[fittest];
    	teams[fittest] = temp;
    }
    
}