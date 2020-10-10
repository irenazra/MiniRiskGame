/**
 * The Country class for the MiniRiskGame. 
 *
 * @author Iren Azra Coskun
 * @version October 10,2020
 */
package game;
import java.util.*;


public class Country {
    public String name = "";

    //The countries that you can reach if you own this country
    public ArrayList<Country> neighbors = new ArrayList<Country>();

   
    public riskPlayer owner;

    //The number of soldiers on this country
    public int armySize = 0;


    /**
     * Initialize a new country
     * 
     * @param theName  name of the country, a string
     */
    public Country(String theName) {
        name = theName; 
    }

    /**
     * Adds a new neighbor to this country
     * 
     * @param c country to add
     */
    public void addNeighbor (Country c) {
        neighbors.add(c);
    }

    /**
     * 
     * @return the list of neighbors of this country
     */
    public ArrayList<Country> giveNeighbors() {
        return neighbors;
    }

    /**
     * Assigns a new owner to the country
     * @param p the new owner
     */
    public void assignOwner(riskPlayer p){
        owner = p;
    }

    /**
     * Places soldiers on the country
     * 
     * @param a number of soldiers to be placed on the country
     */
    public void acceptArmy (int a) {
        armySize += a;
    }

    /**
     * Sets the army size on the country to 0.
     */
    public void resetArmy() {
        armySize = 0; 
    }


    

}
