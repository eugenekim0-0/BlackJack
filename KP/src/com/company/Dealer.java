package com.company;

public class Dealer extends Player {

    /**
     * Constructor for Dealer object
     */
    public Dealer() {
        super();
    }


    /**
     * Prints the dealer's hand
     * @param reveal
     */
    @Override
    public void showPlayerHand(boolean reveal) {
        //Cards first row
        if (!reveal) {
            System.out.print("| * * * * |");
        } else {
            System.out.print("|         |");
        }
        for (int i = 0; i < this.getOpen().size(); i++) {
            System.out.print("    |");
        }
        System.out.print('\n');
        //Cards second row, showing the card ranks
        if (!reveal) {
            System.out.print("|* * * * *|");
        } else {
            String closed = this.getClosed();
            if (closed.equals("10")) {
                System.out.print("|      "+ closed+" |");
            } else {
                System.out.print("|       "+ closed+" |");
            }
        }
        for (String rank : this.getOpen()) {
            if (rank.equals("10")) {
                System.out.print(" "+rank+" |");
            } else {
                System.out.print("  "+rank+" |");
            }
        }
        System.out.print('\n');
        //Cards last row
        System.out.print("|_________|");

        for (int i = 0; i < this.getOpen().size(); i++) {
            System.out.print("____|");
        }
        System.out.print('\n');
    }

}
