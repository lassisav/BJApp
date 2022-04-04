/*
 * This class contains most of the textual interface of the text-based version of the game, and central game logic.
 * Game logic for modifying and comparing the dealer's and player's hands is in the Hand-class.
 * Game logic for moving from one phase of the game to another is in this class.
 * Known problems:
 * Game does not currently contain the concept of a Blackjack. (Not yet implemented)
 * Game does not currently contain the concept of betting. (Not yet implemented)
 * Game does not currently contain special actions (Double down, split, insurance, surrender). (Not yet implemented).
 * Central game logic is built into a single method, so it is not possible to unit test the transition from one game phase to another.
 * Instead, directions given to the game by methods in the Hand-class can be unit tested to measure accuracy.
 */
package bjapp.textGame;

import bjapp.appLogic.Hand;
import java.util.Scanner;

/**
 *
 * @author Lassi Savolainen, student, University of Helsinki
 */
public class TextGame {
    public static void textGame() {
        System.out.println("This is the text based game version.");
        Scanner scanner = new Scanner(System.in);
        Hand dealerHand = new Hand(true);
        Hand playerHand = new Hand(false);
        String temp;
        boolean moreCards;
        boolean dealerHits;
        boolean playing = true;
        while (playing) {
            System.out.println("Press ENTER to start.");
            temp = scanner.nextLine();
            //The following lines will be changed to game operations, as game operations are created
            //Base deal
            playerHand.addRandomCard();
            dealerHand.addRandomCard();
            playerHand.addRandomCard();
            //Additional cards
            moreCards = true;
            dealerHits = true;
            while (moreCards) {                
                System.out.println("Dealer has " + dealerHand.getValueString());
                System.out.println("You have " + playerHand.getValueString());
                System.out.println("Type HIT or STAND");
                temp = scanner.nextLine();
                if (temp.equals("HIT")) {
                    System.out.println("You choose to HIT!");
                    temp = playerHand.addRandomCard();
                    if (temp.endsWith("bust!")) {
                        System.out.println("You have " + temp);
                        System.out.println("Dealer wins!");
                        dealerHits = false;
                        moreCards = false;
                    } else {
                        System.out.println("You have " + temp);
                    }
                } else if (temp.equals("STAND")) {
                    System.out.println("You choose to STAND!");
                    moreCards = false;
                } else {
                    System.out.println("Invalid input, please type HIT or STAND");
                }
            }
            //Dealer takes cards
            System.out.println("Dealer takes cards!");
            while (dealerHits) {
                temp = dealerHand.addRandomCard();
                if (temp.endsWith("bust!")) {
                    System.out.println("Dealer has " + temp);
                    System.out.println("You win!");
                    dealerHits = false;
                } else {
                    dealerHits = dealerHand.dealerHitCheck();
                    if (!dealerHits) {
                        int winner = dealerHand.dealerWinCheck(playerHand);
                        if (winner == 1) {
                            System.out.println("Dealer wins!");
                        } else if (winner == -1) {
                            System.out.println("You win!");
                        } else {
                            System.out.println("Push!");
                        }
                    }
                }
            }
            //End
            System.out.println("Type QUIT to quit playing, or NEW to play a new game.");
            boolean newGameLoop = true;
            while(newGameLoop){
                temp = scanner.nextLine();
                if (temp.equals("QUIT")) {
                    playing = false;
                    newGameLoop = false;
                } else if (temp.equals("NEW")) {
                    playerHand.resetHand();
                    dealerHand.resetHand();
                    newGameLoop = false;
                } else {
                    System.out.println("Invalid input, please type QUIT or NEW.");
                }
            }
        }
    }
}
