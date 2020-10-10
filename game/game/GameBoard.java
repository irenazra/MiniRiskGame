/**
 * The GameBoard class for the implementation of a smaller version of famous strategy game Risk: MiniRiskGame. 
 *There are two players, and 8 countries in this game.
 *The first player to own 5 countries wins the game!
 *First player,f, starts from Iran while the second player,s, starts from Poland.
 *A player can put soldiers on the lands that they own, or they can invade a neighboring land.
 *Everyturn a player gets new soldiers. The number of soldiers that they get is equal to the half the number of soldiers they did not use last round plus 1.
 *If a player tries to invade a country that already has foreign solders on it,players roll one dice each.
 *What they get, gets multiplied with the number of soldiers they have on the land or they are attacking with.
 *Therefore, the more soldiers you have on a country, the more protected it will be.
 *Similarly, if you attack with more soldiers, your attack gets stronger!
 *But, there is always an element of chance, so good luck!!
 *
 * @author Iren Azra Coskun
 * @version October 10,2020
 */
package game;

import java.util.*;

public class GameBoard {


    public riskPlayer firstP;
    public riskPlayer secondP; 
    public Map<String,Country> allCountries;

    /**
     * Initializes a new GameBoard
     */
    public GameBoard() {

        //Creates the 8 countries
        Country Turkey =  new Country("Turkey");
        Country Armenia =  new Country("Armenia");
        Country Russia =  new Country("Russia");
        Country Greece =  new Country("Greece");
        Country Ukraine =  new Country("Ukraine");
        Country Poland =  new Country("Poland");
        Country Iran =  new Country("Iran");
        Country Bulgaria =  new Country("Bulgaria");

        //All the countries are stored in a HashMap
        Map<String,Country> everyCountry = new HashMap<String, Country>();
        //A placeholder player
        riskPlayer noone = new riskPlayer(new ArrayList<>(),"noone", everyCountry);
        everyCountry.put(Turkey.name,Turkey);
        everyCountry.put(Armenia.name,Armenia);
        everyCountry.put(Russia.name,Russia);
        everyCountry.put(Greece.name,Greece);
        everyCountry.put(Ukraine.name,Ukraine);
        everyCountry.put(Poland.name,Poland);
        everyCountry.put(Iran.name,Iran);
        everyCountry.put(Bulgaria.name,Bulgaria);

        //Every country is assigned to the placeholder player. 
        Turkey.assignOwner(noone);
        Armenia.assignOwner(noone);
        Russia.assignOwner(noone);
        Greece.assignOwner(noone);
        Ukraine.assignOwner(noone);
        Poland.assignOwner(noone);
        Iran.assignOwner(noone);
        Bulgaria.assignOwner(noone);
  

        //The neighbor information is encoded
        Turkey.addNeighbor(Armenia);
        Turkey.addNeighbor(Greece);
        Turkey.addNeighbor(Iran);
        Turkey.addNeighbor(Bulgaria);
        Turkey.addNeighbor(Russia);
        Armenia.addNeighbor(Turkey);
        Armenia.addNeighbor(Iran);
        Russia.addNeighbor(Ukraine);
        Russia.addNeighbor(Turkey);
        Greece.addNeighbor(Turkey);
        Greece.addNeighbor(Bulgaria);
        Ukraine.addNeighbor(Russia);
        Ukraine.addNeighbor(Poland);
        Poland.addNeighbor(Ukraine);
        Iran.addNeighbor(Turkey);
        Iran.addNeighbor(Armenia);
        Bulgaria.addNeighbor(Turkey);
        Bulgaria.addNeighbor(Greece);

        
        //Create the players' country lists
        ArrayList<Country> firstPlayersCountries = new ArrayList<Country>(); 
        firstPlayersCountries.add(Iran);
        ArrayList<Country> secondPlayersCountries = new ArrayList<Country>(); 
        secondPlayersCountries.add(Poland);

        //Create players
        riskPlayer first = new riskPlayer(firstPlayersCountries, "first",everyCountry);
        Iran.assignOwner(first);
        riskPlayer second = new riskPlayer(secondPlayersCountries,"second",everyCountry);
        Poland.assignOwner(second);

        firstP = first;
        secondP = second; 
        allCountries = everyCountry;

    }

    /**
     * Prints the game rules
     */
    public void printGameRules () {
        System.out.println("--------------------------------------");
        System.out.println("------------GAME RULES----------------");
        System.out.println("--------------------------------------");
        System.out.println("-There are two players, and 8 countries in this game.");
        System.out.println("-The first player to own 5 countries wins the game!");
        System.out.println("-First player,f, starts from Iran while the second player,s, starts from Poland.");
        System.out.println("-A player can put soldiers on the lands that they own, or they can invade a neighboring land.");
        System.out.println("-Everyturn a player gets new soldiers. The number of soldiers that they get is equal to the half the number of soldiers they did not use last round plus 1.");
        System.out.println("-If a player tries to invade a country that already has foreign solders on it,players roll one dice each.");
        System.out.println("What they get, gets multiplied with the number of soldiers they have on the land or they are attacking with.");
        System.out.println("Therefore, the more soldiers you have on a country, the more protected it will be.");
        System.out.println("Similarly, if you attack with more soldiers, your attack gets stronger!");
        System.out.println("But, there is always an element of chance, so good luck!!");

        

    }

    /**
     * Prints the game state with information about which players own which countries and how many soldiers are placed in each country.
     */
    public void printGameState () {
        String p = "";
        String u="";
        String r="";
        String b="";
        String g="";
        String i="";
        String t="";
        String a="";
        if (!allCountries.get("Turkey").owner.playerName.equals("noone")) {
            t = (allCountries.get("Turkey").owner.playerName).substring(0,1) + (allCountries.get("Turkey").armySize);
        }

        if (!allCountries.get("Poland").owner.playerName.equals("noone")) {
            p = (allCountries.get("Poland").owner.playerName).substring(0,1) + (allCountries.get("Poland").armySize);
        }

        if (!allCountries.get("Ukraine").owner.playerName.equals("noone")) {
            u = (allCountries.get("Ukraine").owner.playerName).substring(0,1) + (allCountries.get("Ukraine").armySize);
        }
        if (!allCountries.get("Armenia").owner.playerName.equals("noone")) {
            a = (allCountries.get("Armenia").owner.playerName).substring(0,1) + (allCountries.get("Armenia").armySize);
        }
        if (!allCountries.get("Bulgaria").owner.playerName.equals("noone")) {
            b = (allCountries.get("Bulgaria").owner.playerName).substring(0,1) + (allCountries.get("Bulgaria").armySize);
        }
        if (!allCountries.get("Greece").owner.playerName.equals("noone")) {
            g = (allCountries.get("Greece").owner.playerName).substring(0,1) + (allCountries.get("Greece").armySize);
        }
        if (!allCountries.get("Iran").owner.playerName.equals("noone")) {
            i = (allCountries.get("Iran").owner.playerName).substring(0,1) + (allCountries.get("Iran").armySize);
        }
        if (!allCountries.get("Russia").owner.playerName.equals("noone")) {
            r = (allCountries.get("Russia").owner.playerName).substring(0,1) + (allCountries.get("Russia").armySize);
        }




        String one = "[Poland " + p + "][Ukraine " + u + "][Russia " + r + "]";
        String two = "                         |                               ";
        String four = "            [Bulgaria " + b + "][Turkey " + t +"][Armenia " + a + "]         ";
        String five = "           [Greece " + g + "]       [Iran " + i + "]";

        System.out.println("--------------------------------------");
        System.out.println("------------GAME MAP------------------");
        System.out.println("--------------------------------------");
        System.out.println("--------------------------------------");
        System.out.println(one);
        System.out.println(two);
        System.out.println(four);
        System.out.println(five);
        System.out.println("--------------------------------------");

    }

    public static void main (String[] args) {

        //Initialize the game board and print the game rules
        GameBoard gb = new GameBoard();
        gb.printGameRules();

        //Initially give 3 soldiers to each player
        gb.firstP.getSoldiers(3);
        gb.secondP.getSoldiers(3);
        
        gb.printGameState();
    

        Boolean firstsTurn = true; 
        while (true) {
            //first player places soldiers
            if (firstsTurn) {
                //each turn, players get new soldiers
                gb.firstP.getSoldiers( 1 + gb.firstP.numSoldiers/2);
                firstsTurn = false; 
                if ( gb.firstP.numSoldiers < 1) {
                    System.out.println("The first player does not have enough soldiers to play.");
                    continue;
                }
                
                System.out.println("First player's turn! First player has " + gb.firstP.numSoldiers + " soldiers to place.");
                gb.firstP.placeArmies();
                gb.printGameState();
                
                //is the game over?
                if (gb.firstP.didIWin()){
                    System.out.println("First player owns: ");
                    for (int i = 0; i< gb.firstP.theCountries.size(); i ++) {
                        System.out.println(gb.firstP.theCountries.get(i).name);
                    }
                    
                    System.out.println("First player won!");
                    break;
                }
            //second player places soldiers
            } else {
                //each turn, players get new soldiers
                gb.secondP.getSoldiers(1 + gb.secondP.numSoldiers/2);
                firstsTurn = true; 
                if (gb.secondP.numSoldiers < 1) {
                    System.out.println("The second player does not have enough soldiers to play.");
                    continue;
                }
                System.out.println("Second player's turn!");
                gb.secondP.placeArmies();
                gb.printGameState();
                //is the game over?
                if (gb.secondP.didIWin()){
                    System.out.println("Second player owns: ");
                    for (int i = 0; i< gb.secondP.theCountries.size(); i ++) {
                        System.out.println(gb.secondP.theCountries.get(i).name);
                    }
                    
                    System.out.println("Second player won!");
                    break;
                }
            }

        }

    }

        

        

        
        
}



    


