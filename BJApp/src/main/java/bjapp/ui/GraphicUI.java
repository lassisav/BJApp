/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bjapp.ui;

/**
 *
 * @author lassisav
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GraphicUI extends Application {
    
    Stage window;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) {
        window = stage;
        
        launch();
    }
    
    public void launch() {
        Button quitGame = new Button("QUIT");
        Button startRound = new Button("NEW GAME");
        
        quitGame.setOnAction((ActionEvent e) -> {
            System.exit(0);
        });
        startRound.setOnAction((ActionEvent e) -> {
            roundStart();
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
    public void roundStart() {
        HBox infoRow = new HBox();
        infoRow.getChildren().add(new Label("Cash: 1000"));
        infoRow.getChildren().add(new Label("    "));
        infoRow.getChildren().add(new Label("Bet: 10"));
        
        HBox dealerRow = new HBox();
        dealerRow.getChildren().add(new Label("Dealer cards:  "));
        
        HBox playerRow = new HBox();
        playerRow.getChildren().add(new Label("Player cards:  "));
        
        VBox roundStartComponents = new VBox();
        roundStartComponents.getChildren().add(infoRow);
        roundStartComponents.getChildren().add(dealerRow);
        roundStartComponents.getChildren().add(playerRow);
        
        Scene scene = new Scene(roundStartComponents, 1600, 1000);
        
        window.setScene(scene);
        window.setTitle("This is a blackjack application!");
        window.show();
    }
}
