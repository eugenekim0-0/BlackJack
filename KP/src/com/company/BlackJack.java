package com.company;

import java.util.ArrayList;

public class BlackJack {
    private final String[] POSSIBLE = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    private int[] deck;
    private int turn;
    private ArrayList<Player> players;
    private Player current;
    private int standCount;
    private boolean allStand;
    private boolean oneBusted;
    private boolean gameEnded;

    /**
     * Constructor for BlackJack object
     * @param players
     */
    public BlackJack(ArrayList<Player> players) {
        this.deck = new int[13];
        this.turn = 0;
        this.players = players;
        this.current = players.get(0);
        this.standCount = 0;
        this.allStand = false;
        this.oneBusted = false;
        this.gameEnded = false;
    }

    //Methods for BlackJack options
    /**
     * Serves as the hit option of BlackJack
     * Adds a card to the current player and check if they busted
     */
    public void hit() {
        this.current.addOpen(this.drawCard());
        this.standCount = 0;
        this.checkBust();
        if (!this.isOneBusted()) {
            this.nextTurn();
        }
    }

    /**
     * Serves as the stand option of BlackJack
     * Skips the current player's turn
     */
    public void stand() {
        this.standCount++;
        this.checkStand();
        if (!this.allStand) {
            this.nextTurn();
        }
    }

    //Methods for oneBusted
    /**
     * Returns whether there's a player that busted
     * @return
     */
    public boolean isOneBusted() {
        return this.oneBusted;
    }

    /**
     * Checks if current player has busted
     * @return
     */
    public boolean checkBust() {
        if (this.current.getTotal() > 21) {
            this.oneBusted = true;
            this.endGame();
            return true;
        }
        return false;
    }

    //Methods for allStand
    /**
     * Sets whether all players chose to stand or not
     * @param stand
     */
    public void setAllStand(boolean stand) {
        this.allStand = stand;
    }

    /**
     * Checks if all players chose to stand
     */
    public void checkStand() {
        if (this.standCount == this.players.size()) {
            this.allStand = true;
            this.endGame();
        }
    }

    /**
     * Checks if a player has BlackJack
     * @param index
     */
    public void checkBlackJack(int index) {
        if (this.getPlayer(index).getTotal() == 21) {
            System.out.println("BlackJack!");
        }
    }

    //Methods for current and players
    /**
     * Returns the current player
     * @return
     */
    public Player getCurrent() {
        return this.current;
    }

    /**
     * Returns a player given its index in the players ArrayList
     * @param index
     * @return
     */
    public Player getPlayer(int index) {
        return this.players.get(index);
    }

    /**
     * Draws a random card from the deck
     * @return
     */
    public String drawCard() {
        int randCard = (int) (Math.random() * 13);
        if (this.deck[randCard] == 0) {
            drawCard();
        } else {
            this.deck[randCard]--;
        }
        return POSSIBLE[randCard];
    }


    //Methods for turn
    /**
     * Returns the turn that the game is on
     * @return
     */
    public int getTurn() {
        return this.turn;
    }

    /**
     * Moves the game onto the next turn
     */
    public void nextTurn() {
        this.turn = (this.turn+1) % players.size();
        this.current = this.players.get(this.turn);
    }

    /**
     * Prints all the players' cards
     */
    public void showTurn() {
        String dealerTurn = "";
        String userTurn = "";
        Player user = this.getPlayer(0);
        Player dealer = this.getPlayer(1);

        if (turn == 0) {
            dealerTurn = "DEALER";
            userTurn = "YOU *";
        } else {
            dealerTurn = "DEALER *";
            userTurn = "YOU";
        }
        System.out.println(dealerTurn);
        if (allStand || oneBusted) {
            dealer.showPlayerHand(true);
        } else {
            dealer.showPlayerHand(false);
        }
        System.out.println('\n');
        user.showPlayerHand(false);
        System.out.println(userTurn);
        System.out.println('\n');
        System.out.println("-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-");
        System.out.println('\n');

    }


    /**
     * Starts a brand new BlackJack game
     */
    public void newGame() {
        this.current = players.get(0);
        this.turn = 0;
        this.standCount = 0;
        this.allStand = false;
        this.oneBusted = false;
        this.gameEnded = false;
        for (int i = 0; i < deck.length; i++) {
            this.deck[i] = 4;
        }
        for (Player drawn : this.players) {
            drawn.emptyOpen();
            drawn.setTotal(0);
            drawn.setClosed(this.drawCard());
            drawn.addOpen(this.drawCard());
        }
    }

    //Methods for gameEnded
    /**
     * Returns whether the game has ended or not
     * @return
     */
    public boolean isGameEnded() {
        return this.gameEnded;
    }

    /**
     * Compares the player's and dealer's total to see who was higher, in other words, who won
     * @param player
     * @param dealer
     * @return
     */
    public int compareTotals(Player player, Player dealer) {
        int outcome = -1;
        if (player.getTotal() > dealer.getTotal()) {
            outcome = 0;
        } else if (player.getTotal() < dealer.getTotal()) {
            outcome = 1;
        }
        return outcome;
    }

    /**
     * Prints the results of the game
     */
    public void endGame() {
        int currentIndex = this.players.indexOf(this.current);
        this.showTurn();
        if (this.oneBusted) {
            if (currentIndex == 0) {
                System.out.println("Busted! You lost!");
            } else {
                System.out.println("Dealer busted! You won!");
            }
        } else if (this.allStand) {
            int result = this.compareTotals(this.getPlayer(0), this.getPlayer(1));
            if (result == 0) {
                checkBlackJack(0);
                System.out.println("You won!");
            } else if (result == 1){
                checkBlackJack(1);
                System.out.println("Dealer won! You lost!");
            } else {
                System.out.println("Dealer and you tied!");
            }
        } else {
            System.out.println("No end");
        }
        this.gameEnded = true;
    }
}
