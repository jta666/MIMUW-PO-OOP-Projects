package pieces;

import java.util.ArrayList;

import chess.Board;
import chess.Player;
import chess.Spot;

public abstract class Piece {
    private Spot spot;
    private boolean alive;
    private boolean white;

    public int getSymbol(int sym) {
        return isWhite() ? sym : sym + 6;
    }

    public abstract int getSymbol();
    public abstract ArrayList<Spot> getPossibleMoves(Board b, Player p2);

    public Piece(int x, int y, boolean white) {
        spot = new Spot(x, y, this);
        alive = true;
        this.white = white;
    }

    public Spot getSpot() {
        return spot;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isWhite() {
        return white;
    }

    protected boolean isValidMove(Spot s) {
        return s.getX() >= 0 && s.getX() <= 7 && s.getY() >= 0 && s.getY() <= 7;
    }

    public boolean opposite(Player p) {
        return (p.isWhite() && !isWhite()) || (!p.isWhite() && isWhite());
    }

    public String toString() {
        return "" + (char) getSymbol();
    }

    public void move(Spot s) {
        this.spot = s;
    }
}
