/*
 * This class is used to represent a hand. The hand can be a dealer's hand, or a player's hand.
 * Methods in this class are used to modify and compare the state of a hand during the game.
 */
package bjapp.applogic;

/**
 *
 * @author Lassi Savolainen, student, University of Helsinki
 */
public class Hand {
    int value;
    boolean hasAce;
    boolean isDealer;
    public Hand(boolean status) {
        value = 0;
        hasAce = false;
        isDealer = status;
    }
    public void resetHand() {
        value = 0;
        hasAce = false;
    }
    public String addCard(int card) {
        if (isDealer) {
            System.out.print("Dealer draws ");
        } else {
            System.out.print("You draw ");
        }
        if (card == 1) {
            hasAce = true;
            System.out.println("Ace");
        } else {
            System.out.println(card);
        }
        value += card;
        return getValueString();
    }
    public String addRandomCard() {
        return this.addCard(randomCardValue());
    }
    public int randomCardValue() {
        int card = 1 + (int) (Math.random() * 13);
        if (card > 9) {
            return 10;
        }
        return card;
    }
    public String getValueString() {
        if (value > 21) {
            return String.valueOf(value) +  ", bust!";
        } else if (hasAce && value < 12) {
            String temp = String.valueOf(value) + "/" + String.valueOf(value + 10);
            return temp;
        } else {
            return String.valueOf(value);
        }
    }
    public int getValue() {
        return value;
    }
    public boolean dealerHitCheck() {
        if (value > 16) {
            return false;
        }
        if (hasAce && value > 6 && value < 12) {
            return false;
        }
        return true;
    }
    public int dealerWinCheck(Hand playerHand) {
        int dealerTrue = value;
        if (hasAce && dealerTrue < 12) {
            dealerTrue += 10;
        }
        int playerTrue = playerHand.getValue();
        if (playerHand.hasAce && playerTrue < 12) {
            playerTrue += 10;
        }
        System.out.println("Dealer has " + dealerTrue + "!");
        System.out.println("You have " + playerTrue + "!");
        if (dealerTrue == playerTrue) {
            return 0;
        } else if (dealerTrue > playerTrue) {
            return 1;
        } else {
            return -1;
        }
    }
}
