package chess;

import pieces.King;
import pieces.Piece;

import java.util.ArrayList;
import java.util.Random;

public class PredatorPlayer extends Player {
    public PredatorPlayer(String nick, boolean white, ArrayList<Piece> pieces) {
        super(nick, white, pieces);
    }

    public boolean move(Board b, Player p2) {
        ArrayList<Integer> alreadyDrawn = new ArrayList<>();
        boolean moveMade = false;

        while(!moveMade && alreadyDrawn.size() < getNumberOfPieces()) {
            Random r = new Random();
            int x = r.nextInt(getNumberOfPieces());
            if (!alreadyDrawn.contains(x))
                alreadyDrawn.add(x);
            else continue;

            Piece chosen = getPieces().get(x);

            ArrayList<Spot> possibleMoves = chosen.getPossibleMoves(b, this);

            if (possibleMoves.size() == 0)
                continue;
            else
                moveMade = true;

            Spot chosenMove = new Spot(-1, -1);
            boolean moveChosen = false;
            boolean anyMoveChosen = false;

            for (Spot m : possibleMoves) {
                if (!anyMoveChosen && m.taken() && m.getPiece().opposite(this)) {
                    chosenMove = m;
                    anyMoveChosen = true;
                }
                if (anyMoveChosen && m.taken() && m.getPiece() instanceof King) {
                    chosenMove = m;
                    moveChosen = true;
                }
            }

            if (!moveChosen && anyMoveChosen)
                moveChosen = true;

            if (!moveChosen) {
                Random r1 = new Random();
                int p = r1.nextInt(possibleMoves.size());
                chosenMove = possibleMoves.get(p);
            }

            boolean taking = b.getSpot(chosenMove.getX(), chosenMove.getY()).taken() &&
                    b.getSpot(chosenMove.getX(), chosenMove.getY()).getPiece().opposite(this);
            assert(!(chosenMove.taken() && !chosenMove.getPiece().opposite(this)));

            String moveLine = taking ? (char) chosen.getSymbol() + ": " + chosen.getSpot().toString() + " -> " +
                    chosenMove.toString() + "; " + "taking " +
                    (char) b.getSpot(chosenMove.getX(), chosenMove.getY()).getPiece().getSymbol() :
                    (char) chosen.getSymbol() + ": " + chosen.getSpot().toString() + " -> " +
                            chosenMove.toString() + ";" + " not taking anything";

            System.out.println(moveLine);

            if (taking) {
                if (b.getSpot(chosenMove.getX(), chosenMove.getY()).getPiece() instanceof King)
                    p2.lose();
                p2.losePiece(b.getSpot(chosenMove.getX(), chosenMove.getY()).getPiece());
            }

            b.empty(chosen.getSpot().getX(), chosen.getSpot().getY());

            b.fill(chosenMove.getX(), chosenMove.getY(), chosen);
            chosen.move(chosenMove);

            b.print();
        }

        return moveMade;
    }
}
