package pl.edu.mimuw.matrix;

public class RepeatedColumnMatrix extends DenseDoubleMatrix {
    public static double[][] repeatColumn(int size, double... values) {
        double[][] res = new double[values.length][size];
        for (int i = 0; i < values.length; i++)
            for (int j = 0; j < size; j++)
                res[i][j] = values[i];
        return res;
    }

    public RepeatedColumnMatrix(int size, double... values) {
        super(Shape.matrix(size, values.length), repeatColumn(size, values));
    }

    public String toString() {
        String res = "Repeated Column Matrix of dimensions: " + this.shape().rows + "x" + this.shape().columns + "\n";
        for (int i = 0; i < this.shape().rows; i++) {
            res += this.get(i, 0) + " ... " + this.get(i, 0) + "\n";
        }
        return res;
    }
}
