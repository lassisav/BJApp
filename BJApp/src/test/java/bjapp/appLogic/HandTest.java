/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package bjapp.appLogic;

//import org.junit.AfterClass;
//import org.junit.BeforeClass;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lassisav
 */
public class HandTest {
    
    Hand playerHand;
    
    public HandTest() {
    }
    
/*  @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
*/    
    @Before
    public void setUp() {
        playerHand = new Hand(false);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void firstCardCorrectNonAce(){
        String value = playerHand.addCard(7);
        assertEquals("7", value);
    }
    
    @Test
    public void firstCardCorrectAce(){
        String value = playerHand.addCard(1);
        assertEquals("1/11", value);
    }
}
