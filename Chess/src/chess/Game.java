package chess;

import java.awt.event.HierarchyBoundsAdapter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class Game {
    public ArrayList<Player> players;
    private Board board;

    private boolean fin;

    public Game(ArrayList<Player> players, Board board) {
        this.players = players;
        this.board = board;
        fin = false;
    }

    public void play() {
        for (int i = 0; i < 100; i++) {
            int player = i % 2 == 0 ? 0 : 1;
            int player2 = Math.abs(player - 1);

            if (!players.get(player).move(board, players.get(player2)))
                players.get(player).lose();

            if (!players.get(0).isAlive()) {
                System.out.println("White won!");
                return;
            }

            if (!players.get(1).isAlive()) {
                System.out.println("Black won!");
                return;
            }
        }
        System.out.println("Draw!");
    }
}
