package tensor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MatrixImpl implements Matrix {
    private final List<List<Scalar>> elements;

    // 06. 고정값으로 채운 m x n 행렬
    MatrixImpl(int rows, int cols, String value) {
        elements = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            List<Scalar> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                row.add(Factory.createScalar(value));
            }
            elements.add(row);
        }
    }

    // 07. 랜덤값으로 채운 m x n 행렬
    MatrixImpl(int rows, int cols, String min, String max) {
        elements = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            List<Scalar> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                row.add(Factory.createRandomScalar(min, max));
            }
            elements.add(row);
        }
    }

    // 08. CSV 파일로부터 생성
    MatrixImpl(String filepath) {
        elements = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                List<Scalar> row = new ArrayList<>();

                for (String token : tokens) {
                    try {
                        row.add(Factory.createScalar(token.trim()));
                    } catch (NumberFormatException e) {
                        throw new TensorInvalidInputException("Invalid number in CSV: " + token.trim());
                    }
                }

                elements.add(row);
            }

        } catch (TensorFileNotFoundException e) {
            throw new TensorFileNotFoundException("CSV file not found: " + filepath);
        } catch (IOException e) {
            throw new TensorFileReadException("Failed to read CSV file: " + filepath);
        }
    }

    // 09. 2차원 배열로 생성
    MatrixImpl(String[][] values) {
        elements = new ArrayList<>();
        for (String[] row : values) {
            List<Scalar> newRow = new ArrayList<>();
            for (String val : row) {
                newRow.add(Factory.createScalar(val));
            }
            elements.add(newRow);
        }
    }

    // 10. 단위 행렬 생성
    MatrixImpl(int size) {
        elements = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            List<Scalar> row = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                row.add(Factory.createScalar(i == j ? "1.0" : "0.0"));
            }
            elements.add(row);
        }
    }

    // 11. 요소 조회
    @Override
    public Scalar getMatrixElement(int row, int col) {
        checkIndices(row, col);
        return elements.get(row).get(col);
    }

    // 12. 요소 설정
    @Override
    public void setMatrixElement(int row, int col, Scalar value) {
        checkIndices(row, col);
        elements.get(row).set(col, value);
    }

    // 13. 행/열 개수
    @Override
    public int getMatrixRowCount() {
        return elements.size();
    }

    @Override
    public int getMatrixColumnCount() {
        return elements.isEmpty() ? 0 : elements.get(0).size();
    }

    //14.문자열 출력
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (List<Scalar> row : elements) {
            for (int j = 0; j < row.size(); j++) {
                Scalar s = row.get(j);
                String val = s.getValue(); // 그냥 getValue 그대로 출력
                sb.append(val);
                if (j < row.size() - 1) sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    // 15. equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Matrix)) return false;
        Matrix other = (Matrix) obj;
        if (getMatrixRowCount() != other.getMatrixRowCount() ||
                getMatrixColumnCount() != other.getMatrixColumnCount()) return false;
        for (int i = 0; i < getMatrixRowCount(); i++) {
            for (int j = 0; j < getMatrixColumnCount(); j++) {
                if (!getMatrixElement(i, j).equals(other.getMatrixElement(i, j))) return false;
            }
        }
        return true;
    }

    // 17. 깊은 복사
    @Override
    public Matrix clone() {
        String[][] copy = new String[getMatrixRowCount()][getMatrixColumnCount()];
        for (int i = 0; i < getMatrixRowCount(); i++) {
            for (int j = 0; j < getMatrixColumnCount(); j++) {
                copy[i][j] = getMatrixElement(i, j).getValue();  // String 기반 복사
            }
        }
        return Factory.createMatrixFromArray(copy);
    }
    // 22. 행렬 덧셈 (자기 자신에 더함)
    @Override
    public void add(Matrix other) {
        if (getMatrixRowCount() != other.getMatrixRowCount() ||
                getMatrixColumnCount() != other.getMatrixColumnCount()) {
            throw new TensorSizeMismatchException("Matrix sizes do not match.");
        }

        for (int i = 0; i < getMatrixRowCount(); i++) {
            for (int j = 0; j < getMatrixColumnCount(); j++) {
                getMatrixElement(i, j).add(other.getMatrixElement(i, j));
            }
        }
    }

    @Override
    public void multiply(Matrix other) {
        // this = other * this (좌측 곱)
        if (other.getMatrixColumnCount() != getMatrixRowCount()) {
            throw new MatrixMulMismatchException("Matrix multiplication mismatch: left.columns != this.rows");
        }

        int rows = other.getMatrixRowCount();       // A의 행
        int cols = getMatrixColumnCount();          // B의 열
        int inner = getMatrixRowCount();            // 공통 차원 (A의 열 == B의 행)

        List<List<Scalar>> newElements = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            List<Scalar> newRow = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                BigDecimal sum = BigDecimal.ZERO;
                for (int k = 0; k < inner; k++) {
                    BigDecimal a = new BigDecimal(other.getMatrixElement(i, k).getValue());
                    BigDecimal b = new BigDecimal(getMatrixElement(k, j).getValue());
                    sum = sum.add(a.multiply(b));
                }
                newRow.add(Factory.createScalar(sum.toPlainString()));
            }
            newElements.add(newRow);
        }

        // 기존 elements의 내부 데이터 교체 (final 필드라 재할당은 불가)
        elements.clear();
        elements.addAll(newElements);
    }

        @Override
        public void multiplyRight(Matrix other) {
            // this = this * other (우측 곱)
            if (getMatrixColumnCount() != other.getMatrixRowCount()) {
                throw new MatrixMulMismatchException("Matrix multiplication mismatch: this.columns != right.rows");
            }

            int rows = getMatrixRowCount();
            int cols = other.getMatrixColumnCount();
            int inner = getMatrixColumnCount();
            List<List<Scalar>> newElements = new ArrayList<>();

            for (int i = 0; i < rows; i++) {
                List<Scalar> row = new ArrayList<>();
                for (int j = 0; j < cols; j++) {
                    BigDecimal sum = BigDecimal.ZERO;
                    for (int k = 0; k < inner; k++) {
                        BigDecimal a = new BigDecimal(getMatrixElement(i, k).getValue());
                        BigDecimal b = new BigDecimal(other.getMatrixElement(k, j).getValue());
                        sum = sum.add(a.multiply(b));
                    }
                    row.add(Factory.createScalar(sum.toPlainString()));
                }
                newElements.add(row);
            }

        for (int i = 0; i < rows; i++) {
            elements.set(i, newElements.get(i));
        }
    }

    // 28. static add
    public static Matrix add(Matrix a, Matrix b) {
        Matrix copy = a.clone();
        copy.add(b);
        return copy;
    }

    // 29. static multiply
    public static Matrix multiply(Matrix a, Matrix b) {
        Matrix copy = a.clone();
        copy.multiply(b);
        return copy;
    }

    // 32. 비정적 concatColumns (열 방향 이어붙임)
    @Override
    public void concatColumns(Matrix other) {
        if (getMatrixRowCount() != other.getMatrixRowCount()) {
            throw new TensorSizeMismatchException("matrix columns size is different");
        }

        for (int i = 0; i < getMatrixRowCount(); i++) {
            for (int j = 0; j < other.getMatrixColumnCount(); j++) {
                elements.get(i).add(other.getMatrixElement(i, j));
            }
        }
    }

    // 33. 비정적 concatRows (행 방향 이어붙임)
    @Override
    public void concatRows(Matrix other) {
        if (getMatrixColumnCount() != other.getMatrixColumnCount()) {
            throw new TensorSizeMismatchException("matrix row size is different");
        }

        for (int i = 0; i < other.getMatrixRowCount(); i++) {
            List<Scalar> newRow = new ArrayList<>();
            for (int j = 0; j < other.getMatrixColumnCount(); j++) {
                newRow.add(other.getMatrixElement(i, j));
            }
            elements.add(newRow);
        }
    }

    // 32. 정적 concatColumns
    public static Matrix concatColumns(Matrix a, Matrix b) {
        Matrix copy = a.clone();
        copy.concatColumns(b);
        return copy;
    }

    // 33. 정적 concatRows
    public static Matrix concatRows(Matrix a, Matrix b) {
        Matrix copy = a.clone();
        copy.concatRows(b);
        return copy;
    }

    // 34. 행 추출
    @Override
    public Vector extractRow(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= getMatrixRowCount()) {
            throw new TensorInvalidInputException("Invalid row index: " + rowIndex);
        }

        int cols = getMatrixColumnCount();
        String[] row = new String[cols];
        for (int j = 0; j < cols; j++) {
            row[j] = getMatrixElement(rowIndex, j).getValue();
        }

        return Factory.createVectorFromArray(row);
    }

    // 35. 열 추출
    @Override
    public Vector extractColumn(int columnIndex) {
        if (columnIndex < 0 || columnIndex >= getMatrixColumnCount()) {
            throw new TensorInvalidInputException("Invalid column index: " + columnIndex);
        }

        int rows = getMatrixRowCount();
        String[] col = new String[rows];
        for (int i = 0; i < rows; i++) {
            col[i] = getMatrixElement(i, columnIndex).getValue();
        }

        return Factory.createVectorFromArray(col);
    }
    // 36. 부분 행렬 추출
    @Override
    public Matrix subMatrix(int startRow, int endRow, int startCol, int endCol) {
        checkIndices(startRow, startCol);
        checkIndices(endRow, endCol);
        if (startRow > endRow || startCol > endCol) {
            throw new TensorInvalidInputException("Invalid submatrix range.");
        }

        String[][] result = new String[endRow - startRow + 1][endCol - startCol + 1];
        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j <= endCol; j++) {
                result[i - startRow][j - startCol] = getMatrixElement(i, j).getValue();
            }
        }
        return Factory.createMatrixFromArray(result);
    }

    // 37. 소행렬 (minor)
    @Override
    public Matrix minor(int rowToExclude, int colToExclude) {
        checkIndices(rowToExclude, colToExclude);
        int rows = getMatrixRowCount();
        int cols = getMatrixColumnCount();

        String[][] result = new String[rows - 1][cols - 1];
        int r = 0;
        for (int i = 0; i < rows; i++) {
            if (i == rowToExclude) continue;
            int c = 0;
            for (int j = 0; j < cols; j++) {
                if (j == colToExclude) continue;
                result[r][c++] = getMatrixElement(i, j).getValue();
            }
            r++;
        }

        return Factory.createMatrixFromArray(result);
    }

    // 38. 전치 행렬
    @Override
    public Matrix transpose() {
        int rows = getMatrixRowCount();
        int cols = getMatrixColumnCount();

        String[][] result = new String[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = getMatrixElement(i, j).getValue();
            }
        }

        return Factory.createMatrixFromArray(result);
    }

    // 39. 대각합(trace)
    @Override
    public Scalar trace() {
        if (!isSquare()) {
            throw new NonSquareMatrixException("Trace is only defined for square matrices.");
        }

        Scalar sum = Factory.createScalar("0.0");
        for (int i = 0; i < getMatrixRowCount(); i++) {
            sum.add(getMatrixElement(i, i));
        }
        return sum;
    }

    // 40. 정사각 행렬인지 확인
    @Override
    public boolean isSquare() {
        return getMatrixRowCount() == getMatrixColumnCount();
    }

    // 41. 상삼각 행렬인지 확인
    @Override
    public boolean isUpperTriangular() {
        if (!isSquare()) return false;

        int n = getMatrixRowCount();
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                BigDecimal val = new BigDecimal(getMatrixElement(i, j).getValue());
                if (val.compareTo(BigDecimal.ZERO) != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    // 42. 하삼각 행렬인지 확인
    @Override
    public boolean isLowerTriangular() {
        if (!isSquare()) return false;
        int n = getMatrixRowCount();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                BigDecimal val = new BigDecimal(getMatrixElement(i, j).getValue());
                if (val.compareTo(BigDecimal.ZERO) != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    // 43. 단위행렬인지 확인
    @Override
    public boolean isIdentityMatrix() {
        if (!isSquare()) return false;
        int n = getMatrixRowCount();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                BigDecimal val = new BigDecimal(getMatrixElement(i, j).getValue());
                if (i == j && val.compareTo(BigDecimal.ONE) != 0) return false;
                if (i != j && val.compareTo(BigDecimal.ZERO) != 0) return false;
            }
        }
        return true;
    }

    // 44. 영행렬인지 확인
    @Override
    public boolean isZeroMatrix() {
        for (int i = 0; i < getMatrixRowCount(); i++) {
            for (int j = 0; j < getMatrixColumnCount(); j++) {
                BigDecimal val = new BigDecimal(getMatrixElement(i, j).getValue());
                if (val.compareTo(BigDecimal.ZERO) != 0) return false;
            }
        }
        return true;
    }

    // 45. 행 스왑
    @Override
    public void swapRows(int row1, int row2) {
        if (row1 == row2) return;
        checkRowIndex(row1);
        checkRowIndex(row2);
        List<Scalar> temp = elements.get(row1);
        elements.set(row1, elements.get(row2));
        elements.set(row2, temp);
    }

    // 46. 열 스왑
    @Override
    public void swapColumns(int col1, int col2) {
        if (col1 == col2) return;
        checkColumnIndex(col1);
        checkColumnIndex(col2);
        for (List<Scalar> row : elements) {
            Scalar temp = row.get(col1);
            row.set(col1, row.get(col2));
            row.set(col2, temp);
        }
    }

    // 47. 특정 행 스칼라배
    @Override
    public void scaleRow(int row, Scalar scalar) {
        checkRowIndex(row);
        for (Scalar s : elements.get(row)) {
            s.multiply(scalar);
        }
    }

    // 48. 특정 열 스칼라배
    @Override
    public void scaleColumn(int column, Scalar scalar) {
        checkColumnIndex(column);
        for (List<Scalar> row : elements) {
            row.get(column).multiply(scalar);
        }
    }

    // 49. scaledRow = targetRow + scalar * sourceRow
    @Override
    public void addScaledRow(int targetRow, int sourceRow, Scalar scalar) {
        checkRowIndex(targetRow);
        checkRowIndex(sourceRow);
        if (targetRow == sourceRow) return;

        for (int j = 0; j < getMatrixColumnCount(); j++) {
            Scalar scaled = getMatrixElement(sourceRow, j).clone();
            scaled.multiply(scalar);
            getMatrixElement(targetRow, j).add(scaled);
        }
    }

    // 50. scaledCol = targetCol + scalar * sourceCol
    @Override
    public void addScaledColumn(int targetCol, int sourceCol, Scalar scalar) {
        checkColumnIndex(targetCol);
        checkColumnIndex(sourceCol);
        if (targetCol == sourceCol) return;

        for (int i = 0; i < getMatrixRowCount(); i++) {
            Scalar scaled = getMatrixElement(i, sourceCol).clone();
            scaled.multiply(scalar);
            getMatrixElement(i, targetCol).add(scaled);
        }
    }

    // 51. RREF 변환
    @Override
    public Matrix toRref() {
        Matrix rref = this.clone();
        int rowCount = rref.getMatrixRowCount();
        int colCount = rref.getMatrixColumnCount();
        int lead = 0;

        for (int r = 0; r < rowCount; r++) {
            if (lead >= colCount) break;

            int i = r;
            while (i < rowCount && new BigDecimal(rref.getMatrixElement(i, lead).getValue()).compareTo(BigDecimal.ZERO) == 0) {
                i++;
            }
            if (i == rowCount) {
                lead++;
                r--;
                continue;
            }

            if (i != r) {
                rref.swapRows(i, r);
            }

            Scalar pivot = rref.getMatrixElement(r, lead);
            Scalar reciprocal = Factory.createScalar("1.0");
            reciprocal.setValue(BigDecimal.ONE
                    .divide(new BigDecimal(pivot.getValue()), 10, RoundingMode.HALF_UP)
                    .toPlainString());
            rref.scaleRow(r, reciprocal);

            for (int j = 0; j < rowCount; j++) {
                if (j == r) continue;
                Scalar factor = rref.getMatrixElement(j, lead).clone();
                factor.multiply(Factory.createScalar("-1.0"));
                rref.addScaledRow(j, r, factor);
            }
            lead++;
        }

        return rref;
    }

    // 52. RREF 여부 확인
    @Override
    public boolean isRref() {
        int lead = -1;
        for (int i = 0; i < getMatrixRowCount(); i++) {
            int rowLead = -1;
            for (int j = 0; j < getMatrixColumnCount(); j++) {
                if (new BigDecimal(getMatrixElement(i, j).getValue()).compareTo(BigDecimal.ZERO) != 0) {
                    rowLead = j;
                    break;
                }
            }
            if (rowLead == -1) continue; // zero row
            if (!new BigDecimal(getMatrixElement(i, rowLead).getValue()).equals(BigDecimal.ONE)) return false;
            for (int k = 0; k < getMatrixRowCount(); k++) {
                if (k != i) {
                    BigDecimal val = new BigDecimal(getMatrixElement(k, rowLead).getValue());
                    if (val.compareTo(BigDecimal.ZERO) != 0) return false;
                }
            }
            if (rowLead <= lead) return false;
            lead = rowLead;
        }
        return true;
    }

    // 53. 행렬식 (재귀 방식)
    @Override
    public Scalar determinant() {
        if (!isSquare()) {
            throw new NonSquareMatrixException("Determinant only defined for square matrices.");
        }

        int size = getMatrixRowCount();
        if (size == 1) return getMatrixElement(0, 0).clone();

        if (size == 2) {
            BigDecimal a = new BigDecimal(getMatrixElement(0, 0).getValue());
            BigDecimal b = new BigDecimal(getMatrixElement(0, 1).getValue());
            BigDecimal c = new BigDecimal(getMatrixElement(1, 0).getValue());
            BigDecimal d = new BigDecimal(getMatrixElement(1, 1).getValue());
            return Factory.createScalar(a.multiply(d).subtract(b.multiply(c)).toPlainString());
        }

        Scalar det = Factory.createScalar("0.0");
        for (int j = 0; j < size; j++) {
            Scalar cofactor = getMatrixElement(0, j).clone();
            Matrix minor = minor(0, j);
            Scalar minorDet = minor.determinant();
            cofactor.multiply(minorDet);
            if (j % 2 != 0) {
                cofactor.multiply(Factory.createScalar("-1"));
            }
            det.add(cofactor);
        }
        return det;
    }

    // 54. 역행렬 (가우스-조르당 방식)
    @Override
    public Matrix inverse() {
        if (!isSquare()) {
            throw new NonSquareMatrixException("Only square matrices can be inverted.");
        }

        int size = getMatrixRowCount();
        MatrixImpl copy = (MatrixImpl) this.clone();
        MatrixImpl identity = (MatrixImpl) Factory.createIdentityMatrix(size);

        int lead = 0;
        for (int r = 0; r < size; r++) {
            if (lead >= size) break;

            int i = r;
            while (i < size && new BigDecimal(copy.getMatrixElement(i, lead).getValue()).compareTo(BigDecimal.ZERO) == 0) {
                i++;
            }

            if (i == size) {
                lead++;
                r--;
                continue;
            }

            if (i != r) {
                copy.swapRows(i, r);
                identity.swapRows(i, r);
            }

            Scalar pivot = copy.getMatrixElement(r, lead);
            Scalar reciprocal = Factory.createScalar("1.0");
            reciprocal.setValue(BigDecimal.ONE
                    .divide(new BigDecimal(pivot.getValue()), 10, RoundingMode.HALF_UP)
                    .toPlainString());

            copy.scaleRow(r, reciprocal);
            identity.scaleRow(r, reciprocal);

            for (int j = 0; j < size; j++) {
                if (j == r) continue;
                Scalar factor = copy.getMatrixElement(j, lead).clone();
                factor.multiply(Factory.createScalar("-1.0"));
                copy.addScaledRow(j, r, factor);
                identity.addScaledRow(j, r, factor);
            }
            lead++;
        }

        if (!copy.isIdentityMatrix()) {
            throw new TensorArithmeticException("Matrix is singular and cannot be inverted.");
        }

        return identity;
    }

    @Override
    public int compareTo(Matrix o) {
        return 0;
    }

    // 유효성 검사 메서드
    private void checkRowIndex(int row) {
        if (row < 0 || row >= getMatrixRowCount()) {
            throw new TensorInvalidInputException("Invalid row index: " + row);
        }
    }

    private void checkColumnIndex(int col) {
        if (col < 0 || col >= getMatrixColumnCount()) {
            throw new TensorInvalidInputException("Invalid column index: " + col);
        }
    }

    private void checkIndices(int row, int col) {
        checkRowIndex(row);
        checkColumnIndex(col);
    }
}
