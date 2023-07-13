package pl.edu.mimuw.matrix;

import java.util.Vector;

public class MatrixVector extends SparseDoubleMatrix {
    public MatrixVector(Vector<MatrixCellValue> v) {
        super(Shape.matrix(v.size(), v.size()), v);
    }

    public static Vector<MatrixCellValue> fromArrConvert (double... diagonalValues) {
        Vector<MatrixCellValue> v = new Vector<>(diagonalValues.length);
        for (int i = 0; i < diagonalValues.length; i++) {
            MatrixCellValue mv =
                    new MatrixCellValue
                            (0, i, diagonalValues[i]);
            v.add(mv);
        }
        return v;
    }

    public MatrixVector(double... diagonalValues) {
        super(Shape.matrix(1, diagonalValues.length),
                fromArrConvert(diagonalValues));
    }

    public IDoubleMatrix plus(IDoubleMatrix m) {
        assert (this.shape().equals(m.shape()));
        if (m.getClass() == this.getClass()) {
            Vector<MatrixCellValue> newContents = new Vector<>(this.realSize());
            for (int i = 0; i < this.realSize(); i++) {
                MatrixCellValue mv = new MatrixCellValue(0, i,
                        this.get(0, i)
                                + m.get(0, i));
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
                MatrixCellValue mv = new MatrixCellValue(0, i,
                        this.get(0, i)
                                * m.get(0, i));
                newContents.add(mv);
            }
            return new DiagonalMatrix(newContents);
        } else
            return new DenseDoubleMatrix(Shape.matrix(this.shape().rows, m.shape().columns),
                    defaultProduct(this.data(), m.data()));
    }

    public double normOne() {
        double res = 0;
        for (int i = 0; i < this.realSize(); i++)
            res += Math.abs(this.getByIndex(i).value);
        return res;
    }

    public double normInfinity() {
        double res = 0;
        for (int i = 0; i < this.realSize(); i++)
            if (Math.abs(this.getByIndex(i).value) > res)
                res = Math.abs(this.getByIndex(i).value);
        return res;
    }

    public String toString() {
        String res = "Vector Matrix of dimensions: " + this.shape().rows + "x" + this.shape().columns + "\n";
        res += super.toStringHelp();
        return res;
    }
}
