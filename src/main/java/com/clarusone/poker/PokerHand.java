package com.clarusone.poker;

public class PokerHand implements Comparable<PokerHand> {

    private final String fiveCards;

    public PokerHand(String fiveCards) {
        this.fiveCards = fiveCards;
    }

    @Override
    public int compareTo(PokerHand opponentHand) {
        /*
        TODO Delete the line below and implement this method to return a positive integer, a negative integer, or zero 
        if this hand is better than, worse than, or the same as the opponent's hand
         */
        throw new UnsupportedOperationException("Hand comparison not implemented");
    }

    public String getFiveCards() {
        return fiveCards;
    }
}
