package pl.edu.mimuw.matrix;

import java.util.Vector;

public class AntiDiagonalMatrix extends SparseDoubleMatrix {
    public AntiDiagonalMatrix(Vector<MatrixCellValue> v) {
        super(Shape.matrix(v.size(), v.size()), v);
    }

    public static Vector<MatrixCellValue> fromArrConvert (double... diagonalValues) {
        Vector<MatrixCellValue> v = new Vector<>(diagonalValues.length);
        for (int i = 0; i < diagonalValues.length; i++) {
            MatrixCellValue mv =
                    new MatrixCellValue
                            (i, diagonalValues.length - 1 - i, diagonalValues[i]);
            v.add(mv);
        }
        return v;
    }

    public AntiDiagonalMatrix(double... diagonalValues) {
        super(Shape.matrix(diagonalValues.length, diagonalValues.length),
                fromArrConvert(diagonalValues));
    }

    public IDoubleMatrix plus(IDoubleMatrix m) {
        assert (this.shape().equals(m.shape()));
        if (m.getClass() == this.getClass()) {
            Vector<MatrixCellValue> newContents = new Vector<>(this.realSize());
            for (int i = 0; i < this.realSize(); i++) {
                MatrixCellValue mv = new MatrixCellValue(i, this.realSize() - 1 - i,
                        this.get(i, this.realSize() - 1 - i)
                                + m.get(i, this.realSize() - 1 - i));
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
                MatrixCellValue mv = new MatrixCellValue(i, this.realSize() - 1 - i,
                        this.get(i, this.realSize() - 1 - i)
                                * m.get(i, this.realSize() - 1 - i));
                newContents.add(mv);
            }
            return new DiagonalMatrix(newContents);
        } else
            return new DenseDoubleMatrix(Shape.matrix(this.shape().rows, m.shape().columns),
                    defaultProduct(this.data(), m.data()));
    }

    public String toString() {
        String res = "Anti Diagonal Matrix of dimensions: " + this.shape().rows + "x" + this.shape().columns + "\n";
        res += super.toStringHelp();
        return res;
    }
}
