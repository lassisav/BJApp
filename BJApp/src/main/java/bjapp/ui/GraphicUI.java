/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bjapp.ui;

/**
 *
 * @author lassisav
 */

import bjapp.applogic.Game;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GraphicUI extends Application {
    
    Stage window;
    Game game;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) {
        window = stage;
        game = new Game(1000);
        game.resetHand();
        launchUI();
    }
    
    public void launchUI() {
        Button quitGame = new Button("QUIT");
        Button startRound = new Button("NEW GAME");
        
        quitGame.setOnAction((ActionEvent e) -> {
            System.exit(0);
        });
        startRound.setOnAction((ActionEvent e) -> {
            startRound();
        });
        
        HBox welcomeMessage = new HBox();
        welcomeMessage.getChildren().add(new Label("Welcome to the game!"));
        
        HBox choices = new HBox();
        choices.getChildren().add(quitGame);
        choices.getChildren().add(startRound);
        
        VBox launchComponents = new VBox();
        launchComponents.getChildren().add(welcomeMessage);
        launchComponents.getChildren().add(choices);
        
        Scene scene = new Scene(launchComponents, 1600, 1000);
        
        window.setScene(scene);
        window.setTitle("This is a blackjack application!");
        window.show();
    }
    public void startRound() {
        TextField inputBetSize = new TextField();
        Button setBetSize = new Button("ENTER");
        Label errorMsg = new Label("");
        
        HBox infoRow = new HBox();
        infoRow.getChildren().add(new Label("Cash: " + game.getPlayerCash()));
        infoRow.getChildren().add(new Label("    "));
        infoRow.getChildren().add(new Label("Choose your betsize! (10-" + Math.min(500, (game.getPlayerCash() / 10) * 10) + ")"));
        
        HBox selection = new HBox();
        selection.getChildren().add(inputBetSize);
        selection.getChildren().add(setBetSize);
        
        VBox startRoundComponents = new VBox();
        startRoundComponents.getChildren().add(infoRow);
        startRoundComponents.getChildren().add(selection);
        startRoundComponents.getChildren().add(errorMsg);
        
        Scene scene = new Scene(startRoundComponents, 1600, 1000);
        
        setBetSize.setOnAction((ActionEvent e) -> {
            int input = Integer.parseInt(inputBetSize.getText());
            if (input % 10 == 0 && input <= game.getPlayerCash() && input <= 500) {
                game.setBetSize(input);
                playRound();
            } else {
                errorMsg.setText("Invalid input, please input a valid betsize.");
            }
        });
        
        window.setScene(scene);
        window.setTitle("This is a blackjack application!");
        window.show();
    }
    public void playRound() {
        int BJStatus = game.baseDeal();
        if (BJStatus == 1) {
            playRoundBothBlackjack();
        } else if (BJStatus == 2) {
            playRoundPlayerBlackjack();
        } else if (BJStatus == 3) {
            playRoundAskInsurance();
        } else if (BJStatus == 0) {
            playRoundNormal();
        }
        
    }
    public void playRoundBothBlackjack() {
        Label playerHand = new Label("Player hand: " + game.playerHandString());
        Label dealerHand = new Label("Dealer hand: " + game.dealerHandString());
        Label bothBJStatus = new Label("Player blackjack! Dealer blackjack possible.");
        Button continueButton = new Button("CONTINUE");
        Button roundOverButton = new Button("CONTINUE");
        
        HBox infoRow = new HBox();
        infoRow.getChildren().add(new Label("Cash: " + game.getPlayerCash()));
        infoRow.getChildren().add(new Label("    "));
        infoRow.getChildren().add(new Label("Bet: " + game.getBetSize()));
        
        HBox dealerRow = new HBox();
        dealerRow.getChildren().add(dealerHand);
        
        HBox playerRow = new HBox();
        playerRow.getChildren().add(playerHand);
        
        HBox buttonRow = new HBox();
        buttonRow.getChildren().add(continueButton);
        
        VBox playRound1Components = new VBox();
        playRound1Components.getChildren().add(infoRow);
        playRound1Components.getChildren().add(dealerRow);
        playRound1Components.getChildren().add(playerRow);
        playRound1Components.getChildren().add(buttonRow);
        
        continueButton.setOnAction((ActionEvent e) -> {
            boolean bothBJ = game.bothBlackjackCheck();
            dealerHand.setText("Dealer hand: " + game.dealerHandString());
            if (bothBJ) {
                bothBJStatus.setText("Player blackjack! Dealer blackjack! Push!");
            } else {
                bothBJStatus.setText("Player blackjack! Player wins " + game.getBetSize() * 3 / 2 + "!");
                game.playerBlackjack();
            }
            buttonRow.getChildren().remove(0);
            buttonRow.getChildren().add(roundOverButton);
        });
        
        roundOverButton.setOnAction((ActionEvent e) -> {
            game.resetHand();
            quitOrContinue();
        });
        
        Scene scene = new Scene(playRound1Components, 1600, 1000);
        
        window.setScene(scene);
        window.setTitle("This is a blackjack application!");
        window.show();
    }

    public void playRoundPlayerBlackjack() {
        Label playerHand = new Label("Player hand: " + game.playerHandString());
        Label dealerHand = new Label("Dealer hand: " + game.dealerHandString());
        Button continueButton = new Button("CONTINUE");
        
        HBox infoRow = new HBox();
        infoRow.getChildren().add(new Label("Cash: " + game.getPlayerCash()));
        infoRow.getChildren().add(new Label("    "));
        infoRow.getChildren().add(new Label("Bet: " + game.getBetSize()));
        
        HBox dealerRow = new HBox();
        dealerRow.getChildren().add(dealerHand);
        
        HBox playerRow = new HBox();
        playerRow.getChildren().add(playerHand);
        
        HBox buttonRow = new HBox();
        buttonRow.getChildren().add(continueButton);
        
        VBox playRound2Components = new VBox();
        playRound2Components.getChildren().add(infoRow);
        playRound2Components.getChildren().add(dealerRow);
        playRound2Components.getChildren().add(playerRow);
        playRound2Components.getChildren().add(new Label("Player blackjack! Dealer blackjack not possible!"));
        playRound2Components.getChildren().add(new Label("Player wins " + (game.getBetSize() * 3 / 2) + "!"));
        playRound2Components.getChildren().add(buttonRow);
        
        continueButton.setOnAction((ActionEvent e) -> {
            game.playerBlackjack();
            game.resetHand();
            quitOrContinue();
        });
        
        Scene scene = new Scene(playRound2Components, 1600, 1000);
        
        window.setScene(scene);
        window.setTitle("This is a blackjack application!");
        window.show();
    }
    public void playRoundAskInsurance(){
        Label playerHand = new Label("Player hand: " + game.playerHandString());
        Label dealerHand = new Label("Dealer hand: " + game.dealerHandString());
        Button yesButton = new Button("YES");
        Button noButton = new Button("NO");
        
        HBox infoRow = new HBox();
        infoRow.getChildren().add(new Label("Cash: " + game.getPlayerCash()));
        infoRow.getChildren().add(new Label("    "));
        infoRow.getChildren().add(new Label("Bet: " + game.getBetSize()));
        
        HBox dealerRow = new HBox();
        dealerRow.getChildren().add(dealerHand);
        
        HBox playerRow = new HBox();
        playerRow.getChildren().add(playerHand);
        
        HBox buttonRow = new HBox();
        buttonRow.getChildren().add(yesButton);
        buttonRow.getChildren().add(noButton);
        
        VBox askInsuranceComponents = new VBox();
        askInsuranceComponents.getChildren().add(infoRow);
        askInsuranceComponents.getChildren().add(dealerRow);
        askInsuranceComponents.getChildren().add(playerRow);
        askInsuranceComponents.getChildren().add(new Label("Would you like to buy insurance?"));
        askInsuranceComponents.getChildren().add(buttonRow);
        
        Scene scene = new Scene(askInsuranceComponents, 1600, 1000);
        
        yesButton.setOnAction((ActionEvent e) -> {
            game.setInsurance(game.getBetSize() / 2);
            playRoundNormal();
        });
        
        noButton.setOnAction((ActionEvent e) -> {
            playRoundNormal();
        });
        
        window.setScene(scene);
        window.setTitle("This is a blackjack application!");
        window.show();
    }
    public void playRoundNormal() {
        Label playerHand = new Label("Player hand: " + game.playerHandString());
        Label dealerHand = new Label("Dealer hand: " + game.dealerHandString());
        Button hitButton = new Button("HIT");
        Button standButton = new Button("STAND");
        Button doubleButton = new Button("DOUBLE");
        Button surrenderButton = new Button("SURRENDER");
        
        HBox infoRow = new HBox();
        infoRow.getChildren().add(new Label("Cash: " + game.getPlayerCash()));
        infoRow.getChildren().add(new Label("    "));
        infoRow.getChildren().add(new Label("Bet: " + game.getBetSize()));
        
        HBox dealerRow = new HBox();
        dealerRow.getChildren().add(dealerHand);
        
        HBox playerRow = new HBox();
        playerRow.getChildren().add(playerHand);
        
        HBox buttonRow = new HBox();
        buttonRow.getChildren().add(hitButton);
        buttonRow.getChildren().add(standButton);
        if (game.getPlayerCash() >= game.getBetSize() * 2) {
            buttonRow.getChildren().add(doubleButton);
        }
        buttonRow.getChildren().add(surrenderButton);
        
        hitButton.setOnAction((ActionEvent e) -> {
            playerHand.setText("Player hand: " + game.playerAction("HIT"));
            if (game.isBust()) {
                game.playerLoss();
                playRoundBust();
            }
            buttonRow.getChildren().remove(3);
            buttonRow.getChildren().remove(2);
        });
        standButton.setOnAction((ActionEvent e) -> {
            playRoundStand();
        });
        doubleButton.setOnAction((ActionEvent e) -> {
            playerHand.setText("Player hand: " + game.playerAction("DOUBLE"));
            if (game.isBust()) {
                game.playerLoss();
                playRoundBust();
            }
            playRoundStand();
        });
        surrenderButton.setOnAction((ActionEvent e) -> {
            playRoundSurrender();
        });
        
        VBox playRoundComponents = new VBox();
        playRoundComponents.getChildren().add(infoRow);
        playRoundComponents.getChildren().add(dealerRow);
        playRoundComponents.getChildren().add(playerRow);
        playRoundComponents.getChildren().add(buttonRow);
        
        Scene scene = new Scene(playRoundComponents, 1600, 1000);
        
        window.setScene(scene);
        window.setTitle("This is a blackjack application!");
        window.show();
    }
    public void playRoundSurrender() {
        game.playerSurrender();
        int insurance = game.bustOrSurrenderInsurance();
        
        Label playerHand = new Label("Player hand: " + game.playerHandString());
        Label dealerHand = new Label("Dealer hand: " + game.dealerHandString());
        Label resultLabel = new Label("Player surrenders. Player loses " +  game.getBetSize() / 2 + "!");
        Label insuranceLabel = new Label("");
        Button continueButton = new Button("CONTINUE");
        
        if (insurance == -1) {
            insuranceLabel.setText("Insurance (" + game.getInsurance() + ") loses!");
        } else if (insurance == 1) {
            insuranceLabel.setText("Insurance wins " + (game.getInsurance() * 2) + "!");
        }
        
        continueButton.setOnAction((ActionEvent e) -> {
            quitOrContinue();
        });
        
        HBox infoRow = new HBox();
        infoRow.getChildren().add(new Label("Cash: " + game.getPlayerCash()));
        infoRow.getChildren().add(new Label("    "));
        infoRow.getChildren().add(new Label("Bet: " + game.getBetSize()));
        
        HBox dealerRow = new HBox();
        dealerRow.getChildren().add(dealerHand);
        
        HBox playerRow = new HBox();
        playerRow.getChildren().add(playerHand);
        
        HBox buttonRow = new HBox();
        buttonRow.getChildren().add(continueButton);
        
        VBox playRoundSurrenderComponents = new VBox();
        playRoundSurrenderComponents.getChildren().add(infoRow);
        playRoundSurrenderComponents.getChildren().add(dealerRow);
        playRoundSurrenderComponents.getChildren().add(playerRow);
        playRoundSurrenderComponents.getChildren().add(resultLabel);
        if (insurance != 0) {
            playRoundSurrenderComponents.getChildren().add(insuranceLabel);
        }
        playRoundSurrenderComponents.getChildren().add(buttonRow);
        
        Scene scene = new Scene(playRoundSurrenderComponents, 1600, 1000);
        
        window.setScene(scene);
        window.setTitle("This is a blackjack application!");
        window.show();
    }
    public void playRoundBust() {
        int insurance = game.bustOrSurrenderInsurance();
        
        Label playerHand = new Label("Player hand: " + game.playerHandString());
        Label dealerHand = new Label("Dealer hand: " + game.dealerHandString());
        Label resultLabel = new Label("Player busts. Player loses " +  game.getBetSize() + "!");
        Label insuranceLabel = new Label("");
        Button continueButton = new Button("CONTINUE");
        
        if (insurance == -1) {
            insuranceLabel.setText("Insurance (" + game.getInsurance() + ") loses!");
        } else if (insurance == 1) {
            insuranceLabel.setText("Insurance wins " + (game.getInsurance() * 2) + "!");
        }
        
        continueButton.setOnAction((ActionEvent e) -> {
            quitOrContinue();
        });
        
        HBox infoRow = new HBox();
        infoRow.getChildren().add(new Label("Cash: " + game.getPlayerCash()));
        infoRow.getChildren().add(new Label("    "));
        infoRow.getChildren().add(new Label("Bet: " + game.getBetSize()));
        
        HBox dealerRow = new HBox();
        dealerRow.getChildren().add(dealerHand);
        
        HBox playerRow = new HBox();
        playerRow.getChildren().add(playerHand);
        
        HBox buttonRow = new HBox();
        buttonRow.getChildren().add(continueButton);
        
        VBox playRoundBustComponents = new VBox();
        playRoundBustComponents.getChildren().add(infoRow);
        playRoundBustComponents.getChildren().add(dealerRow);
        playRoundBustComponents.getChildren().add(playerRow);
        playRoundBustComponents.getChildren().add(resultLabel);
        if (insurance != 0) {
            playRoundBustComponents.getChildren().add(insuranceLabel);
        }
        playRoundBustComponents.getChildren().add(buttonRow);
        
        Scene scene = new Scene(playRoundBustComponents, 1600, 1000);
        
        window.setScene(scene);
        window.setTitle("This is a blackjack application!");
        window.show();
    }
    public void playRoundStand() {
        int result = game.dealerAction();
        boolean insurance = game.getInsurance() != -1;
        
        Label playerHand = new Label("Player hand: " + game.playerHandString());
        Label dealerHand = new Label("Dealer hand: " + game.dealerHandString());
        Label resultLabel = new Label("");
        Label insuranceLabel = new Label("");
        Button continueButton = new Button("CONTINUE");
        
        if (result == -2) {
            resultLabel.setText("Dealer bust! Player wins " + game.getBetSize() + "!");
            if (insurance) {
                insuranceLabel.setText("Insurance (" + game.getInsurance() + ") loses!");
            }
        } else if (result == -1) {
            resultLabel.setText("Dealer stands! Player wins " + game.getBetSize() + "!");
            if (insurance) {
                insuranceLabel.setText("Insurance (" + game.getInsurance() + ") loses!");
            }
        } else if (result == 0) {
            resultLabel.setText("Dealer stands! Push!");
            if (insurance) {
                insuranceLabel.setText("Insurance (" + game.getInsurance() + ") loses!");
            }
        } else if (result == 1) {
            resultLabel.setText("Dealer stands! Player loses " + game.getBetSize() + "!");
            if (insurance) {
                insuranceLabel.setText("Insurance(" + game.getInsurance() + ") loses!");
            }
        } else if (result == 2) {
            resultLabel.setText("Dealer blackjack! Player loses " + game.getBetSize() + "!");
            if (insurance) {
                insuranceLabel.setText("Insurance wins " + game.getInsurance() * 2 + "!");
            }
        }
        
        continueButton.setOnAction((ActionEvent e) -> {
            quitOrContinue();
        });
        
        HBox infoRow = new HBox();
        infoRow.getChildren().add(new Label("Cash: " + game.getPlayerCash()));
        infoRow.getChildren().add(new Label("    "));
        infoRow.getChildren().add(new Label("Bet: " + game.getBetSize()));
        
        HBox dealerRow = new HBox();
        dealerRow.getChildren().add(dealerHand);
        
        HBox playerRow = new HBox();
        playerRow.getChildren().add(playerHand);
        
        HBox buttonRow = new HBox();
        buttonRow.getChildren().add(continueButton);
        
        VBox playRoundStandComponents = new VBox();
        playRoundStandComponents.getChildren().add(infoRow);
        playRoundStandComponents.getChildren().add(dealerRow);
        playRoundStandComponents.getChildren().add(playerRow);
        playRoundStandComponents.getChildren().add(resultLabel);
        if (insurance) {
            playRoundStandComponents.getChildren().add(insuranceLabel);
        }
        playRoundStandComponents.getChildren().add(buttonRow);
        
        Scene scene = new Scene(playRoundStandComponents, 1600, 1000);
        
        window.setScene(scene);
        window.setTitle("This is a blackjack application!");
        window.show();
    }
    public void quitOrContinue() {
        
        HBox buttonRow = new HBox();
        Button quitButton = new Button("QUIT");
        Button newGameButton = new Button("NEW GAME");
        buttonRow.getChildren().add(quitButton);
        buttonRow.getChildren().add(newGameButton);
        
        quitButton.setOnAction((ActionEvent e) -> {
            System.exit(0);
        });
        newGameButton.setOnAction((ActionEvent e) -> {
            game.resetHand();
            startRound();
        });
        
        VBox QOCComponents = new VBox();
        QOCComponents.getChildren().add(new Label("Cash: " + game.getPlayerCash()));
        QOCComponents.getChildren().add(buttonRow);
        
        Scene scene = new Scene(QOCComponents, 1600, 1000);
        
        window.setScene(scene);
        window.setTitle("This is a blackjack application!");
        window.show();
    }
}
