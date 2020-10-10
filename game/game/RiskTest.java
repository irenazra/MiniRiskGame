/**
 * The test suite for the MiniRiskGame. 
 *
 * @author Iren Azra Coskun
 * @version October 10,2020
 */
package game;



public class RiskTest {
    public static GameBoard gb = new GameBoard();
    

    //tests the getSoldier() method in riskPlayer
    static void testGetSoldiers() {
        riskPlayer f = gb.firstP;
        f.getSoldiers(23);
        assert (f.numSoldiers == 23);
    }

    //tests the drop() method in riskPlayer
    static void testDrop(){
        riskPlayer f = gb.firstP;
        Country c = gb.allCountries.get("Iran");
        f.dropCountry(c);
        assert (f.countries.isEmpty());
    }

    //Tests the neighbors of the countries in the game
    static void testNeighbors() {
        Country i = gb.allCountries.get("Iran");
        Country t = gb.allCountries.get("Turkey");
        Country r = gb.allCountries.get("Russia");
        Country g = gb.allCountries.get("Greece");
        Country u = gb.allCountries.get("Ukraine");
        Country p = gb.allCountries.get("Poland");
        Country b = gb.allCountries.get("Bulgaria");
        Country a = gb.allCountries.get("Armenia");
        assert(i.neighbors.size() == 2);
        assert(t.neighbors.size() == 5);
        assert(r.neighbors.size() == 2);
        assert(g.neighbors.size() == 2);
        assert(u.neighbors.size() == 2);
        assert(p.neighbors.size() == 1);
        assert(b.neighbors.size() == 2);
        assert(a.neighbors.size() == 2);
        assert(i.neighbors.size() == 2);
    }




    public static void main(String[] args) {
        testGetSoldiers();
        testDrop();
        testNeighbors();
        System.out.println("All tests passed!");
        
    }

}