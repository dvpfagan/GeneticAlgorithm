package ie.ucd.mscba.nca.GolfGA;

public class GA {

    /* GA parameters */
	private static final double crossoverRate = -0.75;
    private static final double mutationRate = -0.1;
    private static final int tournamentSize = 10;
    private static final boolean elitism = true;
    private static int crossoverPoint = 0;
    private static final boolean random = true;

    // Evolves a population over one generation
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.populationSize(), false);

        // Keep our best individual if elitism is enabled
        int elitismOffset = 0;
        if (elitism) {
            newPopulation.saveTeam(0, pop.getFittest());
            newPopulation.saveTeam(1, pop.getFittestAfterInd(1));
            elitismOffset = 2;
        }

        // Crossover population
        // Loop over the new population's size and create individuals from
        // Current population
        
        int i = elitismOffset;
        while (i < newPopulation.populationSize()) {  //( not repopulated)
        //for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            // Select parents
            Team parent1 = tournamentSelection(pop);
            Team parent2 = tournamentSelection(pop);
            Team child1 = new Team();
            Team child2 = new Team();
            //System.out.println("Parent1: " + parent1);
            //System.out.println("Parent2: " + parent2);
            //System.out.println("-------------------------------");
            // Apply Crossover Probability
            if(Math.random() < crossoverRate) {
	            // Crossover parents
	            crossoverPoint = (int) (Math.random() * parent1.teamSize());
	            //System.out.println("crossover point" + crossoverPoint);
	            child1 = crossover(parent1, parent2, crossoverPoint);
	            //System.out.println("Child1 ["+i+": " + child1);
	            child2 = crossover(parent2, parent1, crossoverPoint);
	            //System.out.println("Child2 ["+i+": " + child2);
	            if (child1.getTotalValue() > 10 || child2.getTotalValue() > 10) {
	            	child1 = parent1;
		            child2 = parent2;
	            }
	            
	        }
            else if (random) {
            	child1.generateIndividual();
            	child2.generateIndividual();
            }
	        else {
	           	child1 = parent1;
	            child2 = parent2;
	        }
            
            
            // Add child to new population
            newPopulation.saveTeam(i, child1);
            i += 1;
            //System.out.println("i after child1: " + i);
            newPopulation.saveTeam(i, child2);
            i += 1;
            //System.out.println("i after child2: " + i);
        }
        //System.out.println("CROSSOVER COMPLETE");
        
        // Mutate the new population a bit to add some new genetic material
        for (int e = elitismOffset; e < newPopulation.populationSize(); e++) {
            mutate(newPopulation.getTeam(e));
        }

        return newPopulation;
    }

    // Applies crossover to a set of parents and creates offspring
    public static Team crossover(Team parent1, Team parent2, int one_point) {
        // Create new child tour
    	
        Team child = new Team();

        // Get point where crossover will be applied
        // 1-point Crossover
                
        for (int i = 0; i < one_point; i++) {
        	child.setGolfer(i, parent1.getGolfer(i));
        }
        for (int i = one_point; i < parent2.teamSize(); i++) {
        	if (!child.containsGolfer(parent2.getGolfer(i))){
        		child.setGolfer(i, parent2.getGolfer(i));
        	}
        	else if (child.containsGolfer(parent1.getGolfer(i))) {
        		child = parent1;
        	}
        	else {
        		child.setGolfer(i, parent1.getGolfer(i));
        	}
        	/*
        	child.setGolfer(i, parent2.getGolfer(i));
        	for (int j =0; j<parent2.teamSize(); j++) {
        		if (child.getGolfer(j) == child.getGolfer(i) && j != i ){
        			child.setGolfer(i, parent1.getGolfer(i));
        		}
        	}*/
        }

        
        return child;
    }

    // Mutate a tour using swap mutation
    private static void mutate(Team team) {
        // Loop through tour cities
    	int g1ID = 0;
        for(int teamPos1=0; teamPos1 < team.teamSize(); teamPos1++){
            // Apply mutation rate
            if(Math.random() < mutationRate){
                // Get the golfer at target position in team
                Golfer golfer1 = team.getGolfer(teamPos1);
                g1ID = golfer1.getGolferID();
                
                // Get Next Golfer based on ID
                Golfer nextG = new Golfer();
                
                if( g1ID+1 < TeamManager.numberOfGolfers() ) {  
                	nextG = TeamManager.getGolfer(g1ID+1);   
                }
                else
                	nextG = TeamManager.getGolfer(g1ID-1);
                
                // Set this golfer to be new golfer in team
                for (int j =0; j<team.teamSize(); j++) {
            		if (team.getGolfer(j) == nextG){
            			nextG = golfer1;
            		}
            	}
                team.setGolfer(teamPos1, nextG);
                if (team.getTotalValue() > 10) {
                	team.setGolfer(teamPos1, golfer1);
                }
                //System.out.println("*****************MUTATED******************");
                
                
            }
        }
    }

    // Selects candidate tour for crossover
    private static Team tournamentSelection(Population pop) {
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false);
        // For each place in the tournament get a random candidate tour and
        // add it
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.populationSize());
            tournament.saveTeam(i, pop.getTeam(randomId));
        }
        // Get the fittest tour
        Team fittest = tournament.getFittest();
        return fittest;
    }
}
