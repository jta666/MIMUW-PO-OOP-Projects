package pl.edu.mimuw.matrix;

public interface IDoubleMatrix {

  IDoubleMatrix times(IDoubleMatrix other);

  //IDoubleMatrix timesMe(IDoubleMatrix other);

  IDoubleMatrix times(double scalar);

  IDoubleMatrix plus(IDoubleMatrix other);

  //IDoubleMatrix plusMe(IDoubleMatrix other);

  IDoubleMatrix plus(double scalar);

  IDoubleMatrix minus(IDoubleMatrix other);

  //IDoubleMatrix minusMe(IDoubleMatrix other);

  IDoubleMatrix minus(double scalar);

  double get(int row, int column);

  double[][] data();

  double normOne();

  double normInfinity();

  double frobeniusNorm();

  String toString();

  Shape shape();
}
