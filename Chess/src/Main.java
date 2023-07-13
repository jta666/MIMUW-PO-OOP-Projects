import chess.*;
import pieces.King;
import pieces.Pawn;
import pieces.Piece;
import pieces.Rook;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Player> players = new ArrayList<>();

        ArrayList<Piece> whitePieces = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            whitePieces.add(new Pawn(i, 1, true));
            if (i != 4)
                whitePieces.add(new Rook(i, 0, true));
        }
        whitePieces.add(new King(4, 0, true));

        Player p1 = new Player("P1", true, whitePieces);
        players.add(p1);

        ArrayList<Piece> blackPieces = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            blackPieces.add(new Pawn(i, 6, false));
            if (i != 4)
                blackPieces.add(new Rook(i, 7, false));
        }
        blackPieces.add(new King(4, 6, false));

        Player p2 = new PredatorPlayer("P2", false, blackPieces);
        players.add(p2);

        Board board = new Board();

        for (Piece p : whitePieces)
            board.fill(p.getSpot());

        for (Piece p : blackPieces)
            board.fill(p.getSpot());

        Game g = new Game(players, board);
        g.play();

    }
}
