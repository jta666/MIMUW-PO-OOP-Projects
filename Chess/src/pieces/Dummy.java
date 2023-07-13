package pieces;

import chess.Board;
import chess.Player;
import chess.Spot;

import java.util.ArrayList;

public class Dummy extends Piece {
    @Override
    public ArrayList<Spot> getPossibleMoves(Board b, Player p2) {
        return new ArrayList<>();
    }

    @Override
    public int getSymbol() {
        return 0;
    }

    public Dummy() {
        super(-1, -1, false);
    }
}
