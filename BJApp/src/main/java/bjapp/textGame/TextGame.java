/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bjapp.textGame;

import java.util.Scanner;

/**
 *
 * @author lassisav
 */
public class TextGame {
    public static void textGame() {
        System.out.println("This is the text based game version.");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Press ENTER to start.");
            String temp = scanner.nextLine();
            //The following lines will be changed to game operations, as game operations are created
            System.out.println("YOU draw 9.");
            System.out.println("DEALER shows 7.");
            System.out.println("YOU draw 7.");
            System.out.println("YOU have 16.");
            while (true) {                
                System.out.println("Type STAND or HIT.");
                temp = scanner.nextLine();
                if (temp.equals("STAND")) {
                    System.out.println("YOU STAND.");
                    System.out.println("DEALER draws 9, DEALER has 16.");
                    System.out.println("DEALER draws 10, DEALER has 26.");
                    System.out.println("DEALER is BUST.");
                    System.out.println("YOU WIN!");
                    break;
                } else if (temp.equals("HIT")) {
                    System.out.println("YOU HIT.");
                    System.out.println("YOU draw 9, YOU have 25.");
                    System.out.println("YOU are BUST.");
                    System.out.println("DEALER WINS!");
                    break;
                } else {
                    System.out.println("Unacceptable input.");
                }
            }
        }
    }
}
