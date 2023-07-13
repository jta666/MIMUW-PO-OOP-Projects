package chess;

import pieces.King;
import pieces.Piece;

import java.util.ArrayList;
import java.util.Random;

public class Player {
    private String nick;
    private boolean white;
    private boolean alive;
    private int numberOfPieces;
    private ArrayList<Piece> pieces;

    public Player(String nick, boolean white, ArrayList<Piece> pieces) {
        this.nick = nick;
        this.white = white;
        alive = true;
        numberOfPieces = pieces.size();
        this.pieces = pieces;
    }

    public int getNumberOfPieces() {
        return numberOfPieces;
    }

    public void losePiece(Piece p) {
        numberOfPieces--;
        pieces.remove(p);
    }

    public boolean isAlive() {
        return alive;
    }

    public void lose() {
        alive = false;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
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

            Piece chosen = pieces.get(x);

            ArrayList<Spot> possibleMoves = chosen.getPossibleMoves(b, this);

            if (possibleMoves.size() == 0)
                continue;
            else
                moveMade = true;

            Random r1 = new Random();
            int p = r1.nextInt(possibleMoves.size());
            Spot chosenMove = possibleMoves.get(p);

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
                p2.losePiece(chosenMove.getPiece());
            }

            b.empty(chosen.getSpot().getX(), chosen.getSpot().getY());

            b.fill(chosenMove.getX(), chosenMove.getY(), chosen);
            chosen.move(chosenMove);

            b.print();
        }

        return moveMade;
    }

    public boolean isWhite() {
        return white;
    }
}
