package pieces;

import chess.Board;
import chess.Player;
import chess.Spot;

import java.util.ArrayList;

public class King extends Piece {
    public King(int x, int y, boolean white) {
        super(x, y, white);
    }

    public int getSymbol() {
        return super.getSymbol(9812);
    }

    @Override
    public ArrayList<Spot> getPossibleMoves(Board b, Player p2) {
        ArrayList<Spot> res = new ArrayList<>();
        int x = getSpot().getX(), y = getSpot().getY();

        Spot s1 = new Spot(x, y + 1);
        if (isValidMove(s1)) {
            s1 = new Spot(x, y + 1, b.getSpot(x, y + 1).taken(), this);
            if (!b.getSpot(x, y + 1).taken() || b.getSpot(x, y + 1).getPiece().opposite(p2))
                res.add(s1);
        }

        Spot s2 = new Spot(x, y - 1);
        if (isValidMove(s2)) {
            s2 = new Spot(x, y - 1, b.getSpot(x, y - 1).taken(), this);
            if (!b.getSpot(x, y - 1).taken() || b.getSpot(x, y - 1).getPiece().opposite(p2))
                res.add(s2);
        }

        Spot s3 = new Spot(x + 1, y);
        if (isValidMove(s3)) {
            s3 = new Spot(x + 1, y, b.getSpot(x + 1, y).taken(), this);
            if (!b.getSpot(x + 1, y).taken() || b.getSpot(x + 1, y).getPiece().opposite(p2))
                res.add(s3);
        }

        Spot s4 = new Spot(x - 1, y);
        if (isValidMove(s4)) {
            s4 = new Spot(x - 1, y, b.getSpot(x - 1, y).taken(), this);
            if (!b.getSpot(x - 1, y).taken() || b.getSpot(x - 1, y).getPiece().opposite(p2))
                res.add(s4);
        }

        Spot s5 = new Spot(x - 1, y - 1);
        if (isValidMove(s5)) {
            s5 = new Spot(x - 1, y - 1, b.getSpot(x - 1, y - 1).taken(), this);
            if (!b.getSpot(x - 1, y - 1).taken() || b.getSpot(x - 1, y - 1).getPiece().opposite(p2))
                res.add(s5);
        }

        Spot s6 = new Spot(x + 1, y - 1);
        if (isValidMove(s6)) {
            s6 = new Spot(x + 1, y - 1, b.getSpot(x + 1, y - 1).taken(), this);
            if (!b.getSpot(x + 1, y - 1).taken() || b.getSpot(x + 1, y - 1).getPiece().opposite(p2))
                res.add(s6);
        }

        Spot s7 = new Spot(x - 1, y + 1);
        if (isValidMove(s7)) {
            s7 = new Spot(x - 1, y + 1, b.getSpot(x - 1, y + 1).taken(), this);
            if (!b.getSpot(x - 1, y + 1).taken() || b.getSpot(x - 1, y + 1).getPiece().opposite(p2))
                res.add(s7);
        }

        Spot s8 = new Spot(x + 1, y + 1);
        if (isValidMove(s8)) {
            s8 = new Spot(x + 1, y + 1, b.getSpot(x + 1, y + 1).taken(), this);
            if (!b.getSpot(x + 1, y + 1).taken() || b.getSpot(x + 1, y + 1).getPiece().opposite(p2))
                res.add(s8);
        }

        return res;
    }
}
