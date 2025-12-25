package net.malo.blackjack;

import net.malo.blackjack.lib.Card;
import net.malo.blackjack.lib.Deck;
import net.malo.blackjack.lib.Hand;

import java.util.Scanner;

public class Game {
    private final Deck deck = new Deck();
    private final Hand playerHand = new Hand();
    private final Hand dealerHand = new Hand();
    private Card holeCard;
    private boolean gameOver = false;

    public void run() {
        dealCards();

        printCurrentCards(false);

        if (dealerHand.getSum() == 21) {
            push(true);
        }

        Scanner scanner = new Scanner(System.in);
        while (!gameOver) {
            System.out.println("What would you like to do? stand/hit");
            String choice = scanner.nextLine();

            switch (choice.toLowerCase()) {
                case "stand":
                    stand();
                    break;
                case "hit":
                    hit();
                    break;
                default:
                    System.out.println("Type either hit or stand.");
                    break;
            }
        }

        scanner.nextLine();
    }

    private void hit() {
        if (deck.getCards().isEmpty()) return;

        System.out.println("\n======== HIT ========\n");

        playerHand.addCards(deck.pullCards(1));

        printCurrentCards(false);

        checkGame(false);
    }

    private void stand() {
        gameOver = true;

        System.out.println("\n======== STOOD ========\n");

        printCurrentCards(true);

        while (dealerHand.getSum() < 17) {
            dealerHand.addCards(deck.pullCards(1));
            printCurrentCards(true);
        }

        checkGame(true);
    }

    private void dealCards() {
        playerHand.addCards(deck.pullCards(2));
        dealerHand.addCards(deck.pullCards(2));
        holeCard = dealerHand.getHand().getFirst();
    }

    private void bust() {
        gameOver = true;

        System.out.println("/!\\ BUST! /!\\\n");

        printCurrentCards(true);
    }

    private void win() {
        gameOver = true;

        System.out.println("/!\\ WIN! /!\\\n");

        printCurrentCards(true);
    }

    private void push(boolean dealerBlackjack) {
        gameOver = true;

        System.out.println("/!\\ PUSH! /!\\\n");

        printCurrentCards(true);

        if (dealerBlackjack)
            System.out.println("The dealer got a BlackJack at the beginning and refuses to let you play.\nHow nice!");
        else
            System.out.println("You and the dealer have the same amount of cards.");
    }

    private  void printCurrentCards(boolean revealHoleCard) {
        System.out.println("DEALER CARDS:");

        StringBuilder dealerCardsBuilder = new StringBuilder();
        dealerCardsBuilder.append(
                revealHoleCard
                        ? "Hole Card: " + holeCard.getName() + ";\n"
                        : "Hole Card: " + "__________;\n"
        );

        for (Card card : dealerHand.getHand()) {
            if (card.equals(holeCard))
                continue;

            dealerCardsBuilder
                    .append(card.getName())
                    .append(";\n");
        }

        if (revealHoleCard)
            dealerCardsBuilder
                    .append("Sum: ")
                    .append(dealerHand.getSum())
                    .append(";\n");

        System.out.println(dealerCardsBuilder);

        System.out.println("YOUR CARDS:");

        StringBuilder playerCards = new StringBuilder();
        for (Card card : playerHand.getHand()) {
            playerCards
                    .append(card.getName())
                    .append(";\n");
        }

        playerCards
                .append("Sum: ")
                .append(playerHand.getSum())
                .append(";\n");

        System.out.println(playerCards);
    }

    private void checkGame(boolean playerStood) {
        if (playerHand.getSum() > 21)
            bust();
        else if (playerHand.getSum() == 21 && dealerHand.getSum() < 21)
            win();
        else if (playerHand.getSum() == dealerHand.getSum())
            push(false);

        if (!playerStood) return;

        if (playerHand.getSum() > dealerHand.getSum() || dealerHand.getSum() > 21)
            win();
        else if (dealerHand.getSum() > playerHand.getSum())
            bust();
    }
}
