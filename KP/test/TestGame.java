import org.junit.Test;
import static org.junit.Assert.*;


import com.company.BlackJack;
import com.company.Player;
import com.company.Dealer;

import java.util.ArrayList;


public class TestGame {

    @Test
    public void testCheckAce() {
        Player user = new Player();
        user.addOpen("A");
        user.addOpen("A");
        assertEquals(12, user.getTotal());
        user.addOpen("10");
        assertEquals(12, user.getTotal());

    }

    @Test
    public void testShowCards() {
        Dealer dealCards = new Dealer();
        dealCards.addOpen("10");
        dealCards.addOpen("A");
        dealCards.addOpen("2");
        dealCards.addOpen("J");
        dealCards.showPlayerHand(false);

        Player playCards = new Player();
        playCards.setClosed("10");
        playCards.addOpen("J");
        playCards.addOpen("1");
        playCards.addOpen("8");
        playCards.showPlayerHand(true);

    }

    @Test
    public void testShowTurn() {
        ArrayList<Player> players = new ArrayList<>();
        Player user = new Player();
        Dealer dealer = new Dealer();
        players.add(user);
        players.add(dealer);
        BlackJack game = new BlackJack(players);
        game.newGame();
        game.showTurn();
        game.nextTurn();
        game.showTurn();
    }

    @Test
    public void testCheckBust() {
        ArrayList<Player> players = new ArrayList<>();
        Player user = new Player();
        Dealer dealer = new Dealer();
        players.add(user);
        players.add(dealer);
        BlackJack game = new BlackJack(players);
        game.newGame();
        game.hit();
        game.hit();
        game.hit();
        game.hit();
        game.showTurn();
        game.checkBust();
        assertTrue(game.isOneBusted());
    }

    @Test
    public void testBothTied() {
        ArrayList<Player> players = new ArrayList<>();
        Player user = new Player();
        Dealer dealer = new Dealer();
        players.add(user);
        players.add(dealer);
        BlackJack game = new BlackJack(players);
        dealer.setClosed("Q");
        dealer.addOpen("J");
        user.setClosed("10");
        user.addOpen("Q");
        game.setAllStand(true);
        game.endGame();
        assertEquals(user.getTotal(), dealer.getTotal());
        assertTrue(game.isGameEnded());
    }
}
