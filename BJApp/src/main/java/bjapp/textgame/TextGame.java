/*
 * This class contains most of the textual interface of the text-based version of the game, and central game logic.
 * Game logic for modifying and comparing the dealer's and player's hands is in the Hand-class.
 * Game logic for moving from one phase of the game to another is in this class.
 * Known problems:
 * Game does not currently contain splitting. (Not yet implemented).
 */
package bjapp.textgame;

import bjapp.applogic.Game;
import java.util.Scanner;

/**
 *
 * @author Lassi Savolainen, student, University of Helsinki
 */
public class TextGame {
    
    static boolean moreCards;
    static boolean dealerHits;
    static boolean playing;
    static Scanner scanner;
    static String temp;
    static int playerBJ; // 0 = no blackjack, 1 = blackjack, dealer 10 or Ace, 2 = blackjack, straight win, 3 = dealerBJ possible
    static public Game game;
    
    public TextGame() {
        scanner = new Scanner(System.in);
        game = new Game(1000);
    }
    public void textGame() {
        firstInit();
        while (playing) {
            betSelection();
            baseDeal();
            if (playerBJ == 0 || playerBJ == 3) {
                playerTurn();
            }
            if (dealerHits) {
                dealerTurn();
            }
            playing = newGame();
        }
    }
    public void firstInit() {
        System.out.println("This is the text based game version.\nPress ENTER to start.");
        temp = scanner.nextLine();
        playing = true;
    }
    public void betSelection() {
        game.resetHand();
        System.out.println("You have " + game.getPlayerCash() + "€ left. Type your betsize (1-" + game.getPlayerCash() + ").");
        while (true) {            
            temp = scanner.nextLine();
            try {
                int betSize = Integer.parseInt(temp);
                if (betSize < 0 || betSize > game.getPlayerCash()) {
                    System.out.println("Please input a valid integer (1-" + game.getPlayerCash() + ").");
                } else {
                    System.out.println("You bet " + betSize + "€!");
                    game.setBetSize(betSize);
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please input a valid integer (1-" + game.getPlayerCash() + ").");
            }
        }
    }
    public void baseDeal() {
        playerBJ = game.baseDeal();
        System.out.println("Dealer has " + game.dealerHandString());
        System.out.println("You have " + game.playerHandString());
        if (playerBJ == 3) {
            if (game.dealerHandString().equals("1/11")) {
                askInsurance();
            }
            dealerHits = true;
        } else if (playerBJ == 2) {
            System.out.println("You have BLACKJACK!");
            System.out.println("You win " + game.getBetSize() * 3 / 2 + "€.");
            game.playerBlackjack();
            dealerHits = false;
        } else if (playerBJ == 1) {
            if (game.dealerHandString().equals("1/11")) {
                askEvenMoney();
                boolean bothBlackjackCheck = game.bothBlackjackCheck();
                if (bothBlackjackCheck) {
                    System.out.println("Push!");
                } else {
                    System.out.println("You have BLACKJACK, dealer has " + game.dealerHandString());
                    System.out.println("You win " + (game.getBetSize() * 3 / 2) + "€.");
                }
                dealerHits = false;
            }
        } else {
            dealerHits = true;
        }
    }
    private void askInsurance() {
        System.out.println("Would you like to buy insurance? YES or NO");
        while (true) {
            temp = scanner.nextLine();
            if (temp.equals("NO")) {
                System.out.println("No insurance.");
                game.setInsurance(-1);
                break;
            } else if (temp.equals("YES")) {
                System.out.println("Type insurance size (1-" + Math.min(game.getBetSize() / 2, game.getPlayerCash() - game.getBetSize()) + ")");
                while (true) {
                    temp = scanner.nextLine();
                    try {
                        int insSize = Integer.parseInt(temp);
                        if (insSize < 1 || insSize > game.getBetSize() / 2) {
                            System.out.println("Please input a valid bet size.");
                        } else if (insSize > game.getPlayerCash() - game.getBetSize()) {
                            System.out.println("Insufficient funds, please input a valid bet size.");
                        } else {
                            System.out.println("You have placed an insurance bet for " + insSize + "€.");
                            game.setInsurance(insSize);
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please input a valid integer (1-" + game.getPlayerCash() + ").");
                    }
                }
                break;
            } else {
                System.out.println("Invalid input, please type YES or NO");
            }
        }
    }
    private void askEvenMoney() {
        System.out.println("You have BLACKJACK!\nWould you like to take even money? YES or NO");
        while (true) {
            temp = scanner.nextLine();
            if (temp.equals("YES")) {
                System.out.println("You have chosen to take even money");
                game.playerWin();
                dealerHits = false;
            } else if (temp.equals("NO")) {
                System.out.println("No even money");
                dealerHits = true;
                break;
            } else {
                System.out.println("Invalid input, please type YES or NO");
            }
        }
    }
    public void playerTurn() {
        moreCards = true;
        System.out.println("Type HIT, STAND, DOUBLE or SURRENDER");
        String nextChoice = playerTurnInput(true);
        if (nextChoice.equals("DOUBLE")) {
            playerDouble();
        } else if (nextChoice.equals("SURRENDER")) {
            playerSurrender();
        } else {
            while (moreCards) {
                if (nextChoice.equals("HIT")) {
                    playerHit();
                } else {
                    moreCards = false;
                    System.out.println("You choose to STAND!");
                    break;
                }
                if (moreCards) {
                    System.out.println("You have " + game.playerHandString());
                    System.out.println("Type HIT or STAND");
                    nextChoice = playerTurnInput(false);
                }
            }
        }
    }
    public String playerTurnInput(boolean canDouble) {
        while (true) {
            temp = scanner.nextLine();
            if (temp.equals("DOUBLE")) {
                if (canDouble) {
                    if (game.getPlayerCash() > game.getBetSize() * 2) {
                        return "DOUBLE";
                    } else {
                        System.out.println("Insufficient funds to DOUBLE.\nplease choose HIT or STAND");
                    }
                } else {
                    System.out.println("You can only DOUBLE after your first two cards.\nPlease choose HIT or STAND");
                }
            } else if (temp.equals("SURRENDER")) {
                if (canDouble) {
                    return "SURRENDER";
                } else {
                    System.out.println("Invalid input, please choose HIT or STAND");
                }
            } else if (temp.equals("HIT")) {
                return "HIT";
            } else if (temp.equals("STAND")) {
                return "STAND";
            } else if (canDouble) {
                System.out.println("Invalid input, please choose HIT, STAND, DOUBLE or SURRENDER");
            } else {
                System.out.println("Invalid input, please choose HIT or STAND");
            }
        }
    }
    public void playerDouble() {
        System.out.println("You choose to DOUBLE!");
        temp = game.playerAction("DOUBLE");
        System.out.println("You have " + temp);
        if (temp.endsWith("bust!")) {
            System.out.println("Dealer wins! You lose " + game.getBetSize() + "€!");
            game.playerLoss();
            dealerHits = false;
            moreCards = false;
            game.bustInsurance();
        }
    }
    public void playerSurrender() {
        System.out.println("You choose to SURRENDER");
        int loss = game.playerSurrender();
        System.out.println("You lose half of your bet. " + loss + "€");
        dealerHits = false;
        moreCards = false;
        game.bustInsurance();
    }
    public void playerHit() {
        System.out.println("You choose to HIT!");
        temp = game.playerAction("HIT");
        System.out.println("You have " + temp);
        if (temp.endsWith("bust!")) {
            System.out.println("Dealer wins! You lose " + game.getBetSize() + "€!");
            game.playerLoss();
            dealerHits = false;
            moreCards = false;
            game.bustInsurance();
        }
    }
    public void dealerTurn() {
        System.out.println("Dealer takes cards!");
        int result = game.dealerAction();
        if (result == -2) {
            System.out.println("Dealer busts! You win " + game.getBetSize() + "€!");
        } else if (result == -1) {
            System.out.println("You win " + game.getBetSize() + "€!");
        } else if (result == 1) {
            System.out.println("Dealer wins! You lose " + game.getBetSize() + "€.");
        } else if (result == 2) {
            System.out.println("Dealer has BLACKJACK! You lose " + game.getBetSize());
        } else {
            System.out.println("Push!");
        }
    }
    public boolean newGame() {
        System.out.println("You have " + game.getPlayerCash() + "€ left.");
        if (game.getPlayerCash() == 0) {
            return outOfMoney();
        }
        System.out.println("Type QUIT to quit playing, or NEW to play a new game.");
        while (true) {
            temp = scanner.nextLine();
            if (temp.equals("QUIT")) {
                return false;
            } else if (temp.equals("NEW")) {
                return true;
            } else {
                System.out.println("Invalid input, please type QUIT or NEW.");
            }
        }
    }
    public boolean outOfMoney() {
        System.out.println("Type MORE to add 1000€ and play a new game, or QUIT to quit playing.");
        while (true) {
            temp = scanner.nextLine();
            if (temp.equals("QUIT")) {
                return false;
            } else if (temp.equals("MORE")) {
                game.addPlayerCash(1000);
                return true;
            } else {
                System.out.println("Invalid input, please type MORE or QUIT.");
            }
        }
    }
}
