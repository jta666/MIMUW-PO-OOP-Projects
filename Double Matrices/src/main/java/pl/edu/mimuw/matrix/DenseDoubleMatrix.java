package pl.edu.mimuw.matrix;

import pl.edu.mimuw.matrix.Shape;

import java.util.Arrays;

public class DenseDoubleMatrix extends DoubleMatrix {
    private final double[][] contents;

    public DenseDoubleMatrix(Shape shape, double[][] contents) {
        super(shape);
        assert (contents != null);
        assert (contents.length > 0);
        int width = contents[0].length;
        assert (width > 0);
        for (int i = 0; i < contents.length; i++)
            assert (contents[i].length == width);
        this.contents = contents;
    }

    public double get(int row, int column) {
        assert (row >= 0 && row < this.shape().rows);
        assert (column >= 0 && column < this.shape().columns);
        return contents[row][column];
    }

    public double[][] data() {
        return this.contents;
    }

    public DoubleMatrix plus(double x) {
        double[][] updatedContents = new double[this.shape().rows][this.shape().columns];
        for (int i = 0; i < this.shape().rows; i++)
            for (int j = 0; j < this.shape().columns; j++)
                updatedContents[i][j] = this.contents[i][j] + x;
        return new DenseDoubleMatrix(this.shape(), updatedContents);
    }

    public IDoubleMatrix plus(IDoubleMatrix m) {
        assert (this.shape().equals(m.shape()));
        double[][] newContents = defaultSum(this.data(), m.data());
        return new DenseDoubleMatrix(this.shape(), newContents);
    }

    public IDoubleMatrix minus(double x) {
        return plus((-1) * x);
    }

    public IDoubleMatrix minus(IDoubleMatrix m) {
        return this.plus(m.times(-1));
    }

    public IDoubleMatrix times(double x) {
        double[][] updatedContents = new double[this.shape().rows][this.shape().columns];
        for (int i = 0; i < this.shape().rows; i++)
            for (int j = 0; j < this.shape().columns; j++)
                updatedContents[i][j] = this.contents[i][j] * x;
        return new DenseDoubleMatrix(this.shape(), updatedContents);
    }

    public IDoubleMatrix times(IDoubleMatrix m) {
        assert (this.shape().columns == m.shape().rows);
        Shape newShape = Shape.matrix(this.shape().rows, m.shape().columns);
        double[][] newContents = defaultProduct(this.data(), m.data());
        return new DenseDoubleMatrix(newShape, newContents);
    }

    public double normOne() {
        double res = 0;
        for (int j = 0; j < this.shape().columns; j++) {
            double curr = 0;
            for (int i = 0; i < this.shape().rows; i++)
                curr += Math.abs(this.get(i, j));
            if (curr > res)
                res = curr;
        }
        return res;
    }

    public double normInfinity() {
        double res = 0;
        for (int j = 0; j < this.shape().rows; j++) {
            double curr = 0;
            for (int i = 0; i < this.shape().columns; i++)
                curr += Math.abs(this.get(j, i));
            if (curr > res)
                res = curr;
        }
        return res;
    }

    public double frobeniusNorm() {
        double res = 0;
        for (int i = 0; i < this.shape().rows; i++)
            for (int j = 0; j < this.shape().columns; j++)
                res += (Math.abs(this.get(i, j)) * Math.abs(this.get(i, j)));
        return Math.sqrt(res);
    }

    @Override
    public String toString() {
        String res = "Dense Matrix of dimensions: " + this.shape().rows + "x" + this.shape().columns + "\n";
        for (int i = 0; i < this.shape().rows; i++) {
            String line = "";
            for (int j = 0; j < this.shape().columns; j++)
                line += this.get(i, j) + " ";
            line += "\n";
            res += line;
        }
        return res;
    }
}
