    package tensor;

    public interface Matrix extends Cloneable {

        // 11. 요소 접근
        Scalar getMatrixElement(int row, int col);
        void setMatrixElement(int row, int col, Scalar value);

        // 13. 행/열 정보
        int getMatrixRowCount();
        int getMatrixColumnCount();

        // 17. deep copy
        Matrix clone();

        // 14. 문자열 출력
        @Override
        String toString();
        String toString(boolean rounding);

        // 15. equals
        @Override
        boolean equals(Object obj);

        // 22. 비정적 행렬 연산
        void add(Matrix other);
        void multiply(Matrix other);
        void multiplyRight(Matrix other);

        // 28. 정적 행렬 연산
        static Matrix add(Matrix a, Matrix b) {
            Matrix copy = a.clone();
            copy.add(b);
            return copy;
        }
        //29.
        static Matrix multiply(Matrix a, Matrix b) {
            Matrix copy = a.clone();
            copy.multiply(b);
            return copy;
        }
        // 34. 행/열 추출
        Vector extractRow(int rowIndex);
        Vector extractColumn(int colIndex);

        // 36. 부분행렬, 소행렬
        Matrix subMatrix(int startRow, int endRow, int startCol, int endCol);
        Matrix minor(int rowToExclude, int colToExclude);

        // 38. 전치행렬, 대각합
        Matrix transpose();
        Scalar trace();

        // 40. 성질
        boolean isSquare();
        boolean isUpperTriangular();
        boolean isLowerTriangular();
        boolean isIdentityMatrix();
        boolean isZeroMatrix();

        // 45. 행/열 교환
        void swapRows(int row1, int row2);
        void swapColumns(int col1, int col2);

        // 47. 행/열 스케일
        void scaleRow(int row, Scalar scalar);
        void scaleColumn(int col, Scalar scalar);

        // 49. 행/열 스케일 덧셈
        void addScaledRow(int targetRow, int sourceRow, Scalar scalar);
        void addScaledColumn(int targetCol, int sourceCol, Scalar scalar);

        // 51. RREF
        Matrix toRref();
        boolean isRref();

        // 53. 행렬식, 역행렬
        Scalar determinant();
        Matrix inverse();

        // 32. 연결
        Matrix concatColumns(Matrix other);
        Matrix concatRows(Matrix other);

        static Matrix concatColumns(Matrix a, Matrix b) {
            return a.concatColumns(b);
        }

        static Matrix concatRows(Matrix a, Matrix b) {
            return a.concatRows(b);
        }
    }
