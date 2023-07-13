package pl.edu.mimuw.matrix;

import java.util.*;

public class SparseDoubleMatrix extends DoubleMatrix {
    protected final Vector<MatrixCellValue> contents;

    private int searchFirstByRow(Vector<MatrixCellValue> v, int i, int beg, int fin) {
        int a = beg, b = fin;
        while (a < b) {
            int s = a + (b - a) / 2;
            if (v.get(s).row < i)
                a = s + 1;
            else
                b = s;
        }
        if (a != v.capacity() && v.get(a).row == i)
            return a;
        else
            return -1;
    }

    private int searchLastByRow(Vector<MatrixCellValue> v, int i, int beg, int fin) {
        int a = beg, b = fin;
        while (a < b) {
            int s = a + (b - a) / 2;
            if (v.get(s).row <= i)
                a = s + 1;
            else
                b = s;
        }
        if (v.get(a - 1).row == i)
            return a - 1;
        else
            return -1;
    }

    private int searchByColumn(Vector<MatrixCellValue> v, int i, int beg, int fin) {
        int a = beg, b = fin;
        while (a < b) {
            int s = a + (b - a) / 2;
            if (v.get(s).column < i)
                a = s + 1;
            else
                b = s;
        }
        if (v.get(a).column == i)
            return a;
        else
            return -1;
    }

    private int searchFirstByColumn(Vector<MatrixCellValue> v, int i, int beg, int fin) {
        return searchByColumn(v, i, beg, fin);
    }

    private int searchLastByColumn(Vector<MatrixCellValue> v, int i, int beg, int fin) {
        int a = beg, b = fin;
        while (a < b) {
            int s = a + (b - a) / 2;
            if (v.get(s).column <= i)
                a = s + 1;
            else
                b = s;
        }
        if (v.get(a - 1).column == i)
            return a - 1;
        else
            return -1;
    }

    private boolean lexBigger(MatrixCellValue mv1, MatrixCellValue mv2) {
        if (mv1.row > mv2.row)
            return true;
        if (mv2.row > mv1.row)
            return false;
        if (mv1.column > mv2.column)
            return true;
        return false;
    }

    private boolean lexColumnBigger(MatrixCellValue mv1, MatrixCellValue mv2) {
        if (mv1.column > mv2.column)
            return true;
        if (mv2.column > mv1.column)
            return false;
        if (mv1.row > mv2.row)
            return true;
        return false;
    }

    private void merge(MatrixCellValue[] lArr, MatrixCellValue[] rArr,
                             MatrixCellValue[] arr, int lSize, int rSize) {
        int i = 0, l = 0, r = 0;
        while (l < lSize && r < rSize) {
            if (lexBigger(rArr[r], lArr[l]))
                arr[i++] = lArr[l++];
            else
                arr[i++] = rArr[r++];
        }
        while (l < lSize)
            arr[i++] = lArr[l++];
        while (r < rSize)
            arr[i++] = rArr[r++];
    }

    private void mergeByColumn(MatrixCellValue[] lArr, MatrixCellValue[] rArr,
                               MatrixCellValue[] arr, int lSize, int rSize) {
        int i = 0, l = 0, r = 0;
        while (l < lSize && r < rSize) {
            if (lexColumnBigger(rArr[r], lArr[l]))
                arr[i++] = lArr[l++];
            else
                arr[i++] = rArr[r++];
        }
        while (l < lSize)
            arr[i++] = lArr[l++];
        while (r < rSize)
            arr[i++] = rArr[r++];
    }

    private void sort(MatrixCellValue[] arr, int len) {
        if (len < 2)
            return;
        int mid = len / 2;
        MatrixCellValue[] lArr = new MatrixCellValue[mid];
        MatrixCellValue[] rArr = new MatrixCellValue[len - mid];
        int k = 0;
        for (int i = 0; i < len; ++i) {
            if (i < mid)
                lArr[i] = arr[i];
            else
                rArr[k++] = arr[i];
        }
        sort(lArr, mid);
        sort(rArr,len-mid);
        merge(lArr, rArr, arr, mid,len-mid);
    }

    private void sortByColumn(MatrixCellValue[] arr, int len) {
        if (len < 2)
            return;
        int mid = len / 2;
        MatrixCellValue[] lArr = new MatrixCellValue[mid];
        MatrixCellValue[] rArr = new MatrixCellValue[len - mid];
        int k = 0;
        for (int i = 0; i < len; ++i) {
            if (i < mid)
                lArr[i] = arr[i];
            else
                rArr[k++] = arr[i];
        }
        sortByColumn(lArr, mid);
        sortByColumn(rArr,len-mid);
        mergeByColumn(lArr, rArr, arr, mid,len-mid);
    }

    public SparseDoubleMatrix(Shape shape, MatrixCellValue... values) {
        super(shape);
        Vector<MatrixCellValue> v = new Vector<MatrixCellValue>(values.length);
        sort(values, values.length);
        for (MatrixCellValue mv : values) {
            assert (mv.row >= 0 && mv.row < shape.rows);
            assert (mv.column >= 0 && mv.column < shape.columns);
            v.add(mv);
        }
        this.contents = v;
    }

    public SparseDoubleMatrix(Shape shape, Vector<MatrixCellValue> values) {
        super(shape);
        this.contents = values;
    }

    public int realSize() {
        return this.contents.size();
    }

    public Vector<MatrixCellValue> getContents() {
        return contents;
    }

    public MatrixCellValue getByIndex(int i) {
        return this.contents.get(i);
    }

    public MatrixCellValue getMv(int row, int column) {
        assert (row >= 0 && row < this.shape().rows);
        assert (column >= 0 && column < this.shape().columns);
        int beg = searchFirstByRow(this.contents, row, 0, this.realSize());
        if (beg == -1)
            return null;
        int fin = searchLastByRow(this.contents, row, 0, this.realSize());
        int i = searchByColumn(this.contents, column, beg, fin);
        if (i == -1)
            return null;
        return this.contents.get(i);
    }

    public double get(int row, int column) {
        assert (row >= 0 && row < this.shape().rows);
        assert (column >= 0 && column < this.shape().columns);
        MatrixCellValue mv = this.getMv(row, column);
        if (mv == null)
            return 0;
        else
            return mv.value;
    }

    @Override
    public SparseDoubleMatrix plus(double x) {
        Vector<MatrixCellValue> newContents = new Vector<MatrixCellValue>(this.contents.size());
        for (MatrixCellValue mv : this.contents) {
            MatrixCellValue newMv = new MatrixCellValue(mv.row, mv.column, mv.value + x);
            newContents.add(newMv);
        }
        return new SparseDoubleMatrix(this.shape(), newContents);
    }

    public IDoubleMatrix plus(IDoubleMatrix m) {
        assert (this.shape().equals(m.shape()));
        if (m.getClass() == this.getClass()) {
            Vector<MatrixCellValue> newContents = new Vector<MatrixCellValue>(this.contents.size());
            int l = 0, r = 0;
            while (l < this.realSize() && r < ((SparseDoubleMatrix) m).realSize()) {
                if (lexBigger(this.getByIndex(l), ((SparseDoubleMatrix) m).getByIndex(r))) {
                    newContents.add(((SparseDoubleMatrix) m).getByIndex(r));
                    r++;
                } else if (lexBigger(((SparseDoubleMatrix) m).getByIndex(r), this.getByIndex(l))) {
                    newContents.add(this.getByIndex(l));
                    l++;
                } else {
                    int currRow = this.getByIndex(l).row;
                    int currCol = this.getByIndex(l).column;
                    assert(currRow == ((SparseDoubleMatrix) m).getByIndex(r).row);
                    assert(currCol == ((SparseDoubleMatrix) m).getByIndex(r).column);
                    double v1 = this.getByIndex(l).value;
                    double v2 = ((SparseDoubleMatrix) m).getByIndex(r).value;
                    MatrixCellValue newOne = new MatrixCellValue(currRow, currCol, v1 + v2);
                    newContents.add(newOne);
                    l++;
                    r++;
                }
            }
            while (l < this.realSize()) {
                newContents.add(this.getByIndex(l));
                l++;
            }
            while (r < ((SparseDoubleMatrix) m).realSize()) {
                newContents.add(((SparseDoubleMatrix) m).getByIndex(r));
                r++;
            }
            return new SparseDoubleMatrix(this.shape(), newContents);
        } else
            return new DenseDoubleMatrix(this.shape(), defaultSum(this.data(), m.data()));
    }

    public IDoubleMatrix minus(double x) {
        return this.plus((-1) * x);
    }

    public IDoubleMatrix minus(IDoubleMatrix m) {
        return this.plus(m.times(-1));
    }

    public IDoubleMatrix times(double x) {
        Vector<MatrixCellValue> newContents = new Vector<MatrixCellValue>(this.contents.size());
        for (MatrixCellValue mv : this.contents) {
            MatrixCellValue newMv = new MatrixCellValue(mv.row, mv.column, mv.value * x);
            newContents.add(newMv);
        }
        return new SparseDoubleMatrix(this.shape(), newContents);
    }

    public IDoubleMatrix times(IDoubleMatrix m) {
        int a = this.shape().columns;
        int b = m.shape().rows;
        assert (a == b);
        if (m.getClass() == this.getClass()) {
            Vector<Integer> rows = new Vector<Integer>();
            rows.add(this.getByIndex(0).row);
            for (int i = 1; i < this.realSize(); i++)
                if (this.getByIndex(i).row != this.getByIndex(i - 1).row)
                    rows.add(this.getByIndex(i).row);

            int mSize = ((SparseDoubleMatrix) m).realSize();
            Vector<MatrixCellValue> mCopy = ((SparseDoubleMatrix) m).getContents();
            MatrixCellValue[] mArr = new MatrixCellValue[mSize];
            for(int i = 0; i < mSize; i++)
                mArr[i] = mCopy.get(i);
            sortByColumn(mArr, mArr.length);

            Vector<Integer> columns = new Vector<Integer>();
            columns.add(mArr[0].column);
            for (int i = 1; i < mSize; i++)
                if (mArr[i].column != mArr[i - 1].column)
                    columns.add(mArr[i].column);

            Vector<MatrixCellValue> mCopySorted = new Vector<MatrixCellValue>(mSize);
            for (int i = 0; i < mSize; i++)
                mCopySorted.add(mArr[i]);

            Vector<MatrixCellValue> newContents = new Vector<MatrixCellValue>();

            for (int i : rows) {
                int rowBeg = searchFirstByRow(this.contents, i, 0, this.realSize());
                int rowFin = searchLastByRow(this.contents, i, 0, this.realSize());
                int rowLen = rowFin - rowBeg + 1;
                for (int j : columns) {
                    int colBeg = searchFirstByColumn(mCopySorted, j, 0, mSize);
                    int colFin = searchLastByColumn(mCopySorted, j, 0, mSize);
                    int colLen = colFin - colBeg + 1;
                    double res = 0;
                    for (int k = 0; k < rowLen; k++) {
                        int currCol = this.contents.get(rowBeg + k).column;
                        int match = searchFirstByRow(mCopySorted, currCol, colBeg, colFin);
                        if (match != -1)
                            res += (this.get(i, currCol) * m.get(mCopySorted.get(match).row, j));
                    }
                    if (res != 0)
                        newContents.add(new MatrixCellValue(i, j, res));
                }
            }
            if (newContents.isEmpty())
                return new NullMatrix(this.shape());
            return new SparseDoubleMatrix(Shape.matrix(this.shape().rows, m.shape().columns),
                    newContents);
        }
        return new DenseDoubleMatrix(Shape.matrix(this.shape().rows, m.shape().columns),
                defaultProduct(this.data(), m.data()));
    }

    @Override
    public double normOne() {
        MatrixCellValue[] mArr = new MatrixCellValue[this.realSize()];
        for (int i = 0; i < this.realSize(); i++)
            mArr[i] = this.getByIndex(i);
        sortByColumn(mArr, mArr.length);

        Vector<MatrixCellValue> byCol = new Vector<MatrixCellValue>(this.realSize());
        for (int i = 0; i < this.realSize(); i++)
            byCol.add(mArr[i]);

        double res = 0, curr = Math.abs(byCol.get(0).value);
        for (int i = 1; i < this.realSize(); i++) {
            if (byCol.get(i).column != byCol.get(i - 1).column) {
                if (res < curr)
                    res = curr;
                curr = Math.abs(byCol.get(i).value);
            } else
                curr += Math.abs(byCol.get(i).value);
        }
        if (res < curr)
            res = curr;
        return res;
    }

    @Override
    public double normInfinity() {
        double res = 0, curr = this.getByIndex(0).value;
        for (int i = 1; i < this.realSize(); i++) {
            if (this.getByIndex(i).row != this.getByIndex(i - 1).row) {
                if (res < curr)
                    res = curr;
                curr = Math.abs(this.getByIndex(i).value);
            } else
                curr += Math.abs(this.getByIndex(i).value);
        }
        if (res < curr)
            res = curr;
        return res;
    }

    @Override
    public double frobeniusNorm() {
        double sum = 0;
        for (MatrixCellValue mv : this.contents)
            sum += (Math.abs(mv.value) * Math.abs(mv.value));
        return Math.sqrt(sum);
    }

    protected String toStringHelp() {
        String res = "";
        for (int i = 0; i < this.shape().rows; i++) {
            String line = "";
            int zeroCounter = 0;
            for (int j = 0; j < this.shape().columns; j++) {
                if (this.get(i , j) == 0)
                    zeroCounter++;
                else {
                    if (zeroCounter == 0) {
                        line += this.get(i, j) + " ";
                    }
                    if (zeroCounter == 1) {
                        line += "0.0 " + this.get(i, j) + " ";
                    }
                    if (zeroCounter == 2) {
                        line += "0.0 0.0 " + this.get(i, j) + " ";
                    }
                    if (zeroCounter >= 3) {
                        line += "0.0 ... 0.0 " + this.get(i, j) + " ";
                    }
                    zeroCounter = 0;
                }
            }
            if (zeroCounter > 0) {
                if (zeroCounter == 1) {
                    line += "0.0 ";
                }
                if (zeroCounter == 2) {
                    line += "0.0 0.0 ";
                }
                if (zeroCounter >= 3) {
                    line += "0.0 ... 0.0 ";
                }
            }
            line += "\n";
            res += line;
        }
        return res;
    }

    public String toString() {
        String res = "Sparse Matrix of dimensions: " + this.shape().rows + "x" + this.shape().columns + "\n";
        res += this.toStringHelp();
        return res;
    }


}
