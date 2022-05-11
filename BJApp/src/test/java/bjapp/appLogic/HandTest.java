/*
 * This class contains tests for the Hand-class.
*/
package bjapp.appLogic;

//import org.junit.AfterClass;
//import org.junit.BeforeClass;
import bjapp.applogic.Hand;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lassi Savolainen, student, University of Helsinki
 */
public class HandTest {
    
    Hand playerHand;
    Hand dealerHand;
    
    public HandTest() {
    }
    
    @Before
    public void setUp() {
        playerHand = new Hand(false);
        dealerHand = new Hand(true);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void firstCardCorrectNonAcePlayerTest(){
        String value = playerHand.addCard(7);
        assertEquals("7", value);
    }
    
    @Test
    public void firstCardCorrectAcePlayerTest(){
        String value = playerHand.addCard(1);
        assertEquals("1/11", value);
    }
    @Test
    public void firstCardCorrectNonAceDealerTest() {
        String value = dealerHand.addCard(9);
        assertEquals("9", value);
    }
    @Test
    public void resetTest(){
        playerHand.addCard(1);
        playerHand.resetHand();
        String value = playerHand.getValueString();
        assertEquals("0", value);
    }
    @Test
    public void getValueTest() {
        playerHand.addCard(4);
        int value = playerHand.getValue();
        assertEquals(4, value);
    }
    @Test
    public void bustTest() {
        playerHand.addCard(10);
        playerHand.addCard(5);
        String value = playerHand.addCard(10);
        assertEquals("25, bust!", value);
    }
    @Test
    public void getValueStringLastBranchTest() {
        playerHand.addCard(7);
        String value = playerHand.addCard(10);
        assertEquals("17", value);
    }
    @Test
    public void dealerDoesNotHitNoAceTest() {
        dealerHand.addCard(7);
        dealerHand.addCard(10);
        boolean check = dealerHand.dealerHitCheck();
        assertEquals(false, check);
    }
    @Test
    public void dealerDoesNotHitWithAceTest() {
        dealerHand.addCard(1);
        dealerHand.addCard(7);
        boolean check = dealerHand.dealerHitCheck();
        assertEquals(false, check);
    }
    @Test
    public void dealerHitsNoAceTest() {
        dealerHand.addCard(4);
        dealerHand.addCard(6);
        boolean check = dealerHand.dealerHitCheck();
        assertEquals(true, check);
    }
    @Test
    public void dealerHitsWithSoftAceTest(){
        dealerHand.addCard(1);
        dealerHand.addCard(4);
        boolean check = dealerHand.dealerHitCheck();
        assertEquals(true, check);
    }
    @Test
    public void dealerHitsWithHardAceTest() {
        dealerHand.addCard(10);
        dealerHand.addCard(4);
        dealerHand.addCard(1);
        boolean check = dealerHand.dealerHitCheck();
        assertEquals(true, check);
    }
    @Test
    public void dealerWinsSoftVSoftTest() {
        playerHand.addCard(7);
        playerHand.addCard(1);
        dealerHand.addCard(8);
        dealerHand.addCard(1);
        int check = dealerHand.dealerWinCheck(playerHand);
        assertEquals(1, check);
    }
    @Test
    public void playerWinsNoAceVNoAceTest() {
        playerHand.addCard(10);
        playerHand.addCard(10);
        dealerHand.addCard(9);
        dealerHand.addCard(7);
        int check = dealerHand.dealerWinCheck(playerHand);
        assertEquals(-1, check);
    }
    @Test
    public void pushHardvHardTest() {
        playerHand.addCard(1);
        playerHand.addCard(3);
        playerHand.addCard(5);
        dealerHand.addCard(1);
        dealerHand.addCard(8);
        int check = dealerHand.dealerWinCheck(playerHand);
        assertEquals(0, check);
    }
    @Test
    public void randomCardsAreValidTest() {
        boolean validity = true;
        boolean[] eachValue = new boolean[11];
        for (int i = 0; i < 1000; i++) {
            playerHand.addRandomCard();
            if (playerHand.getValue() < 1 || playerHand.getValue() > 10) {
                validity = false;
            } else {
                eachValue[playerHand.getValue()] = true;
            }
            playerHand.resetHand();
        }
        for (int i = 1; i < 11; i++) {
            if (eachValue[i] == false) {
                validity = false;
            }
        }
        assertEquals(validity, true);
    }
    @Test
    public void hasAceWithAceTest(){
        playerHand.addCard(1);
        assertEquals(playerHand.hasAce(), true);
    }
    @Test
    public void hasAceWithoutAceTest(){
        playerHand.addCard(7);
        assertEquals(playerHand.hasAce(), false);
    }
}
