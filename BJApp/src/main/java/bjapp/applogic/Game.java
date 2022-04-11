/*
 * This class will handle the functions of a game round, and the game itself
 *
 */
package bjapp.applogic;

import java.util.Scanner;

/**
 *
 * @author lassisav
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
        } else if(dealerHandString().equals("10") || dealerHandString().equals("1/11")) {
            playerBJ = 3;
        } else {
            playerBJ = 0;
        }
        return playerBJ;
    }
    public void playerBlackjack() {
        playerCash += (betSize * 3 / 2);
    }
    public void playerWin() {
        playerCash += betSize;
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
    public void setInsurance(int insSize) {
        this.insurance = insSize;
    }
    
}