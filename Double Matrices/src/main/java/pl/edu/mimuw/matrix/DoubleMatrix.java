package pl.edu.mimuw.matrix;

public abstract class DoubleMatrix implements IDoubleMatrix {
    protected final Shape myShape;

    public DoubleMatrix(Shape shape) {
        this.myShape = shape;
    }

    public abstract IDoubleMatrix plus(double x);
    public abstract IDoubleMatrix plus(IDoubleMatrix m);

    public abstract IDoubleMatrix minus(double x);
    public abstract IDoubleMatrix minus(IDoubleMatrix m);

    public abstract IDoubleMatrix times(double x);
    public abstract IDoubleMatrix times(IDoubleMatrix m);

    public abstract double get(int row, int column);

    public abstract double normOne();
    public abstract double normInfinity();
    public abstract double frobeniusNorm();

    public double[][] data() {
        double[][] res = new double[this.shape().rows][this.shape().columns];
        for (int i = 0; i < this.shape().rows; i++)
            for (int j = 0; j < this.shape().columns; j++)
                res[i][j] = this.get(i , j);
        return res;
    }

    public double[][] defaultProduct(double[][] a, double[][] b) {
        int aLen = a.length;
        double[][] newContents = new double[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                double res = 0;
                for (int k = 0; k < b.length; k++)
                    res += a[i][k] * b[k][j];
                newContents[i][j] = res;
            }
        }
        return newContents;
    }

    public double[][] defaultSum(double[][] a, double[][] b) {
        double[][] newContents = new double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[0].length; j++)
                newContents[i][j] = a[i][j] + b[i][j];
        return newContents;
    }

    public abstract String toString();

    public Shape shape() {
        return this.myShape;
    }
}