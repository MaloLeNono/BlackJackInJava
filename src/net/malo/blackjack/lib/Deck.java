package net.malo.blackjack.lib;

import net.malo.blackjack.enums.Rank;
import net.malo.blackjack.enums.Suit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private final List<Card> deck = new ArrayList<>();

    public Deck() {
        for (Suit suit : Suit.values()) {
            int cardValue = 1;

            for (Rank rank : Rank.values()) {
                if (cardValue > 10)
                    cardValue = 10;

                deck.add(new Card(cardValue, rank, suit));
                cardValue++;
            }
        }
    }

    public List<Card> getCards() {
        return deck;
    }

    public List<Card> pullCards(int amount) {
        List<Card> cardsToReturn = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < amount; i++) {
            int indexToRemove = random.nextInt(deck.size());
            Card cardToRemove = deck.get(indexToRemove);
            deck.remove(cardToRemove);
            cardsToReturn.add(cardToRemove);
        }

        return cardsToReturn;
    }
}
