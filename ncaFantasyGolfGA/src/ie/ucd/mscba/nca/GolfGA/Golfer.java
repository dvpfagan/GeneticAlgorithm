package ie.ucd.mscba.nca.GolfGA;

public class Golfer {
    int golferID;
    double golferValue;
    double golferPPM;
    int numGolfers = 111;
    
    // Constructs a random golfer
    public Golfer(){
        this.golferID = (int)(Math.random()*numGolfers);
        this.golferValue = (int)(Math.random()*numGolfers);
        this.golferPPM = (int)(Math.random()*numGolfers);
    }
    
    // Constructs a golfer
    public Golfer(int ID, double val, double ppm){
        this.golferID = ID;
        this.golferValue = val;
        this.golferPPM = ppm;
    }
    
    // Gets golfer's ID
    public int getGolferID(){
        return this.golferID;
    }
    
    // Gets golfer's value
    public double getGolferValue(){
        return this.golferValue;
    }
    
 // Gets golfer's PPM
    public double getGolferPPM(){
        return this.golferPPM;
    }
    
 // Gets number of golfers
    public int getNumGolfers(){
        return this.numGolfers;
    }
    
     
    @Override
    public String toString(){
        return getGolferID() + "=ID";
    }
}
