package game;
import java.util.*;


public class Country {
    public String name = "";
    //The countries that you can reach if you own this country
    public ArrayList<Country> neighbors = new ArrayList<Country>();
    public riskPlayer owner;
    //The number of soldiers on this country
    public int armySize = 0;


    public Country(String theName) {
        name = theName; 
    }

    public void addNeighbor (Country c) {
        neighbors.add(c);
    }

    public ArrayList<Country> giveNeighbors() {
        return neighbors;
    }

    public void assignOwner(riskPlayer p){
        owner = p;
    }

    public void acceptArmy (int a) {
        armySize += a;
    }

    public void resetArmy() {
        armySize = 0; 
    }


    

}
