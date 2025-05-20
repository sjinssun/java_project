package Test;

import tensor.*;

public class Test {
    static String indent = "\t\t";

    public static void main(String[] args) {
        // no.01 | 값(String)을 지정하여 Scalar 생성할 수 있다.
        System.out.println("no.01 | 값(String)을 지정하여 Scalar 생성할 수 있다.");
        Scalar scalar1 = Factory.createScalar("3.14159265358979323846264338327950288419716939");
        System.out.println(indent + "scalar1 = " + scalar1);
        System.out.println();

// no.02 | -10.0 ~ 10.0 사이의 값을 가지는 Random Scalar 생성
        System.out.println("no.02 | -10.0 ~ 10.0 사이의 값을 가지는 Random Scalar 생성");
        Scalar scalar2 = Factory.createRandomScalar("-10.0", "10.0");
        System.out.println(indent + "scalar2 = " + scalar2);
        System.out.println();

// no.03 | 지정한 하나의 값을 모든 요소의 값으로 하는 n-차원 벡터 생성
        System.out.println("no.03 | 지정한 하나의 값을 모든 요소의 값으로 하는 n-차원 벡터 생성");
        Vector vector1 = Factory.createVector(5, "1.23");
        System.out.println(indent + "vector1 = " + vector1);
        System.out.println();

// no.04 | i 이상 j 미만 무작위 값의 n-차원 벡터 생성
        System.out.println("no.04 | i 이상 j 미만 무작위 값의 n-차원 벡터 생성");
        Vector vector2 = Factory.createRandomVector(5, "-5.0", "5.0");
        System.out.println(indent + "vector2 = " + vector2);
        System.out.println();

// no.05 | 1차원 배열로부터 n-차원 벡터 생성
        System.out.println("no.05 | 1차원 배열로부터 n-차원 벡터 생성");
        String[] vectorArray = {"1.0", "2.0", "3.0", "4.0", "5.0"};
        Vector vector3 = Factory.createVectorFromArray(vectorArray);
        System.out.println(indent + "vector3 = " + vector3);
        System.out.println();

// no.06 | 하나의 값으로 채워진 mxn 행렬 생성
        System.out.println("no.06 | 하나의 값으로 채워진 mxn 행렬 생성");
        Matrix matrix1 = Factory.createMatrix(2, 3, "0.5");
        System.out.println(indent + "matrix1 =\n" + matrix1);
        System.out.println();

// no.07 | 무작위 값으로 채워진 mxn 행렬 생성
        System.out.println("no.07 | 무작위 값으로 채워진 mxn 행렬 생성");
        Matrix matrix2 = Factory.createRandomMatrix(2, 3, "-1.0", "1.0");
        System.out.println(indent + "matrix2 =\n" + matrix2);
        System.out.println();

// no.08 | CSV 파일로부터 행렬 생성
        System.out.println("no.08 | CSV 파일로부터 행렬 생성");
        try {
            Matrix csvMatrix = Factory.createMatrixFromCsv("src/Test/sample.csv");
            System.out.println(indent + "csvMatrix =\n" + csvMatrix);
        } catch (TensorFileNotFoundException e) {
            System.out.println(indent + "[오류] 파일 없음: " + e.getMessage());
        } catch (TensorFileReadException e) {
            System.out.println(indent + "[오류] 읽기 실패: " + e.getMessage());
        } catch (TensorInvalidInputException e) {
            System.out.println(indent + "[오류] 유효하지 않은 입력: " + e.getMessage());
        }
        System.out.println();

// no.09 | 2차원 배열로부터 행렬 생성
        System.out.println("no.09 | 2차원 배열로부터 행렬 생성");
        String[][] matArr = {{"1.0", "2.0"}, {"3.0", "4.0"}};
        Matrix matrix3 = Factory.createMatrixFromArray(matArr);
        System.out.println(indent + "matrix3 =\n" + matrix3);
        System.out.println();

// no.10 | 단위행렬 생성
        System.out.println("no.10 | 단위행렬 생성");
        Matrix identity = Factory.createIdentityMatrix(3);
        System.out.println(indent + "identity =\n" + identity);
        System.out.println();

// no.11v | Vector 특정 요소 접근 및 설정
        System.out.println("no.11v | Vector 특정 요소 접근 및 설정");
        Scalar element = vector3.getVectorElement(2);
        System.out.println(indent + "접근 (index 2) = " + element.getValue());
        vector3.setVectorElement(2, Factory.createScalar("7"));
        System.out.println(indent + "수정 후 (index 2) = " + vector3.getVectorElement(2).getValue());
        System.out.println();

// no.11m | Matrix 특정 요소 접근 및 설정
        System.out.println("no.11m | Matrix 특정 요소 접근 및 설정");
        Scalar mElem = matrix3.getMatrixElement(0, 1);
        System.out.println(indent + "접근 (0,1) = " + mElem.getValue());
        matrix3.setMatrixElement(0, 1, Factory.createScalar("9.99"));
        System.out.println(indent + "수정 후 (0,1) = " + matrix3.getMatrixElement(0, 1).getValue());
        System.out.println();

// no.12 | Scalar 값 조회 및 설정
        System.out.println("no.12 | Scalar 값 조회 및 설정");
        System.out.println(indent + "scalar2 = " + scalar2);
        scalar2.setValue("8.88");
        System.out.println(indent + "수정 후 scalar2 = " + scalar2);
        System.out.println();

// no.13v | Vector 크기 조회
        System.out.println("no.13v | Vector 크기 조회");
        System.out.println(indent + "vector3 size = " + vector3.getVectorSize());
        System.out.println();

// no.13m | Matrix 행/열 크기 조회
        System.out.println("no.13m | Matrix 행/열 크기 조회");
        System.out.println(indent + "matrix3 row count = " + matrix3.getMatrixRowCount());
        System.out.println(indent + "matrix3 column count = " + matrix3.getMatrixColumnCount());
        System.out.println();

// no.14 | toString 메서드로 출력 (Scalar, Vector, Matrix)
        System.out.println("no.14s | Scalar 출력");
        System.out.println(indent + scalar1);
        System.out.println();

        System.out.println("no.14v | Vector 출력");
        System.out.println(indent + vector3);
        System.out.println();

        System.out.println("no.14m | Matrix 출력");
        System.out.println(indent + matrix3);
        System.out.println();

// no.15 | equals 메서드로 동등성 비교
        System.out.println("no.15s | Scalar 동등성 비교");
        Scalar scalar3 = Factory.createScalar(scalar1.getValue());
        System.out.println(indent + "scalar1.equals(scalar3): " + scalar1.equals(scalar3));
        System.out.println();

        System.out.println("no.15v | Vector 동등성 비교");
        Vector v4 = Factory.createVectorFromArray(vectorArray);
        System.out.println(indent + "vector3.equals(v4): " + vector3.equals(v4));
        System.out.println();

        System.out.println("no.15m | Matrix 동등성 비교");
        Matrix m4 = Factory.createMatrixFromArray(matArr);
        System.out.println(indent + "matrix3.equals(m4): " + matrix3.equals(m4));
        System.out.println();

        // no.16 | 스칼라 값의 대소 비교
        System.out.println("no.16 | 스칼라 값의 대소 비교");
        System.out.println(indent + "scalar1 = " + scalar1);
        System.out.println(indent + "scalar2 = " + scalar2);
        System.out.println(indent + "scalar1.compareTo(scalar2) = " + scalar1.compareTo(scalar2));
        System.out.println();

        // no.17 | 객체 복제 (Scalar, Vector, Matrix)
        System.out.println("no.17s | 스칼라 복제");
        Scalar scalarClone = scalar1.clone();
        System.out.println(indent + "original: " + scalar1);
        System.out.println(indent + "clone: " + scalarClone);
        System.out.println();

        System.out.println("no.17v | 벡터 복제");
        Vector vectorClone = vector3.clone();
        System.out.println(indent + "original: " + vector3);
        System.out.println(indent + "clone: " + vectorClone);
        System.out.println();

        System.out.println("no.17m | 행렬 복제");
        Matrix matrixClone = matrix3.clone();
        System.out.println(indent + "original:\n" + matrix3);
        System.out.println(indent + "clone:\n" + matrixClone);
        System.out.println();

        // no.18 | 스칼라 덧셈
        System.out.println("no.18 | 스칼라 덧셈");
        Scalar s1 = Factory.createScalar("2.0");
        Scalar s2 = Factory.createScalar("3.0");
        System.out.println(indent + "s1 = " + s1 + ", s2 = " + s2);
        s1.add(s2);
        System.out.println(indent + "s1 + s2 = " + s1);
        System.out.println();

        // no.19 | 스칼라 곱셈
        System.out.println("no.19 | 스칼라 곱셈");
        s1 = Factory.createScalar("2.0");
        s2 = Factory.createScalar("3.0");
        System.out.println(indent + "s1 = " + s1 + ", s2 = " + s2);
        s1.multiply(s2);
        System.out.println(indent + "s1 * s2 = " + s1);
        System.out.println();

        // no.20 | 벡터 덧셈
        System.out.println("no.20 | 벡터 덧셈");
        Vector va = Factory.createVector(3, "1.0");
        Vector vb = Factory.createVector(3, "2.0");
        System.out.println(indent + "va = " + va);
        System.out.println(indent + "vb = " + vb);
        va.add(vb);
        System.out.println(indent + "va + vb = " + va);
        System.out.println();

        // no.21 | 벡터와 스칼라 곱셈
        System.out.println("no.21 | 벡터와 스칼라 곱셈");
        Vector vc = Factory.createVector(3, "2.0");
        Scalar weight = Factory.createScalar("4.0");
        System.out.println(indent + "vc = " + vc);
        System.out.println(indent + "scalar = " + weight);
        vc.multiply(weight);
        System.out.println(indent + "vc * scalar = " + vc);
        System.out.println();

        // no.22 | 행렬 덧셈
        System.out.println("no.22 | 행렬 덧셈");
        Matrix ma = Factory.createMatrix(2, 2, "1.0");
        Matrix mb = Factory.createMatrix(2, 2, "2.0");
        System.out.println(indent + "ma =\n" + ma);
        System.out.println(indent + "mb =\n" + mb);
        ma.add(mb);
        System.out.println(indent + "ma + mb =\n" + ma);
        System.out.println();

        // no.23 | 행렬 곱셈 (오른쪽 곱)
        System.out.println("no.23 | 행렬 곱셈 (오른쪽 곱)");
        Matrix m1 = Factory.createMatrix(2, 3, "1.0");
        Matrix m2 = Factory.createMatrix(3, 2, "2.0");
        System.out.println(indent + "m1 =\n" + m1);
        System.out.println(indent + "m2 =\n" + m2);
        m1.multiplyRight(m2);
        System.out.println(indent + "m1 * m2 =\n" + m1);
        System.out.println();

        // no.24 | 전달받은 두 스칼라의 덧셈이 가능하다.
        System.out.println("no.24 | 전달받은 두 스칼라의 덧셈이 가능하다.");
        s1 = Factory.createScalar("3.0");
        s2 = Factory.createScalar("5.0");
        Scalar sAdd = Tensors.add(s1, s2);
        System.out.println(indent + s1 + " + " + s2 + " = " + sAdd);
        System.out.println();

        // no.25 | 전달받은 두 스칼라의 곱셈이 가능하다.
        System.out.println("no.25 | 전달받은 두 스칼라의 곱셈이 가능하다.");
        s1 = Factory.createScalar("2.0");
        s2 = Factory.createScalar("4.0");
        Scalar sMul = Tensors.multiply(s1, s2);
        System.out.println(indent + s1 + " * " + s2 + " = " + sMul);
        System.out.println();

        // no.26 | 전달받은 두 벡터의 덧셈이 가능하다.
        System.out.println("no.26 | 전달받은 두 벡터의 덧셈이 가능하다.");
        va = Factory.createVector(3, "1.0");
        vb = Factory.createVector(3, "2.0");
        Vector vAdd = Tensors.add(va, vb);
        System.out.println(indent + va + " + " + vb + " = " + vAdd);
        System.out.println();

        // no.27 | 전달받은 스칼라와 벡터의 곱셈이 가능하다.
        System.out.println("no.27 | 전달받은 스칼라와 벡터의 곱셈이 가능하다.");
        Scalar sWeight = Factory.createScalar("3.0");
        Vector vTarget = Factory.createVector(3, "2.0");
        Vector scaledVec = Tensors.multiply(sWeight, vTarget);
        System.out.println(indent + sWeight + " * " + vTarget + " = " + scaledVec);
        System.out.println();

        // no.28 | 전달받은 같은 크기의 두 행렬 덧셈이 가능하다.
        System.out.println("no.28 | 전달받은 같은 크기의 두 행렬 덧셈이 가능하다.");
        Matrix mA = Factory.createMatrix(2, 2, "1.0");
        Matrix mB = Factory.createMatrix(2, 2, "2.0");
        Matrix mAdd = Tensors.add(mA, mB);
        System.out.println(indent + "matrix A =\n" + mA);
        System.out.println(indent + "matrix B =\n" + mB);
        System.out.println(indent + "A + B =\n" + mAdd);
        System.out.println();

        // no.29 | 전달받은 두 행렬의 곱셈이 가능하다. (mxn)x(nxl)
        System.out.println("no.29 | 전달받은 두 행렬의 곱셈이 가능하다.");
        Matrix mA1 = Factory.createMatrixFromArray(new String[][]{
                {"1.0", "2.0", "3.0"},
                {"4.0", "5.0", "6.0"}
        });

        Matrix mB2 = Factory.createMatrixFromArray(new String[][]{
                {"7.0", "8.0"},
                {"9.0", "10.0"},
                {"11.0", "12.0"}
        });

        Matrix mMul = Tensors.multiply(mA1, mB2);
        System.out.println(indent + "matrix A =\n" + mA);
        System.out.println(indent + "matrix B =\n" + mB);
        System.out.println(indent + "A x B =\n" + mMul);
        System.out.println();

        // no.30 | n-차원 벡터 객체는 자신으로부터 nx1 행렬을 생성하여 반환할 수 있다.
        System.out.println("no.30 | n-차원 벡터 객체는 자신으로부터 nx1 행렬을 생성하여 반환할 수 있다.");
        String[] arr = {"1.0", "2.0", "3.0", "4.0", "5.0"};
        Vector vecCol = Factory.createVectorFromArray(arr);
        Matrix colMatrix = vecCol.toColumnMatrix();
        System.out.println(indent + "원본 벡터 = " + vecCol);
        System.out.println(indent + "nx1 행렬 =\n" + colMatrix);
        System.out.println();

        // no.31 | n-차원 벡터 객체는 자신으로부터 1xn 행렬을 생성하여 반환할 수 있다.
        System.out.println("no.31 | n-차원 벡터 객체는 자신으로부터 1xn 행렬을 생성하여 반환할 수 있다.");
        Vector vecRow = Factory.createVectorFromArray(arr);
        Matrix rowMatrix = vecRow.toRowMatrix();
        System.out.println(indent + "원본 벡터 = " + vecRow);
        System.out.println(indent + "1xn 행렬 =\n" + rowMatrix);
        System.out.println();

        // no.32 | 행렬은 다른 행렬과 가로로 합쳐질 수 있다.
        System.out.println("no.32 | 행렬은 다른 행렬과 가로로 합쳐질 수 있다.");
        Matrix mLeft = Factory.createMatrixFromArray(new String[][]{
                {"1.0", "2.0"},
                {"3.0", "4.0"}
        });
        Matrix mRight = Factory.createMatrixFromArray(new String[][]{
                {"5.0"},
                {"6.0"}
        });
        Matrix mConcatCols = Tensors.concatColumns(mLeft, mRight);
        System.out.println(indent + "왼쪽 행렬 =\n" + mLeft);
        System.out.println(indent + "오른쪽 행렬 =\n" + mRight);
        System.out.println(indent + "가로로 합친 결과 =\n" + mConcatCols);
        System.out.println();

        // no.33 | 행렬은 다른 행렬과 세로로 합쳐질 수 있다.
        System.out.println("no.33 | 행렬은 다른 행렬과 세로로 합쳐질 수 있다.");
        Matrix mTop = Factory.createMatrixFromArray(new String[][]{
                {"1.0", "2.0"}
        });
        Matrix mBottom = Factory.createMatrixFromArray(new String[][]{
                {"3.0", "4.0"}
        });
        Matrix mConcatRows = Tensors.concatRows(mTop, mBottom);
        System.out.println(indent + "위쪽 행렬 =\n" + mTop);
        System.out.println(indent + "아래쪽 행렬 =\n" + mBottom);
        System.out.println(indent + "세로로 합친 결과 =\n" + mConcatRows);
        System.out.println();

        // no.34 | 행렬은 특정 행을 벡터 형태로 추출할 수 있다.
        System.out.println("no.34 | 행렬은 특정 행을 벡터 형태로 추출할 수 있다.");
        Matrix mRowSrc = Factory.createMatrixFromArray(new String[][]{
                {"1.0", "2.0", "3.0"},
                {"4.0", "5.0", "6.0"}
        });
        Vector extractedRow = mRowSrc.extractRow(1);
        System.out.println(indent + "행렬 =\n" + mRowSrc);
        System.out.println(indent + "1번 행 추출 결과 = " + extractedRow);
        System.out.println();

        // no.35 | 행렬은 특정 열을 벡터 형태로 추출할 수 있다.
        System.out.println("no.35 | 행렬은 특정 열을 벡터 형태로 추출할 수 있다.");
        Vector extractedCol = mRowSrc.extractColumn(2);
        System.out.println(indent + "행렬 =\n" + mRowSrc);
        System.out.println(indent + "2번 열 추출 결과 = " + extractedCol);
        System.out.println();

        // no.36 | 행렬은 특정 범위의 부분 행렬을 추출할 수 있다.
        System.out.println("no.36 | 행렬은 특정 범위의 부분 행렬을 추출할 수 있다.");
        Matrix mSubSrc = Factory.createMatrixFromArray(new String[][]{
                {"1.0", "2.0", "3.0"},
                {"4.0", "5.0", "6.0"},
                {"7.0", "8.0", "9.0"}
        });
        Matrix subMatrix = mSubSrc.subMatrix(1, 2, 1, 2);  // 2x2 중간 부분
        System.out.println(indent + "원본 행렬 =\n" + mSubSrc);
        System.out.println(indent + "부분 행렬(1~2, 1~2) =\n" + subMatrix);
        System.out.println();

        // no.37 | 행렬은 특정 행과 열을 제외한 minor 행렬을 추출할 수 있다.
        System.out.println("no.37 | 행렬은 특정 행과 열을 제외한 minor 행렬을 추출할 수 있다.");
        Matrix minorMatrix = mSubSrc.minor(1, 1); // 1행 1열 제외
        System.out.println(indent + "원본 행렬 =\n" + mSubSrc);
        System.out.println(indent + "minor(1,1) 결과 =\n" + minorMatrix);
        System.out.println();

        // no.38 | 행렬은 전치 행렬을 구할 수 있다.
        System.out.println("no.38 | 행렬은 전치 행렬을 구할 수 있다.");
        Matrix mTransSrc = Factory.createMatrixFromArray(new String[][]{
                {"1.0", "2.0", "3.0"},
                {"4.0", "5.0", "6.0"}
        });
        Matrix mTrans = mTransSrc.transpose();
        System.out.println(indent + "원본 행렬 =\n" + mTransSrc);
        System.out.println(indent + "전치 행렬 =\n" + mTrans);
        System.out.println();

        // no.39 | 행렬은 대각 요소의 합(Trace)을 구할 수 있다.
        System.out.println("no.39 | 행렬은 대각 요소의 합(Trace)을 구할 수 있다.");
        Matrix mTraceSrc = Factory.createMatrixFromArray(new String[][]{
                {"1.0", "2.0", "3.0"},
                {"4.0", "5.0", "6.0"},
                {"7.0", "8.0", "9.0"}
        });
        Scalar trace = mTraceSrc.trace();
        System.out.println(indent + "행렬 =\n" + mTraceSrc);
        System.out.println(indent + "대각합(Trace) = " + trace.getValue());
        System.out.println();

        // no.40 | 행렬은 자신이 정사각 행렬인지 여부를 판별해 줄 수 있다.
        System.out.println("no.40 | 행렬은 자신이 정사각 행렬인지 여부를 판별해 줄 수 있다.");
        Matrix sq1 = Factory.createMatrixFromArray(new String[][]{
                {"1.0", "2.0", "3.0"},
                {"4.0", "5.0", "6.0"},
                {"7.0", "8.0", "9.0"}
        });
        Matrix sq2 = Factory.createMatrixFromArray(new String[][]{
                {"1.0", "2.0"}
        });
        System.out.println(indent + "sq1 =\n" + sq1);
        System.out.println(indent + "isSquare: " + sq1.isSquare());
        System.out.println(indent + "sq2 =\n" + sq2);
        System.out.println(indent + "isSquare: " + sq2.isSquare());
        System.out.println();

// no.41 | 행렬은 자신이 상삼각 행렬인지 여부를 판별해 줄 수 있다.
        System.out.println("no.41 | 행렬은 자신이 상삼각 행렬인지 여부를 판별해 줄 수 있다.");
        Matrix upper = Factory.createMatrixFromArray(new String[][]{
                {"1.0", "2.0", "3.0"},
                {"0.0", "4.0", "5.0"},
                {"0.0", "0.0", "6.0"}
        });
        Matrix notUpper = Factory.createMatrixFromArray(new String[][]{
                {"1.0", "0.0", "0.0"},
                {"7.0", "1.0", "0.0"},
                {"0.0", "0.0", "1.0"}
        });
        System.out.println(indent + "upper =\n" + upper);
        System.out.println(indent + "isUpperTriangular: " + upper.isUpperTriangular());
        System.out.println(indent + "notUpper =\n" + notUpper);
        System.out.println(indent + "isUpperTriangular: " + notUpper.isUpperTriangular());
        System.out.println();

// no.42 | 행렬은 자신이 하삼각 행렬인지 여부를 판별해 줄 수 있다.
        System.out.println("no.42 | 행렬은 자신이 하삼각 행렬인지 여부를 판별해 줄 수 있다.");
        System.out.println(indent + "upper =\n" + upper);
        System.out.println(indent + "isLowerTriangular: " + upper.isLowerTriangular());
        System.out.println(indent + "notUpper =\n" + notUpper);
        System.out.println(indent + "isLowerTriangular: " + notUpper.isLowerTriangular());
        System.out.println();

// no.43 | 행렬은 자신이 단위 행렬인지 여부를 판별해 줄 수 있다.
        System.out.println("no.43 | 행렬은 자신이 단위 행렬인지 여부를 판별해 줄 수 있다.");
        Matrix identity1 = Factory.createIdentityMatrix(3);
        Matrix notIdentity1 = Factory.createMatrixFromArray(new String[][]{
                {"1.0", "0.0", "0.0"},
                {"0.0", "1.0", "1.0"},
                {"0.0", "0.0", "1.0"}
        });
        System.out.println(indent + "identity =\n" + identity1);
        System.out.println(indent + "isIdentityMatrix: " + identity.isIdentityMatrix());
        System.out.println(indent + "notIdentity =\n" + notIdentity1);
        System.out.println(indent + "isIdentityMatrix: " + notIdentity1.isIdentityMatrix());
        System.out.println();

// no.44 | 행렬은 자신이 영 행렬인지 여부를 판별해 줄 수 있다.
        System.out.println("no.44 | 행렬은 자신이 영 행렬인지 여부를 판별해 줄 수 있다.");
        Matrix zero = Factory.createMatrix(3, 3, "0.0");
        Matrix notZero = Factory.createMatrixFromArray(new String[][]{
                {"0.0", "0.0", "0.0"},
                {"0.0", "5.0", "0.0"},
                {"0.0", "0.0", "0.0"}
        });
        System.out.println(indent + "zero =\n" + zero);
        System.out.println(indent + "isZeroMatrix: " + zero.isZeroMatrix());
        System.out.println(indent + "notZero =\n" + notZero);
        System.out.println(indent + "isZeroMatrix: " + notZero.isZeroMatrix());
        System.out.println();

// no.45 | 행렬은 특정 두 행의 위치를 맞교환할 수 있다.
        System.out.println("no.45 | 행렬은 특정 두 행의 위치를 맞교환할 수 있다.");
        Matrix mSwapRow = Factory.createMatrixFromArray(new String[][]{
                {"1.0", "2.0"},
                {"3.0", "4.0"},
                {"5.0", "6.0"}
        });
        System.out.println(indent + "before swap:\n" + mSwapRow);
        mSwapRow.swapRows(0, 2);
        System.out.println(indent + "after swap row 0 <-> 2:\n" + mSwapRow);
        System.out.println();

// no.46 | 행렬은 특정 두 열의 위치를 맞교환할 수 있다.
        System.out.println("no.46 | 행렬은 특정 두 열의 위치를 맞교환할 수 있다.");
        Matrix mSwapCol = Factory.createMatrixFromArray(new String[][]{
                {"1.0", "2.0", "3.0"},
                {"4.0", "5.0", "6.0"}
        });
        System.out.println(indent + "before swap:\n" + mSwapCol);
        mSwapCol.swapColumns(0, 2);
        System.out.println(indent + "after swap column 0 <-> 2:\n" + mSwapCol);
        System.out.println();

// no.47 | 행렬은 특정 행에 스칼라배 할 수 있다.
        System.out.println("no.47 | 행렬은 특정 행에 스칼라배 할 수 있다.");
        Matrix mScaleRow = Factory.createMatrixFromArray(new String[][]{
                {"1.0", "2.0"},
                {"3.0", "4.0"}
        });
        Scalar rowFactor = Factory.createScalar("-2.0");
        System.out.println(indent + "before scaling:\n" + mScaleRow);
        mScaleRow.scaleRow(1, rowFactor);
        System.out.println(indent + "after scale row 1 by -2:\n" + mScaleRow);
        System.out.println();

// no.48 | 행렬은 특정 열에 스칼라배 할 수 있다.
        System.out.println("no.48 | 행렬은 특정 열에 스칼라배 할 수 있다.");
        Matrix mScaleCol = Factory.createMatrixFromArray(new String[][]{
                {"1.0", "2.0"},
                {"3.0", "4.0"}
        });
        Scalar colFactor = Factory.createScalar("3.0");
        System.out.println(indent + "before scaling:\n" + mScaleCol);
        mScaleCol.scaleColumn(0, colFactor);
        System.out.println(indent + "after scale column 0 by 3:\n" + mScaleCol);
        System.out.println();

// no.49 | 행렬은 특정 행에 다른 행의 상수배를 더할 수 있다.
        System.out.println("no.49 | 행렬은 특정 행에 다른 행의 상수배를 더할 수 있다.");
        Matrix mAddRow = Factory.createMatrixFromArray(new String[][]{
                {"1.0", "2.0"},
                {"3.0", "4.0"}
        });
        Scalar addRowFactor = Factory.createScalar("0.5");
        System.out.println(indent + "before operation:\n" + mAddRow);
        mAddRow.addScaledRow(0, 1, addRowFactor);
        System.out.println(indent + "after R0 = R0 + 0.5 * R1:\n" + mAddRow);
        System.out.println();

// no.50 | 행렬은 특정 열에 다른 열의 상수배를 더할 수 있다.
        System.out.println("no.50 | 행렬은 특정 열에 다른 열의 상수배를 더할 수 있다.");
        Matrix mAddCol = Factory.createMatrixFromArray(new String[][]{
                {"1.0", "2.0"},
                {"3.0", "4.0"}
        });
        Scalar addColFactor = Factory.createScalar("-1.0");
        System.out.println(indent + "before operation:\n" + mAddCol);
        mAddCol.addScaledColumn(0, 1, addColFactor);
        System.out.println(indent + "after C0 = C0 + (-1.0) * C1:\n" + mAddCol);
        System.out.println();

        // no.51 | 행렬은 자신으로부터 RREF 행렬을 구해서 반환해줄 수 있다.
        System.out.println("no.51 | 행렬은 자신으로부터 RREF 행렬을 구해서 반환해줄 수 있다.");
        Matrix rrefInput = Factory.createMatrixFromArray(new String[][]{
                {"2.0", "0.0", "1.0"},
                {"3.0", "4.0", "5.0"},
                {"1.0", "0.0", "1.0"}
        });
        System.out.println(indent + "원본 행렬 =\n" + rrefInput);
        Matrix rrefResult = rrefInput.toRref();
        System.out.println(indent + "RREF 결과 =\n" + rrefResult);
        System.out.println();

// no.52 | 행렬은 자신이 RREF 행렬인지 여부를 판별해 줄 수 있다.
        System.out.println("no.52 | 행렬은 자신이 RREF 행렬인지 여부를 판별해 줄 수 있다.");
        Matrix rrefMatrix = Factory.createMatrixFromArray(new String[][]{
                {"1.0", "0.0", "2.0"},
                {"0.0", "1.0", "3.0"},
                {"0.0", "0.0", "0.0"}
        });
        Matrix notRrefMatrix = Factory.createMatrixFromArray(new String[][]{
                {"0.0", "1.0", "0.0"},
                {"1.0", "0.0", "0.0"},
                {"0.0", "0.0", "1.0"}
        });
        System.out.println(indent + "RREF 형태의 행렬 =\n" + rrefMatrix);
        System.out.println(indent + "isRref: " + rrefMatrix.isRref());
        System.out.println(indent + "RREF 아님 =\n" + notRrefMatrix);
        System.out.println(indent + "isRref: " + notRrefMatrix.isRref());
        System.out.println();

// no.53 | 행렬은 자신의 행렬식을 구해줄 수 있다.
        System.out.println("no.53 | 행렬은 자신의 행렬식을 구해줄 수 있다.");
        Matrix detMat1 = Factory.createMatrixFromArray(new String[][]{
                {"1.0", "2.0"},
                {"3.0", "4.0"}
        });
        Matrix detMat2 = Factory.createMatrixFromArray(new String[][]{
                {"1.0", "2.0", "3.0"},
                {"0.0", "1.0", "4.0"},
                {"5.0", "6.0", "0.0"}
        });
        System.out.println(indent + "행렬 A =\n" + detMat1);
        System.out.println(indent + "행렬식 det(A) = " + detMat1.determinant());
        System.out.println(indent + "행렬 B =\n" + detMat2);
        System.out.println(indent + "행렬식 det(B) = " + detMat2.determinant());
        System.out.println();

// no.54 | 행렬은 자신의 역행렬을 구해줄 수 있다.
        System.out.println("no.54 | 행렬은 자신의 역행렬을 구해줄 수 있다.");
        Matrix invMat1 = Factory.createMatrixFromArray(new String[][]{
                {"2.0", "1.0", "3.0"},
                {"1.0", "0.0", "1.0"},
                {"0.0", "1.0", "2.0"}
        });
        Matrix invMat2 = Factory.createMatrixFromArray(new String[][]{
                {"1.0", "1.0", "1.0"},
                {"2.0", "2.0", "2.0"},
                {"3.0", "4.0", "5.0"}
        });
        System.out.println(indent + "역행렬 가능한 행렬 =\n" + invMat1);
        try {
            Matrix inv1 = invMat1.inverse();
            System.out.println(indent + "역행렬 결과 =\n" + inv1);
        } catch (NonSquareMatrixException | TensorArithmeticException e) {
            System.out.println(indent + "[예외] 역행렬 계산 실패: " + e.getMessage());
        }
        System.out.println();

        System.out.println(indent + "역행렬 불가능한 행렬 =\n" + invMat2);
        try {
            Matrix inv2 = invMat2.inverse();
            System.out.println(indent + "역행렬 결과 =\n" + inv2);
        } catch (NonSquareMatrixException | TensorArithmeticException e) {
            System.out.println(indent + "[예외] 역행렬 계산 실패: " + e.getMessage());
        }
        System.out.println();
    }
}