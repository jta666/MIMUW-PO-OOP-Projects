package pl.edu.mimuw.matrix;

import java.util.*;

public class ConstantMatrix extends DoubleMatrix {
    private double content;

    public ConstantMatrix(Shape shape, double content) {
        super(shape);
        this.content = content;
    }

    public double get(int i, int j) {
        assert (i >= 0 && i < this.shape().rows);
        assert (j >= 0 && j < this.shape().columns);
        return content;
    }

    public double[][] data() {
        double[][] res = new double[this.shape().rows][this.shape().columns];
        Arrays.fill(res, content);
        return res;
    }

    public IDoubleMatrix plus(double x) {
        if (this.content + x == 0)
            return new NullMatrix(this.shape());
        return new ConstantMatrix(this.shape(), this.content + x);
    }

    public IDoubleMatrix plus(IDoubleMatrix m) {
        return m.plus(this.content);
    }

    public IDoubleMatrix minus(double x) {
        return this.plus((-1) * x);
    }

    public IDoubleMatrix minus(IDoubleMatrix m) {
        return m.minus(this.content);
    }

    public IDoubleMatrix times(double x) {
        if (x == 0)
            return new NullMatrix(this.shape());
        else
            return new ConstantMatrix(this.shape(), this.content * x);
    }

    public IDoubleMatrix times(IDoubleMatrix m) {
        assert (m.shape().columns == this.shape().rows);
        if (m.getClass() == this.getClass()) {
            double newContent = this.get(1,1) * m.get(1, 1) * this.shape().rows;
            return new ConstantMatrix
                    (Shape.matrix(m.shape().rows, this.shape().columns), newContent);
        } else
            return new DenseDoubleMatrix(Shape.matrix(this.shape().rows, m.shape().columns),
                    defaultProduct(this.data(), m.data()));
    }

    public double normOne() {
        return this.content * this.shape().columns;
    }

    public double normInfinity() {
        return this.content * this.shape().rows;
    }

    public double frobeniusNorm() {
        return Math.sqrt(this.shape().rows * this.shape().columns *
                Math.abs(this.content) * Math.abs(this.content));
    }

    public String toString() {
        String res = "Constant Matrix of dimensions: " + this.shape().rows + "x" + this.shape().columns + "\n";
        for (int i = 0; i < this.shape().rows; i++) {
            String line = this.content + " ... " + this.content + "\n";
            res += line;
        }
        return res;
    }
}
