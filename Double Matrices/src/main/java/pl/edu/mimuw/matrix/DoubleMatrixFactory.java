package pl.edu.mimuw.matrix;

public class DoubleMatrixFactory {

  private DoubleMatrixFactory() {
  }

  public static IDoubleMatrix sparse(Shape shape, MatrixCellValue... values){
    return new SparseDoubleMatrix(shape, values);
  }

  public static IDoubleMatrix full(double[][] values) {
    assert (values != null);
    assert (values.length > 0);
    assert (values[0].length > 0);
    return new DenseDoubleMatrix(Shape.matrix(values.length, values[0].length), values);
  }

  public static IDoubleMatrix identity(int size) {
    return new IdentityMatrix(size);
  }

  public static IDoubleMatrix diagonal(double... diagonalValues) {
    return new DiagonalMatrix(diagonalValues);
  }

  public static IDoubleMatrix antiDiagonal(double... antiDiagonalValues) {
    return new AntiDiagonalMatrix(antiDiagonalValues);
  }

  public static IDoubleMatrix vector(double... values){
    return new MatrixVector(values);
  }

  public static IDoubleMatrix zero(Shape shape) {
    return new NullMatrix(shape);
  }
}
