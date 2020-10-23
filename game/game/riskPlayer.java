/**
 * The riskPlayer class for the MiniRiskGame. 
 *
 * @author Iren Azra Coskun
 * @version October 10,2020
 */

package game;
import java.util.*;
public class riskPlayer{

    //all the countries in the game
    protected Map<String,Country> countries = new HashMap<String, Country>();

    //countries that this player owns
    protected List<Country> theCountries; 

    protected Random rand = new Random();

    //number of soldiers that can be freely placed to any of player's territory or any adjacent territories
    protected int numSoldiers = 0; 

    protected String playerName ="";


    /**
     * initializes a new player
     * 
     * @param myCountries, a list containing the Countries initially assigned to this player
     * @param name, a string representing the name of this player
     * @param allCountries, a HashMap which contains all the countries in the game
     */
    public riskPlayer(List<Country> myCountries, String name, Map<String,Country> allCountries) {
        theCountries = myCountries; 
        countries = allCountries; 
        playerName = name;
       
    }

    /**
     * Adds soldiers to player's numSoldiers
     * 
     * @param a, number of soldiers that are given to the player
     */
    public void getSoldiers(int a) {
        numSoldiers += a;
    }

    /**
     * Calculates a number representing how powerful defense will be
     * 
     * @param co, the country that is being attacked by another player
     * @return an integer representing how powerful the defense will be whose value depends on random integer generation
     *         and the number of players the defendor has on the country
     */
    public int defend (Country co) {
        int numSoldiersOnCo = co.armySize;
        int dice = rand.nextInt(6) + 1;
        System.out.println(playerName + " rolled " + dice); 
        return dice * numSoldiersOnCo;

       

    }

    /**
     * Removes a country from a player's country list if it has been lost. 
     * 
     * @param c, the country that is lost after a battle
     */
    public void dropCountry (Country c) {
        theCountries.remove(c);
    }

    /**
     * Prints out the names of all the possible countries that the player can put soldiers to with our without an attack
     * 
     * @return a set countraining all the possible countries that the player can put soldiers on with or without an attack
     */
    public Set<Country> printPossibleCountries() {
        
        Set<Country> set = new HashSet<>(); 
        for (Country country : theCountries) {
            //Add all the countries owned by this player to the set
            set.add(country); 
            //Add all the countries neighboring the countries owned by this player to the set
            for (int i = 0; i < country.neighbors.size(); i ++) {
                if (!set.contains(country.neighbors.get(i))) {
                    set.add(country.neighbors.get(i));
                }
            }
        }

        //Iterate through the set, and print the names of all the countries
        Iterator<Country> setIterator = set.iterator();
        while (setIterator.hasNext()) { 
            Country next = setIterator.next();
            System.out.println("-" + next.name + " which is owned by " + next.owner.playerName + " with " + next.armySize + " soldiers.");

            
        } 

        return set;


    }

    /**
     * Determines the target country of the player, asks for user input
     * 
     * @return the target country
     */
    public String getTarget() {
        Scanner sc =new Scanner(System.in); 
        return sc.nextLine();
      
    }


    /**
     * Determines the number of soldiers to place or attack with, asks for user input
     * 
     * @return number of soldiers
     */
    public int getSoldierNumber () {
        Scanner sc =new Scanner(System.in); 
        if (!sc.hasNextInt()) {
            System.out.println("Please enter an integer!");
            return -1; 
        }
        return sc.nextInt();

    
    }

    /**
     * Enables the player to choose a target country and choose the number of soldiers to attack with. 
     * Simulates the battle between two players if necessary
     * 
     */

    public void placeArmies() {

        System.out.println("Which country do you want to put soldiers to? "); 
        Set<Country> possibleTargets = printPossibleCountries();
 
        //Get a valid target country
        String targetName = getTarget();
        while (!possibleTargets.contains(countries.get(targetName))){
            System.out.println("You either entered a country that does not exist or a country that you cannot reach. Please try again!");
            targetName = getTarget();
        }
        System.out.println("You selected " + targetName);
        Country target = countries.get(targetName); 
    
        //Get a valid number of soldiers to attack with/to place
        System.out.println("How many soldiers would you like to send? You have " + numSoldiers + " soldiers.");
        int b= getSoldierNumber(); 
        while (b==-1) {
            System.out.println("You entered an invalid soldier number. Please try again!");
            b = getSoldierNumber();
        }
        if (b > numSoldiers) {
            System.out.println("You do not have " + b + " soldiers. You will attack with all the soldiers you have which is " + numSoldiers);
            b = numSoldiers;
        }

        //the country is owned by this player or is owned by no one
        if (target.owner.playerName.equals("noone") || target.owner.playerName.equals(playerName)) {
            System.out.println("No enemy in " + target.name + "!");
            target.acceptArmy(b);
            target.owner = this;
            if(!theCountries.contains(target)) {
                theCountries.add(target);
            }
            
            
        } else {
            //Battle
            System.out.println("Oh no! " + target.owner.playerName+ "  owns" + target.name);

            //Get the defense score
            int opponentScore = target.owner.defend(target);

            //Get the attack score
            int d = rand.nextInt(6) + 1;
            int dice = d * b;

            System.out.println(playerName + " rolled " + d);
            System.out.println(target.owner.playerName+ " has " + opponentScore + " points!");
            System.out.println("You have " + dice + " points!");

            //This player wins
            if (dice > opponentScore) {
                target.owner.dropCountry(target);
                System.out.println("You won!");
                theCountries.add(target); 
                target.resetArmy();
                target.acceptArmy(b);
                target.assignOwner(this);

            //This player loses
            } else {
                numSoldiers -= b; 
            }
        }


    }
    /**
     * Checks whether the user owns 5 countries. If so, the player has won.
     * @return boolean indicating whether the user won or not. 
     */
    public Boolean didIWin() {
        return (theCountries.size()>=5);
    }


}




