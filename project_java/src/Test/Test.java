package Test;

import tensor.*;

public class Test {
    static String indent = "\t\t";

    public static void main(String[] args) {
        // 01. 고정값 Scalar 생성
        Scalar scalar1 = Factory.createScalar("3.14");
        System.out.println("01 | Scalar 생성: " + scalar1);

        // 02. 랜덤 Scalar 생성
        Scalar scalar2 = Factory.createRandomScalar("-1.0", "1.0");
        System.out.println("02 | Random Scalar 생성: " + scalar2);

        // 03. 고정값 Vector 생성
        Vector vector1 = Factory.createVector(3, "1.0");
        System.out.println("03 | Vector 생성: " + vector1);

        // 04. 랜덤값 Vector 생성
        Vector vector2 = Factory.createRandomVector(3, "0.0", "5.0");
        System.out.println("04 | Random Vector: " + vector2);

        // 05. 배열로 Vector 생성
        String[] vecArray = {"1.0", "2.0", "3.0"};
        Vector vector3 = Factory.createVectorFromArray(vecArray);
        System.out.println("05 | Array to Vector: " + vector3);

        // 06. 고정값 Matrix 생성
        Matrix matrix1 = Factory.createMatrix(2, 3, "1.0");
        System.out.println("06 | Matrix 생성: \n" + matrix1);

        // 07. 랜덤값 Matrix 생성
        Matrix matrix2 = Factory.createRandomMatrix(2, 3, "0.0", "1.0");
        System.out.println("07 | Random Matrix: \n" + matrix2);

        // 08. CSV로 Matrix 생성 (경로는 사용자에 맞게 수정 필요)
        try {
            Matrix csvMatrix = Factory.createMatrixFromCsv("C:/Users/yourname/Desktop/3by3matrix.csv");
            System.out.println("08 | CSV Matrix: \n" + csvMatrix);
        } catch (Exception e) {
            System.out.println("08 | CSV 읽기 실패: " + e.getMessage());
        }

        // 09. 배열로 Matrix 생성
        String[][] matArray = {{"1.0", "2.0"}, {"3.0", "4.0"}};
        Matrix matrix3 = Factory.createMatrixFromArray(matArray);
        System.out.println("09 | Array to Matrix: \n" + matrix3);

        // 10. 단위행렬 생성
        Matrix identity = Factory.createIdentityMatrix(3);
        System.out.println("10 | Identity Matrix: \n" + identity);

        // 이후 테스트 항목 (11~54)도 위 패턴대로 반복 구현하면 됩니다.
        // 11. get/set 요소
        System.out.println("11 | matrix3[0][1] = " + matrix3.getMatrixElement(0, 1));
        matrix3.setMatrixElement(0, 1, Factory.createScalar("10.0"));
        System.out.println("11 | 변경 후 matrix3: \n" + matrix3);

        // 12. Scalar get/set
        System.out.println("12 | scalar1 값: " + scalar1.getValue());
        scalar1.setValue("2.71");
        System.out.println("12 | 변경 후 scalar1: " + scalar1);

        // 13. 벡터와 행렬 크기 정보 확인
        Vector vector = Factory.createVector(5, "1.0");
        Matrix matrix = Factory.createMatrix(3, 4, "0.5");
        System.out.println("no.13v | 벡터의 크기 정보를 조회할 수 있다.");
        System.out.println(indent + "vector size: " + vector.getVectorSize());
        System.out.println();

        System.out.println("no.13m | 행렬의 크기 정보를 조회할 수 있다.");
        System.out.println(indent + "matrix rows: " + matrix.getMatrixRowCount());
        System.out.println(indent + "matrix columns: " + matrix.getMatrixColumnCount());
        System.out.println();

        // 14. toString
        System.out.println("no.14s | 스칼라 toString 테스트");
        Scalar scalar = Factory.createScalar("123.456789");
        System.out.println(indent + scalar);
        System.out.println();

        System.out.println("no.14v | 벡터 toString 테스트");
        System.out.println(indent + vector);
        System.out.println();

        System.out.println("no.14m | 행렬 toString 테스트");
        System.out.println(indent + matrix);
        System.out.println();

        // 15. equals
        System.out.println("no.15s | 두 스칼라 비교");
        Scalar scalar2 = Factory.createScalar("123.456789");
        System.out.println(indent + "scalar1 equals scalar2: " + scalar.equals(scalar2));
        System.out.println();

        System.out.println("no.15v | 두 벡터 비교");
        Vector vector2 = Factory.createVector(5, "1.0");
        System.out.println(indent + "vector1 equals vector2: " + vector.equals(vector2));
        System.out.println();

        System.out.println("no.15m | 두 행렬 비교");
        Matrix matrix2 = Factory.createMatrix(3, 4, "0.5");
        System.out.println(indent + "matrix1 equals matrix2: " + matrix.equals(matrix2));
        System.out.println();

        // 16. compareTo
        System.out.println("no.16 | 스칼라 대소 비교");
        Scalar bigger = Factory.createScalar("200.0");
        System.out.println(indent + "scalar1.compareTo(bigger): " + scalar.compareTo(bigger));
        System.out.println(indent + "bigger.compareTo(scalar): " + bigger.compareTo(scalar));
        System.out.println();

        // 17. clone
        System.out.println("no.17s | 스칼라 복제");
        Scalar cloneS = scalar.clone();
        System.out.println(indent + "original: " + scalar + ", cloned: " + cloneS);
        System.out.println();

        System.out.println("no.17v | 벡터 복제");
        Vector cloneV = vector.clone();
        System.out.println(indent + "original: " + vector + ", cloned: " + cloneV);
        System.out.println();

        System.out.println("no.17m | 행렬 복제");
        Matrix cloneM = matrix.clone();
        System.out.println(indent + "original: \n" + matrix + indent + "cloned: \n" + cloneM);
        System.out.println();

        // 18~19. 스칼라 연산
        System.out.println("no.18 | 스칼라 덧셈");
        Scalar addResult = scalar.clone();
        addResult.add(scalar2);
        System.out.println(indent + "result: " + addResult);
        System.out.println();

        System.out.println("no.19 | 스칼라 곱셈");
        Scalar mulResult = scalar.clone();
        mulResult.multiply(scalar2);
        System.out.println(indent + "result: " + mulResult);
        System.out.println();

        // 20~21. 벡터 연산
        System.out.println("no.20 | 벡터 덧셈");
        Vector v1 = Factory.createVector(3, "1.0");
        Vector v2 = Factory.createVector(3, "2.0");
        v1.add(v2);
        System.out.println(indent + "v1 + v2 = " + v1);
        System.out.println();

        System.out.println("no.21 | 벡터 스칼라 곱");
        Scalar scale = Factory.createScalar("2.0");
        v1.multiply(scale);
        System.out.println(indent + "v1 * 2 = " + v1);
        System.out.println();

        // 22~23. 행렬 연산
        System.out.println("no.22 | 행렬 덧셈");
        Matrix m1 = Factory.createMatrix(2, 2, "1.0");
        Matrix m2 = Factory.createMatrix(2, 2, "2.0");
        m1.add(m2);
        System.out.println(indent + "m1 + m2 = \n" + m1);
        System.out.println();

        System.out.println("no.23 | 행렬 곱셈");
        Matrix m3 = Factory.createMatrix(2, 3, "1.0");
        Matrix m4 = Factory.createMatrix(3, 2, "1.0");
        m3.multiply(m4);
        System.out.println(indent + "m3 * m4 = \n" + m3);
        System.out.println();

        // 30~31. 벡터의 행렬 변환
        System.out.println("no.30 | 벡터 -> column matrix");
        Vector vCol = Factory.createVector(3, "1.0");
        System.out.println(indent + vCol.toColumnMatrix());
        System.out.println();

        System.out.println("no.31 | 벡터 -> row matrix");
        System.out.println(indent + vCol.toRowMatrix());
    }
}

