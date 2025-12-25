package net.malo.blackjack;

import java.util.Scanner;

public class Main {
     public static void main(String[] args) {
        System.out.println(
                "Welcome to BlackJack in Java!\n" +
                "Type 'start' to start the game or anything else to exit.");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toLowerCase();

        if (!input.equals("start"))
            System.exit(0);

        Game game = new Game();
        game.run();
    }
}