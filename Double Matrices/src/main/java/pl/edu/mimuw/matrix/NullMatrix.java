package pl.edu.mimuw.matrix;

public class NullMatrix extends ConstantMatrix {
    private double content;

    public NullMatrix(Shape shape) {
        super(shape, 0);
    }

    public double get(int i, int j) {
        assert (i >= 0 && i < this.shape().rows);
        assert (j >= 0 && j < this.shape().columns);
        return 0;
    }

    @Override
    public double[][] data() {
        return new double[this.shape().rows][this.shape().columns];
    }

    public IDoubleMatrix plus(IDoubleMatrix m) {
        return m;
    }

    public IDoubleMatrix plus(double x) {
        return new ConstantMatrix(this.shape(), x);
    }

    public IDoubleMatrix minus(IDoubleMatrix m) {
        return m.times(-1);
    }

    public IDoubleMatrix minus(double x) {
        return new ConstantMatrix(this.shape(), (-1) * x);
    }

    public IDoubleMatrix times(IDoubleMatrix m) {
        return new NullMatrix(Shape.matrix(this.shape().rows, this.shape().columns));
    }

    public IDoubleMatrix times(double x) {
        return this;
    }

    public double normOne() {
        return 0;
    }

    public double normInfinity() {
        return 0;
    }

    public double frobeniusNorm() {
        return 0;
    }
}
