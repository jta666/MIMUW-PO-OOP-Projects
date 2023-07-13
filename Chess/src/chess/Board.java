package chess;
import pieces.Piece;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Board {
    private ArrayList<ArrayList<Spot>> spots;

    public Board() {
        ArrayList<ArrayList<Spot>> spots = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            ArrayList<Spot> line = new ArrayList<>();
            for (int j = 0; j < 8; j++)
                line.add(new Spot(i, j));
            spots.add(line);
        }
        this.spots = spots;
    }

    public Spot getSpot(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7)
            return null;
        return spots.get(x).get(y);
    }

    public void empty(int x, int y) {
        getSpot(x, y).empty();
    }

    public void fill(int x, int y, Piece piece) {
        getSpot(x, y).fill(piece);
    }

    public void fill(Spot s) {
        getSpot(s.getX(), s.getY()).fill(s.getPiece());
    }

    public void print() {
        for (int i = 0; i < 8; i++) {
            String line = "";
            for (int j = 0; j < 8; j++)
                if (!getSpot(j, i).taken())
                    line += "_";
                else
                    line += getSpot(j, i).getPiece().toString();
            System.out.println(line);
        }
    }
}
