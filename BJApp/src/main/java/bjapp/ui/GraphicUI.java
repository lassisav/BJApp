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
        
        Label playerHand = new Label("Player hand: " + game.playerHandString());
        Label dealerHand = new Label("Dealer hand: " + game.dealerHandString());
        Button hitButton = new Button("HIT");
        Button standButton = new Button("STAND");
        
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
}
