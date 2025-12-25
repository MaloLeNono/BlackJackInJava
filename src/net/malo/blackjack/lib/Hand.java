package net.malo.blackjack.lib;

import net.malo.blackjack.enums.Rank;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Card> hand = new ArrayList<>();

    public List<Card> getHand() {
        return hand;
    }

    public void addCards(List<Card> cards) {
        hand.addAll(cards);
    }

    public int getSum() {
        int sum = 0;

        for (Card card : hand) {
            sum += card.value;

            if (card.rank == Rank.ACE && sum + 10 <= 21) {
                sum += 10;
            }
        }

        return sum;
    }
}
