package pl.edu.mimuw.matrix;

import java.util.Vector;

public class IdentityMatrix extends DiagonalMatrix {
    public static Vector<MatrixCellValue> oneVector(int size) {
        Vector<MatrixCellValue> res = new Vector<>(size);
        for (int i = 0; i < size; i++) {
            MatrixCellValue mv = new MatrixCellValue(i, i, 1);
            res.add(mv);
        }
        return res;
    }

    public IdentityMatrix(int size) {
        super(oneVector(size));
    }
}
