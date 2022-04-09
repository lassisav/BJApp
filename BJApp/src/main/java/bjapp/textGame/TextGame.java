/*
 * This class contains most of the textual interface of the text-based version of the game, and central game logic.
 * Game logic for modifying and comparing the dealer's and player's hands is in the Hand-class.
 * Game logic for moving from one phase of the game to another is in this class.
 * Known problems:
 * Game does not currently contain the concept of a Blackjack. (Not yet implemented)
 * Game does not currently contain special actions (Double down, split, insurance, surrender). (Not yet implemented).
 */
package bjapp.textGame;

import bjapp.appLogic.Hand;
import java.util.Scanner;

/**
 *
 * @author Lassi Savolainen, student, University of Helsinki
 */
public class TextGame {
    
    static Hand dealerHand;
    static Hand playerHand;
    static boolean moreCards;
    static boolean dealerHits;
    static Scanner scanner;
    static String temp;
    static int playerCash;
    static int betSize;
    
    public static void textGame() {
        System.out.println("This is the text based game version.");
        scanner = new Scanner(System.in);
        dealerHand = new Hand(true);
        playerHand = new Hand(false);
        playerCash = 1000;
        boolean playing = true;
        while (playing) {
            System.out.println("Press ENTER to start.");
            temp = scanner.nextLine();
            betSelection();
            baseDeal();
            playerTurn();
            if (dealerHits) {
                dealerTurn();
            }
            playing = newGame();
        }
    }
    public static void betSelection(){
        System.out.println("You have " + playerCash + "€ left. Type your betsize (1-" + playerCash + ").");
        while (true) {            
            temp = scanner.nextLine();
            try {
                betSize = Integer.parseInt(temp);
                if (betSize < 0 || betSize > playerCash) {
                    System.out.println("Please input a valid integer (1-" + playerCash + ").");
                } else {
                    System.out.println("You bet " + betSize + "€!");
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please input a valid integer (1-" + playerCash + ").");
            }
        }
    }
    public static void baseDeal() {
        playerHand.addRandomCard();
        dealerHand.addRandomCard();
        playerHand.addRandomCard();
    }
    public static void playerTurn() {
        moreCards = true;
        dealerHits = true;
        while (moreCards) {                
            System.out.println("Dealer has " + dealerHand.getValueString());
            System.out.println("You have " + playerHand.getValueString());
            System.out.println("Type HIT or STAND");
            temp = scanner.nextLine();
            if (temp.equals("HIT")) {
                playerHit();
            } else if (temp.equals("STAND")) {
                System.out.println("You choose to STAND!");
                moreCards = false;
            } else {
                System.out.println("Invalid input, please type HIT or STAND");
            }
        }
    }
    public static void dealerTurn() {
        System.out.println("Dealer takes cards!");
        while (dealerHits) {
            temp = dealerHand.addRandomCard();
            if (temp.endsWith("bust!")) {
                System.out.println("Dealer has " + temp);
                System.out.println("You win " + betSize + "€!");
                playerCash += betSize;
                dealerHits = false;
            } else {
                dealerHits = dealerHand.dealerHitCheck();
                if (!dealerHits) {
                    int winner = dealerHand.dealerWinCheck(playerHand);
                    if (winner == 1) {
                        System.out.println("Dealer wins! You lose " + betSize + "€!");
                        playerCash -= betSize;
                    } else if (winner == -1) {
                        System.out.println("You win " + betSize + "€!");
                        playerCash += betSize;
                    } else {
                        System.out.println("Push!");
                    }
                }
            }
        }
    }
    public static boolean newGame() {
        System.out.println("You have " + playerCash + "€ left.");
        if (playerCash == 0) {
            System.out.println("Type MORE to add 1000€ and play a new game, or QUIT to quit playing.");
            while(true) {
                temp = scanner.nextLine();
                if (temp.equals("QUIT")) {
                    return false;
                } else if (temp.equals("MORE")) {
                    playerCash = 1000;
                    playerHand.resetHand();
                    dealerHand.resetHand();
                    return true;
                } else {
                    System.out.println("Invalid input, please type MORE or QUIT.");
                }
            }
        }
        System.out.println("Type QUIT to quit playing, or NEW to play a new game.");
            while(true){
                temp = scanner.nextLine();
                if (temp.equals("QUIT")) {
                    return false;
                } else if (temp.equals("NEW")) {
                    playerHand.resetHand();
                    dealerHand.resetHand();
                    return true;
                } else {
                    System.out.println("Invalid input, please type QUIT or NEW.");
                }
            }
    }
    public static void playerHit() {
        System.out.println("You choose to HIT!");
        temp = playerHand.addRandomCard();
        if (temp.endsWith("bust!")) {
            System.out.println("You have " + temp);
            System.out.println("Dealer wins! You lose " + betSize + "€!");
            playerCash -= betSize;
            dealerHits = false;
            moreCards = false;
        } else {
            System.out.println("You have " + temp);
        }
    }
}
