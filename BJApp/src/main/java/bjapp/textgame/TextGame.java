/*
 * This class contains most of the textual interface of the text-based version of the game, and central game logic.
 * Game logic for modifying and comparing the dealer's and player's hands is in the Hand-class.
 * Game logic for moving from one phase of the game to another is in this class.
 * Known problems:
 * Game does not currently contain certain special actions (Double down, split, surrender). (Not yet implemented).
 */
package bjapp.textgame;

import bjapp.applogic.Hand;
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
    static int playerBJ; // 0 = no blackjack, 1 = blackjack, dealer 10 or Ace, 2 = blackjack, straight win
    static int insurance;
    
    public static void textGame() {
        System.out.println("This is the text based game version.\nPress ENTER to start.");
        scanner = new Scanner(System.in);
        temp = scanner.nextLine();
        dealerHand = new Hand(true);
        playerHand = new Hand(false);
        playerCash = 1000;
        boolean playing = true;
        while (playing) {
            betSelection();
            baseDeal();
            if (playerBJ == 0) {
                playerTurn();  
            }
            if (dealerHits) {
                dealerTurn();
            }
            playing = newGame();
        }
    }
    public static void betSelection() {
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
        dealerHits = true;
        insurance = -1;
        playerHand.addRandomCard();
        dealerHand.addRandomCard();
        playerHand.addRandomCard();
        if (playerHand.getValueString().equals("11/21")) {
            if (!(dealerHand.getValueString().equals("10") || dealerHand.getValueString().equals("1/11"))) {
                System.out.println("You have a BLACKJACK! You win " + (betSize * 3 / 2));
                playerBJ = 2;
                dealerHits = false;
                playerCash += betSize * 3 / 2;
            } else {
                System.out.println("You have a BLACKJACK!");
                playerBJ = 1;
            }
        } else {
            playerBJ = 0;
        }
    }
    public static void playerTurn() {
        moreCards = true;
        dealerHits = true;
        if (dealerHand.getValueString().equals("1/11")) {
            askInsurance();
        }
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
        boolean dealerBJ = false;
        if (dealerHand.getValueString().equals("10") || dealerHand.getValueString().equals("1/11")) {
            dealerBJ = true;
        }
        while (dealerHits) {
            temp = dealerHand.addRandomCard();
            if (dealerBJ && dealerHand.getValueString().equals("11/21")) {
                System.out.println("Dealer has BLACKJACK!");
                if (playerBJ == 1) {
                    System.out.println("Push!");
                } else {
                    System.out.println("Dealer wins! You lose " + betSize + "€!");
                    playerCash -= betSize;
                    if (insurance != -1) {
                        winInsurance();
                    }
                }
                break;
            } else {
                dealerBJ = false;
                if (insurance != -1) {
                    loseInsurance();
                }
            }
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
            return outOfMoney();
        }
        System.out.println("Type QUIT to quit playing, or NEW to play a new game.");
        while (true) {
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
    public static boolean outOfMoney() {
        System.out.println("Type MORE to add 1000€ and play a new game, or QUIT to quit playing.");
        while (true) {
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

    private static void askInsurance() {
        System.out.println("Would you like to buy insurance?");
        while (true) {
            temp = scanner.nextLine();
            if (temp.equals("NO")) {
                System.out.println("No insurance.");
                break;
            } else if (temp.equals("YES")) {
                System.out.println("Type insurance bet size (1-" + Math.min(betSize / 2, playerCash - betSize) + ")");
                while (true) {
                    temp = scanner.nextLine();
                    try {
                        int insSize = Integer.parseInt(temp);
                        if (insSize < 1 || insSize > betSize / 2) {
                            System.out.println("Please input a valid bet size.");
                        } else if (insSize > playerCash - betSize) {
                            System.out.println("Insufficient funds, please input a valid bet size.");
                        } else {
                            insurance = insSize;
                            System.out.println("You have placed an insurance bet for " + insurance + "€.");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please input a valid integer (1-" + playerCash + ").");
                    }
                }
                break;
            } else {
                System.out.println("Please type YES or NO");
            }
        }
    }

    private static void winInsurance() {
        System.out.println("Insurance bet wins! You win " + insurance * 2 + "€.");
        playerCash += insurance * 2;
    }

    private static void loseInsurance() {
        System.out.println("Insurance bet (" + insurance + "€) loses.");
        playerCash -= insurance;
    }
}