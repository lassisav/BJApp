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
import javafx.stage.Stage;

public class GraphicUI extends Application {
    @Override
    public void start(Stage ikkuna) {
        ikkuna.setTitle("This is a blackjack application!");
        ikkuna.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
