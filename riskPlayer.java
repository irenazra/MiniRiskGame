package Risk;
import java.util.*;
public class riskPlayer{
    //countries
    public Map<String,Country> countries = new HashMap<String, Country>();
    public List<Country> theCountries; 
    public Random rand = new Random();

    //number of soldiers
    public int numSoldiers = 0; 

    public String playerName ="";
    //mission: this is to reach 5 countries first

    public riskPlayer(List<Country> myCountries, String name, Map<String,Country> allCountries) {
        theCountries = myCountries; 
        countries = allCountries; 
        playerName = name;
       
    }

    public void getSoldiers(int a) {
        numSoldiers += a;
    }

    public int defend (Country co) {
        int numSoldiersOnCo = co.armySize;
        int dice = rand.nextInt(6) + 1;
        System.out.println(playerName + " rolled " + dice); 
        int defendPower = dice * numSoldiersOnCo;

        return defendPower; 

    }

    public void dropCountry (Country c) {
        theCountries.remove(c);
    }

    public Set<Country> printPossibleCountries() {
        Set<Country> set = new HashSet<>(); 
        for (Country country : theCountries) {
            set.add(country); 
            for (int i = 0; i < country.neighbors.size(); i ++) {
                if (!set.contains(country.neighbors.get(i))) {
                    set.add(country.neighbors.get(i));
                }
            }
        }

        Iterator<Country> setIterator = set.iterator();
        while (setIterator.hasNext()) { 
            Country next = setIterator.next();
            System.out.println("-" + next.name + " which is owned by " + next.owner.playerName + " with " + next.armySize + " soldiers.");

            
        } 

        return set;


    }

    public String getTarget() {
        Scanner sc =new Scanner(System.in); 
        String a= sc.nextLine();
        sc.close();
        return a; 
    }


    //YOU NEED TO HANDLE INPUT MISMATCHEXCEPTION HERE
    public int getSoldierNumber () {
        Scanner sc =new Scanner(System.in); 
        Boolean valid = sc.hasNextInt();
        if (!valid) {
            System.out.println("Please enter an integer!");
            sc.close();
            return -1; 
        }
        int a= sc.nextInt();
        sc.close();
        return a; 

    }

    public void placeArmies() {
        System.out.println("Which country do you want to put soldiers to? "); 
    
        Set<Country> possibleTargets = printPossibleCountries();
 
        String targetName = getTarget();
        
        while (!possibleTargets.contains(countries.get(targetName))){
            System.out.println("You either entered a country that does not exist or a country that you cannot reach. Please try again!");
            targetName = getTarget();
        }

        System.out.println("You selected " + targetName);
        Country target = countries.get(targetName); 
               
    
        System.out.println("the target is " + target);

        System.out.println("How many soldiers would you like to send? You have " + numSoldiers + " soldiers.");
        int b= getSoldierNumber();   
        while (b==-1) {
            b = getSoldierNumber(); 
        }
        if (b > numSoldiers) {
            System.out.println("You do not have " + b + " soldiers. You will attack with all the soldiers you have which is " + numSoldiers);
            b = numSoldiers;
        }

        if (target.owner.playerName.equals("noone") || target.owner.playerName.equals(playerName)) {
            System.out.println("No enemy in " + target.name + "!");
            target.acceptArmy(b);
            target.owner = this;
            theCountries.add(target);
            
        } else {
            System.out.println("Oh no! " + target.owner.playerName+ "  owns" + target.name);
            int opponentScore = target.owner.defend(target);
            int d = rand.nextInt(6) + 1;
            int dice = d * b;
            System.out.println(playerName + " rolled " + d);
            System.out.println(target.owner.playerName+ " has " + opponentScore + "points!");
            System.out.println("You have " + dice + "points!");
            if (dice > opponentScore) {
                target.owner.dropCountry(target);
                System.out.println("You won!");
                theCountries.add(target); 
                target.resetArmy();
                target.acceptArmy(b);
                target.assignOwner(this);
            } else {
                numSoldiers -= b; 
            }
        }


    }
    public Boolean didIWin() {
        return (theCountries.size()>=5);
    }


}




