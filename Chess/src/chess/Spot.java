package chess;

import pieces.Dummy;
import pieces.Piece;

public class Spot {
    private int x;
    private int y;

    private Piece piece;
    private boolean taken;

    public Spot(int x, int y) {
        this.x = x;
        this.y = y;
        piece = new Dummy();
        taken = false;
    }

    public Spot(int x, int y, boolean taken) {
        this.x = x;
        this.y = y;
        piece = new Dummy();
        this.taken = taken;
    }

    public Spot(int x, int y, boolean taken, Piece piece) {
        this.x = x;
        this.y = y;
        this.piece = piece;
        this.taken = taken;
    }

    public Spot(int x, int y, Piece piece) {
        this.x = x;
        this.y = y;
        this.piece = piece;
        taken = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean taken() {
        return taken;
    }

    public void empty() {
        piece = null;
        taken = false;
    }

    public void fill(Piece piece) {
        this.piece = piece;
        taken = true;
    }

    public String toString() {
        switch (y) {
            case 0:
                return "A" + x;
            case 1:
                return "B" + x;
            case 2:
                return "C" + x;
            case 3:
                return "D" + x;
            case 4:
                return "E" + x;
            case 5:
                return "F" + x;
            case 6:
                return "G" + x;
            case 7:
                return "H" + x;
            default:
                return "Out of bounds spot";
        }
    }
}
