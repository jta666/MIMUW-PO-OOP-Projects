package pl.edu.mimuw.matrix;

import java.util.*;

public class DiagonalMatrix extends SparseDoubleMatrix {
    public DiagonalMatrix(Vector<MatrixCellValue> v) {
        super(Shape.matrix(v.size(), v.size()), v);
    }

    public static Vector<MatrixCellValue> fromArrConvert (double... diagonalValues) {
        Vector<MatrixCellValue> v = new Vector<>(diagonalValues.length);
        for (int i = 0; i < diagonalValues.length; i++) {
            MatrixCellValue mv = new MatrixCellValue(i, i, diagonalValues[i]);
            v.add(mv);
        }
        return v;
    }

    public DiagonalMatrix(double... diagonalValues) {
        super(Shape.matrix(diagonalValues.length, diagonalValues.length),
                fromArrConvert(diagonalValues));
    }

    public IDoubleMatrix plus(IDoubleMatrix m) {
        assert (this.shape().equals(m.shape()));
        if (m.getClass() == this.getClass()) {
            Vector<MatrixCellValue> newContents = new Vector<>(this.realSize());
            for (int i = 0; i < this.realSize(); i++) {
                MatrixCellValue mv = new MatrixCellValue(i, i, this.get(i, i) + m.get(i, i));
                newContents.add(mv);
            }
            return new DiagonalMatrix(newContents);
        } else
            return new DenseDoubleMatrix(this.shape(), defaultSum(this.data(), m.data()));
    }

    public IDoubleMatrix minus(IDoubleMatrix m) {
        return this.plus(m.times(-1));
    }

    public IDoubleMatrix times(IDoubleMatrix m) {
        assert (this.shape().columns == m.shape().rows);
        if (m.getClass() == this.getClass()) {
            Vector<MatrixCellValue> newContents = new Vector<>(this.realSize());
            for (int i = 0; i < this.realSize(); i++) {
                MatrixCellValue mv = new MatrixCellValue(i, i, this.get(i, i) * m.get(i, i));
                newContents.add(mv);
            }
            return new DiagonalMatrix(newContents);
        } else
            return new DenseDoubleMatrix(Shape.matrix(this.shape().rows, m.shape().columns),
                    defaultProduct(this.data(), m.data()));
    }

    public String toString() {
        String res = "Diagonal Matrix of dimensions: " + this.shape().rows + "x" + this.shape().columns + "\n";
        res += super.toStringHelp();
        return res;
    }
}
