package pl.edu.mimuw.matrix;

public class RepeatedRowMatrix extends DenseDoubleMatrix {
    public static double[][] repeatRow(int size, double... values) {
        double[][] res = new double[size][values.length];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < values.length; j++)
                res[i][j] = values[j];
        return res;
    }

    public RepeatedRowMatrix(int size, double... values) {
        super(Shape.matrix(size, values.length), repeatRow(size, values));
    }

    public String toString() {
        String res = "Repeated Row Matrix of dimensions: " + this.shape().rows + "x" + this.shape().columns + "\n";
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
