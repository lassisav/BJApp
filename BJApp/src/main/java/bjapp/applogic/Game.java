/*
 * This class will handle the functions of a game round, and the game itself
 *
 */
package bjapp.applogic;

import java.util.Scanner;

/**
 *
 * @author Lassi Savolainen, student, University of Helsinki
 */
public class Game {
    Hand playerHand;
    Hand dealerHand;
    boolean moreCards;
    boolean dealerHits;
    String temp;
    int playerCash;
    int betSize;
    int playerBJ; // 0 = no BJ, no dealer BJ; 1 = BJ, dealer 10 or Ace; 2 = BJ, straight win; 3 = no BJ, dealer 10 or Ace
    int insurance;
    public Game(int playerCash) {
        playerHand = new Hand(false);
        dealerHand = new Hand(true);
        this.playerCash = playerCash;
    }
    public void resetHand() {
        playerHand.resetHand();
        dealerHand.resetHand();
        dealerHits = true;
        insurance = -1;
        betSize = -1;
        playerBJ = -1;
    }
    public int baseDeal() {
        playerHand.addRandomCard();
        dealerHand.addRandomCard();
        playerHand.addRandomCard();
        if (playerHandString().equals("11/21")) {
            if (dealerHandString().equals("10") || dealerHandString().equals("1/11")) {
                playerBJ = 1;
            } else {
                playerBJ = 2;
            }
        } else if (dealerHandString().equals("10") || dealerHandString().equals("1/11")) {
            playerBJ = 3;
        } else {
            playerBJ = 0;
        }
        return playerBJ;
    }
    public String playerAction(String type) {
        if (type.equals("DOUBLE")) {
            betSize *= 2;
        }
        if (!type.equals("STAND")) {
            playerHand.addRandomCard();
        }
        return playerHandString();
    }
    public int dealerAction() { //-2 = Dealer bust, -1 = Dealer stand, player win, 0 = Dealer stand, push, 1 = Dealer stand, player loss, 2 = Dealer BJ, player loss
        if (dealerHandString().equals("1/11") || dealerHandString().equals("10")) {
            dealerHand.addRandomCard();
            if (dealerHandString().equals("11/21")) {
                insuranceWin();
                if (playerBJ == 1 || playerBJ == 2) {
                    return 0;
                } else {
                    playerLoss();
                    return 2;
                }
            }
        }
        insuranceLoss();
        while (true) {
            if (dealerHand.getValue() > 21) {
                playerWin();
                return -2;
            } else if (dealerHand.getValue() > 16 || (dealerHand.hasAce() && dealerHand.getValue() > 6)) {
                int result = dealerHand.dealerWinCheck(playerHand);
                if (result == 1) {
                    playerLoss();
                } else if (result == -1) {
                    playerWin();
                }
                return result;
            }
            dealerHand.addRandomCard();
        }
    }
    public boolean bothBlackjackCheck() {
        dealerHand.addRandomCard();
        if (dealerHandString().equals("11/21")) {
            return true;
        }
        playerBlackjack();
        return false;
    }
    public void playerBlackjack() {
        playerCash += (betSize * 3 / 2);
    }
    public void playerWin() {
        playerCash += betSize;
    }
    public void playerLoss() {
        playerCash -= betSize;
    }
    public void insuranceLoss() {
        if (insurance != -1) {
            playerCash -= insurance;
            System.out.println("You have lost your insurance bet (" + insurance + "€).");
        }
    }
    public void insuranceWin() {
        if (insurance != -1) {
            playerCash += insurance * 2;
            System.out.println("Your insurance bet wins (" + insurance * 2 + ")");
        }
    }
    public int playerSurrender() {
        playerCash -= betSize/2;
        return betSize/2;
    }
    public String dealerHandString() {
        return dealerHand.getValueString();
    }
    public String playerHandString() {
        return playerHand.getValueString();
    }
    public int getPlayerCash() {
        return playerCash;
    }
    public void setBetSize(int betSize) {
        this.betSize = betSize;
    }
    public int getBetSize() {
        return betSize;
    }
    public void setInsurance(int insSize) {
        this.insurance = insSize;
    }
    public void addPlayerCash(int playerCash) {
        this.playerCash += playerCash;
    }
}
