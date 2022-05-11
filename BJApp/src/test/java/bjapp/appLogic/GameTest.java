/*
 * This class contains tests for the Game-class
 */
package bjapp.appLogic;

import bjapp.applogic.Game;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lassisav
 */
public class GameTest {
    
    Game game;
    public GameTest() {
    }
    
    @Before
    public void setUp() {
        game = new Game(1000);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void betSizeGetSetTest() {
        int temp = 1 + (int) Math.random()*1000;
        game.setBetSize(temp);
        assertEquals(temp, game.getBetSize());
    }
    @Test
    public void playerCashIsInitializedCorrectlyTest() {
        assertEquals(1000, game.getPlayerCash());
    }
    @Test
    public void baseDealTest() {
        boolean correct = true;
        for (int i = 0; i < 1000; i++) {
            int temp = game.baseDeal();
            if (temp == 1) {
                if (baseDealPlayerBJ() == false || baseDealDealerBJPossible() == false) {
                    correct = false;
                    System.out.println("baseDeal incorrectly returns 1");
                }
            } else if (temp == 2) {
                if (baseDealPlayerBJ() == false || baseDealDealerBJPossible()) {
                    correct = false;
                    System.out.println("baseDeal incorrectly returns 2");
                }
            } else if (temp == 3) {
                if (baseDealPlayerBJ() || baseDealDealerBJPossible() == false) {
                    correct = false;
                    System.out.println("baseDeal incorrectly returns 3");
                }
            } else if (temp == 0) {
                if (baseDealPlayerBJ() || game.dealerHandString().equals("1/11")) {
                    correct = false;
                    System.out.println("baseDeal incorrectly returns 0");
                }
            } else {
                correct = false;
                System.out.println("baseDeal incorrectly returns " + temp);
            }
            game.resetHand();
        }
        assertEquals(true, correct);
    }
    public boolean baseDealPlayerBJ() {
        if (game.playerHandString().equals("11/21")) {
            return true;
        }
        return false;
    }
    public boolean baseDealDealerBJPossible() {
        if (game.dealerHandString().equals("10") || game.dealerHandString().equals("1/11")) {
            return true;
        }
        return false;
    }
    @Test
    public void playerActionDoubleTest() {
        game.setBetSize(10);
        game.playerAction("DOUBLE");
        assertEquals(20, game.getBetSize());
    }
    @Test
    public void playerActionStandTest() {
        game.baseDeal();
        String temp = game.playerHandString();
        assertEquals(game.playerAction("STAND"), temp);
    }
    @Test
    public void bothBlackjackCheckWithPossibleDealerBJTest() {
        boolean correct = true;
        for (int i = 0; i < 1000; i++) {
            game.addPlayerCard(10);
            game.addPlayerCard(1);
            game.addDealerCard(10);
            boolean temp = game.bothBlackjackCheck();
            if (temp == true) {
                if (!game.dealerHandString().equals("11/21")) {
                    System.out.println("bothBlackjackCheck incorrectly returns true");
                    correct = false;
                }
            } else {
                if (game.dealerHandString().equals("11/21")) {
                    System.out.println("bothBlackjackCheck incorrectly returns false");
                    correct = false;
                }
            }
            game.resetHand();
        }
        assertEquals(correct, true);
    }
    @Test
    public void bothBlackJackCheckWithNoPossibleDealerBJTest() {
        game.addPlayerCard(1);
        game.addPlayerCard(10);
        game.addDealerCard(7);
        assertEquals(false, game.bothBlackjackCheck());
    }
    @Test
    public void playerBlackjackTest() {
        game.setBetSize(10);
        game.playerBlackjack();
        assertEquals(1015, game.getPlayerCash());
    }
    @Test
    public void addPlayerCashTest() {
        game.addPlayerCash(1000);
        assertEquals(2000, game.getPlayerCash());
    }
    @Test
    public void playerWinTest() {
        game.setBetSize(100);
        game.playerWin();
        assertEquals(1100, game.getPlayerCash());
    }
    @Test
    public void playerLossTest() {
        game.setBetSize(200);
        game.playerLoss();
        assertEquals(800, game.getPlayerCash());
    }
    @Test
    public void playerSurrenderTest() {
        game.setBetSize(500);
        game.playerSurrender();
        assertEquals(750, game.getPlayerCash());
    }
    @Test
    public void isBustWhenBustTest() {
        game.addPlayerCard(10);
        game.addPlayerCard(6);
        game.addPlayerCard(9);
        assertEquals(true, game.isBust());
    }
    @Test
    public void isBustWhenNotBustTest() {
        game.addPlayerCard(10);
        game.addPlayerCard(5);
        assertEquals(false, game.isBust());
    }
    @Test
    public void insuranceLossWithInsuranceTest() {
        game.setInsurance(20);
        game.insuranceLoss();
        assertEquals(980, game.getPlayerCash());
    }
    @Test
    public void insuranceLossWithoutInsuranceTest() {
        game.setInsurance(-1);
        game.insuranceLoss();
        assertEquals(1000, game.getPlayerCash());
    }
    @Test
    public void insuranceWinWithInsuranceTest() {
        game.setInsurance(100);
        game.insuranceWin();
        assertEquals(1200, game.getPlayerCash());
    }
    @Test
    public void insuranceWinWithoutInsuranceTest() {
        game.setInsurance(-1);
        game.insuranceWin();
        assertEquals(1000, game.getPlayerCash());
    }
}