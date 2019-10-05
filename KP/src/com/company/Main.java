package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static BlackJack thisGame;

    public static void main(String[] args) {
	    Scanner input = new Scanner(System.in);
	    //BlackJack game setup
        ArrayList<Player> players = new ArrayList<>();
        Player user = new Player();
        Dealer dealer = new Dealer();
        players.add(user);
        players.add(dealer);
	    thisGame = new BlackJack(players);
	    System.out.println("Let's play BlackJack!");
	    thisGame.newGame();
	    boolean endless = true;
	    while (endless) {
	        thisGame.showTurn();
	        if (thisGame.getTurn() == 0) {
                //User move
                System.out.println("Hit or stand?");
                String move = input.nextLine();
                userAction(input, move);
            } else {
                //Dealer move
                dealerChoice();
            }
            if (thisGame.isGameEnded()) {
                System.out.println("New game? (y/n)");
                String continueGame = input.nextLine();
                if (continueGame.equals("y")) {
                    thisGame.newGame();
                } else {
                    endless = false;
                }
            }
        }


    }

    /**
     * Acts accordingly to the user's input
     * @param action
     */
    public static void userAction(Scanner input, String action) {
        if (action.equals("Hit") || action.equals("hit")) {
            thisGame.hit();
        } else if (action.equals("Stand") || action.equals("stand")) {
            thisGame.stand();
        } else {
            System.out.println("Not a valid move");
            System.out.println("'Hit'/'hit' or 'Stand'/'stand'?");
            String revised = input.nextLine();
            userAction(input, revised);
        }
    }

    /**
     * Serves for what the dealer choose
     */
    public static void dealerChoice() {
        double mayBust = thisGame.getCurrent().getBustPercent();
        if (mayBust < 0.6) {
            thisGame.hit();
        } else {
            thisGame.stand();
        }
    }
}
