package pieces;

import chess.Board;
import chess.Player;
import chess.Spot;

import java.util.ArrayList;

public class Pawn extends Piece {
    private boolean hasMoved;

    public Pawn(int x, int y, boolean white) {
        super(x, y, white);
        hasMoved = false;
    }

    public int getSymbol() {
        return super.getSymbol(9817);
    }

    @Override
    public ArrayList<Spot> getPossibleMoves(Board b, Player p2) {
        ArrayList<Spot> res = new ArrayList<>();

        if (isWhite()) {
            Spot s = new Spot(this.getSpot().getX() + 1, this.getSpot().getY());
            if (isValidMove(s)) {
                s = new Spot(this.getSpot().getX() + 1, this.getSpot().getY(),
                        b.getSpot(this.getSpot().getX() + 1, this.getSpot().getY()).taken(), this);
                if (!b.getSpot(this.getSpot().getX() + 1, this.getSpot().getY()).taken())
                    res.add(s);
            }

            if (!hasMoved) {
                Spot s1 = new Spot(this.getSpot().getX() + 2, this.getSpot().getY());
                Spot s2 = new Spot(this.getSpot().getX() + 1, this.getSpot().getY());
                if (isValidMove(s1) && isValidMove(s2)) {
                    s1 = new Spot(this.getSpot().getX(), this.getSpot().getY() + 2,
                            b.getSpot(this.getSpot().getX() + 2, this.getSpot().getY()).taken(), this);
                    if (!b.getSpot(this.getSpot().getX() + 1, this.getSpot().getY()).taken() &&
                        !b.getSpot(this.getSpot().getX() + 2, this.getSpot().getY()).taken())
                        res.add(s1);
                }
            }

            Spot s1 = new Spot(this.getSpot().getX() + 1, this.getSpot().getY() - 1);
            if (isValidMove(s1)) {
                s1 = new Spot(this.getSpot().getX() + 1, this.getSpot().getY() - 1,
                        b.getSpot(this.getSpot().getX() + 1, this.getSpot().getY() - 1).taken(), this);
                if (b.getSpot(this.getSpot().getX() + 1, this.getSpot().getY() - 1).taken() &&
                    !b.getSpot(this.getSpot().getX() + 1, this.getSpot().getY() - 1).getPiece().isWhite())
                    res.add(s1);
            }

            Spot s2 = new Spot(this.getSpot().getX() + 1, this.getSpot().getY() + 1);
            if (isValidMove(s2)) {
                s2 = new Spot(this.getSpot().getX() + 1, this.getSpot().getY() + 1,
                        b.getSpot(this.getSpot().getX() + 1, this.getSpot().getY() + 1).taken(), this);
                if (b.getSpot(this.getSpot().getX() + 1, this.getSpot().getY() + 1).taken() &&
                        !b.getSpot(this.getSpot().getX() + 1, this.getSpot().getY() + 1).getPiece().isWhite())
                    res.add(s2);
            }
        } else {
            Spot s = new Spot(this.getSpot().getX() - 1, this.getSpot().getY());
            if (isValidMove(s)) {
                s = new Spot(this.getSpot().getX() - 1, this.getSpot().getY(),
                        b.getSpot(this.getSpot().getX() - 1, this.getSpot().getY()).taken(), this);
                if (!b.getSpot(this.getSpot().getX() - 1, this.getSpot().getY()).taken())
                    res.add(s);
            }

            if (!hasMoved) {
                Spot s1 = new Spot(this.getSpot().getX() - 2, this.getSpot().getY());
                Spot s2 = new Spot(this.getSpot().getX() - 1, this.getSpot().getY());
                if (isValidMove(s1) && isValidMove(s2)) {
                    s1 = new Spot(this.getSpot().getX() - 2, this.getSpot().getY(),
                            b.getSpot(this.getSpot().getX() - 2, this.getSpot().getY()).taken(), this);
                    if (!b.getSpot(this.getSpot().getX() - 1, this.getSpot().getY()).taken() &&
                            !b.getSpot(this.getSpot().getX() - 2, this.getSpot().getY()).taken())
                        res.add(s1);
                }
            }

            Spot s1 = new Spot(this.getSpot().getX() - 1, this.getSpot().getY() - 1);
            if (isValidMove(s1)) {
                s1 = new Spot(this.getSpot().getX() - 1, this.getSpot().getY() - 1,
                        b.getSpot(this.getSpot().getX() - 1, this.getSpot().getY() - 1).taken(), this);
                if (b.getSpot(this.getSpot().getX() - 1, this.getSpot().getY() - 1).taken() &&
                        b.getSpot(this.getSpot().getX() - 1, this.getSpot().getY() - 1).getPiece().isWhite())
                    res.add(s1);
            }

            Spot s2 = new Spot(this.getSpot().getX() - 1, this.getSpot().getY() + 1);
            if (isValidMove(s2)) {
                s2 = new Spot(this.getSpot().getX() - 1, this.getSpot().getY() + 1,
                        b.getSpot(this.getSpot().getX() - 1, this.getSpot().getY() + 1).taken(), this);
                if (b.getSpot(this.getSpot().getX() - 1, this.getSpot().getY() + 1).taken() &&
                        b.getSpot(this.getSpot().getX() - 1, this.getSpot().getY() + 1).getPiece().isWhite())
                    res.add(s2);
            }
        }

        hasMoved = true;
        return res;
    }
}
