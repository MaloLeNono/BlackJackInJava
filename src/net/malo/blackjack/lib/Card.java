package net.malo.blackjack.lib;

import net.malo.blackjack.enums.Rank;
import net.malo.blackjack.enums.Suit;

import java.util.Objects;

public class Card {
    public int value;
    public Rank rank;
    public Suit suit;

    public Card(int value, Rank rank, Suit suit) {
        this.value = value;
        this.rank = rank;
        this.suit = suit;
    }

    public String getName() {
        return rank + " OF " + suit;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return value == card.value && rank == card.rank && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, rank, suit);
    }
}
