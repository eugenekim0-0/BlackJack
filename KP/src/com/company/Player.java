package com.company;

import java.util.ArrayList;

public class Player {
    private ArrayList<String> open;
    private String closed;
    private int total;
    private int aceCount;
    private double bustPercent;


    /**
     * Constructor for Player object
     */
    public Player() {
        this.open = new ArrayList<String>();
        this.closed = null;
        this.total = 0;
        this.aceCount = 0;
        this.bustPercent = 0;
    }

    //Methods for open
    /**
     * Returns the open cards of the Player's hand
     * @return open
     */
    public ArrayList<String> getOpen() {
        return this.open;
    }

    /**
     * Adds a new card to the player's open hand
     * @param newCard
     */
    public void addOpen(String newCard) {
        this.open.add(newCard);
        this.total += convertPoints(newCard);
        this.checkAce();
        this.calcBustPercent();
    }

    /**
     * Sets the open hand as an empty ArrayList
     */
    public void emptyOpen() {
        this.open = new ArrayList<>();
    }

    //Methods for closed
    /**
     * Returns the player's closed card
     * @return
     */
    public String getClosed() {
        return this.closed;
    }

    /**
     * Sets a newCard as the player's closed card
     * @param newCard
     */
    public void setClosed(String newCard) {
        this.closed = newCard;
        this.total += convertPoints(newCard);
    }

    //Methods for total
    /**
     * Returns the player's total points based off of their hand
     * @return
     */
    public int getTotal() {
        return this.total;
    }

    /**
     * Sets the newTotal as the player's total
     * @param newTotal
     */
    public void setTotal(int newTotal) {
        this.total = newTotal;
    }

    /**
     * Reverts the player's Ace(s) from 11 to 1 if player would bust otherwise
     * Only done if player has any more Ace(s) left to change back based on the aceCount
     */
    public void checkAce() {
        for (int a = 0; a < this.aceCount; a++) {
            if (this.total > 21) {
                this.total -= 10;
                this.aceCount--;
            }
        }
    }


    //Methods for bustPercent
    /**
     * Returns the bust percent of the player
     * @return
     */
    public double getBustPercent() {
        return this.bustPercent;
    }

    /**
     * Calculates the bust percent of a player's hand, specifically the dealer's,
     * to determine if they should hit or stand
     * @source: https://www.blackjack.org/blackjack-strategy/
     */
    public void calcBustPercent() {
        if (this.total <= 11) {
            this.bustPercent = 0.0;
        } else if (this.total == 12) {
            this.bustPercent = 0.31;
        } else if (this.total == 13) {
            this.bustPercent = 0.39;
        } else if (this.total == 14) {
            this.bustPercent = 0.56;
        } else if (this.total == 15) {
            this.bustPercent = 0.58;
        } else if (this.total == 16) {
            this.bustPercent = 0.62;
        } else if (this.total == 17) {
            this.bustPercent = 0.69;
        } else if (this.total == 18) {
            this.bustPercent = 0.77;
        } else if (this.total == 19) {
            this.bustPercent = 0.85;
        } else if (this.total == 20) {
            this.bustPercent = 0.92;
        } else if (this.total >= 21) {
            this.bustPercent = 1.0;
        }
    }

    /**
     * Converts the player's cards into points
     * @param rank
     * @return
     */
    public int convertPoints(String rank) {
        int val = 0;
        if (rank == "A") {
            this.aceCount++;
            val = 11;
        } else if (rank.equals("J") || rank.equals("Q") || rank.equals("K")) {
            val = 10;
        } else {
            val = Integer.parseInt(rank);
        }
        return val;
    }

    /**
     * Prints the player's hand
     * @param reveal
     */
    public void showPlayerHand(boolean reveal) {
        //Cards first row
        System.out.print(" _________");
        for (int i = 0; i < this.open.size(); i++) {
            System.out.print(" ____");
        }
        System.out.print('\n');
        System.out.print("|         |");
        for (int i = 0; i < this.open.size(); i++) {
            System.out.print("    |");
        }
        System.out.print('\n');
        //Cards second row, showing the card ranks
        if (closed.equals("10")) {
            System.out.print("|      "+ closed+" |");
        } else {
            System.out.print("|       "+ closed+" |");
        }
        for (String rank : this.open) {
            if (rank.equals("10")) {
                System.out.print(" "+rank+" |");
            } else {
                System.out.print("  "+rank+" |");
            }
        }
        System.out.print('\n');
        //Cards last row
        System.out.print("|         |");
        for (int i = 0; i < this.open.size(); i++) {
            System.out.print("    |");
        }
        System.out.print('\n');

    }


}
