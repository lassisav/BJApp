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
}
