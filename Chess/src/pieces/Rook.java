package pieces;

import chess.Board;
import chess.Player;
import chess.Spot;

import java.util.ArrayList;

public class Rook extends Piece {
    public Rook(int x, int y, boolean white) {
        super(x, y, white);
    }

    @Override
    public int getSymbol() {
        return super.getSymbol(9814);
    }

    @Override
    public ArrayList<Spot> getPossibleMoves(Board b, Player p2) {
        ArrayList<Spot> res = new ArrayList<>();
        int x = getSpot().getX(), y = getSpot().getY();

        for (int i = 0; i < 8; i++) {
            if (!b.getSpot(i, y).taken() || (b.getSpot(i, y).taken() && b.getSpot(i, y).getPiece().opposite(p2)))
                res.add(new Spot(i, y, b.getSpot(i, y).taken(), this));
        }

        for (int i = 0; i < 8; i++) {
            if (!b.getSpot(x, i).taken() || (b.getSpot(x, i).taken() && b.getSpot(x, i).getPiece().opposite(p2)))
                res.add(new Spot(x, i, b.getSpot(x, i).taken(), this));
        }

        return res;
    }
}
