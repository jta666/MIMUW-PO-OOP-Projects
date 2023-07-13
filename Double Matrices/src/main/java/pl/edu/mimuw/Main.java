package pl.edu.mimuw;

import pl.edu.mimuw.matrix.*;

public class Main {

  public static void main(String[] args) {
    Shape testShape = Shape.matrix(10, 10);

    ConstantMatrix constantMatrix = new ConstantMatrix(testShape, 6);
    System.out.println(constantMatrix);

    NullMatrix nullMatrix = new NullMatrix(testShape);
    System.out.println(nullMatrix);

    double[][] denseContents = new double[10][10];
    for (int i = 0; i < 10; i++)
      for (int j = 0; j < 10; j++)
        denseContents[i][j] = i * j;

    DenseDoubleMatrix denseDoubleMatrix = new DenseDoubleMatrix(testShape, denseContents);
    System.out.println(denseDoubleMatrix);

    MatrixCellValue[] mvs = {MatrixCellValue.cell(1, 1, 3.0), MatrixCellValue.cell(5, 5, 4.0), MatrixCellValue.cell(7, 4, 444.0)};

    SparseDoubleMatrix sparseDoubleMatrix = new SparseDoubleMatrix(testShape, mvs);
    System.out.println(sparseDoubleMatrix);

    RepeatedRowMatrix repeatedRowMatrix = new RepeatedRowMatrix(10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    System.out.println(repeatedRowMatrix);

    RepeatedColumnMatrix repeatedColumnMatrix = new RepeatedColumnMatrix(10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    System.out.println(repeatedColumnMatrix);

    DiagonalMatrix diagonalMatrix = new DiagonalMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    System.out.println(diagonalMatrix);

    AntiDiagonalMatrix antiDiagonalMatrix = new AntiDiagonalMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    System.out.println(antiDiagonalMatrix);
  }
}
