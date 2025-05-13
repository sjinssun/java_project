package Test;

import tensor.*;

public class Test {
    static String indent = "\t\t";

    public static void main(String[] args) {
        // no.01 | 값(String)을 지정하여 Scalar 생성
        Scalar scalar1 = Factory.createScalar("3.1415");
        System.out.println("no.01 | Scalar 생성: " + indent + scalar1.getValue());

        // no.02 | 랜덤 범위 내 Scalar 생성
        Scalar scalar2 = Factory.createRandomScalar("-10.0", "10.0");
        System.out.println("no.02 | 랜덤 Scalar 생성: " + indent + scalar2.getValue());

        // no.03 | 고정값 Vector 생성
        Vector vector1 = Factory.createVector(3, "1.0");
        System.out.println("no.03 | 고정값 Vector 생성: " + indent + vector1.toString());

        // no.04 | 랜덤 Vector 생성
        Vector vector2 = Factory.createRandomVector(3, "-5.0", "5.0");
        System.out.println("no.04 | 랜덤 Vector 생성: " + indent + vector2.toString());

        // no.05 | 배열 기반 Vector 생성
        Vector vector3 = Factory.createVectorFromArray(new String[]{"1.0", "2.0", "3.0"});
        System.out.println("no.05 | 배열 기반 Vector 생성: " + indent + vector3.toString());

        // no.06 | 고정값 Matrix 생성
        Matrix matrix1 = Factory.createMatrix(2, 2, "4.0");
        System.out.println("no.06 | 고정값 Matrix 생성:\n" + indent + matrix1.toString());

        // no.07 | 랜덤 Matrix 생성
        Matrix matrix2 = Factory.createRandomMatrix(2, 2, "0.0", "1.0");
        System.out.println("no.07 | 랜덤 Matrix 생성:\n" + indent + matrix2.toString());

        // no.08 | CSV 기반 Matrix 생성
        try {
            Matrix matrix3 = Factory.createMatrixFromCsv("matrix.csv");
            System.out.println("no.08 | CSV 기반 Matrix 생성:\n" + indent + matrix3.toString());
        } catch (TensorFileNotFoundException e) {
            System.out.println("no.08 | CSV 로딩 실패: " + indent + e.getMessage());
        }

        // no.09 | 2차원 배열 기반 Matrix 생성
        Matrix matrix4 = Factory.createMatrixFromArray(new String[][]{
                {"1.0", "2.0"},
                {"3.0", "4.0"}
        });
        System.out.println("no.09 | 배열 기반 Matrix 생성:\n" + indent + matrix4.toString());

        // no.10 | 단위행렬 생성
        Matrix identity = Factory.createIdentityMatrix(3);
        System.out.println("no.10 | 단위행렬 생성:\n" + indent + identity.toString());

        // no.11 | Matrix 요소 접근 및 설정
        Scalar element = matrix4.getMatrixElement(0, 1);
        System.out.println("no.11 | Matrix 요소 접근 (0,1): " + indent + element.getValue());
        matrix4.setMatrixElement(0, 1, Factory.createScalar("9.9"));
        System.out.println(indent + "수정 후 (0,1): " + matrix4.getMatrixElement(0, 1).getValue());

        // no.12 | Vector 요소 접근 및 설정
        Scalar velement = vector3.getVectorElement(2);
        System.out.println("no.12 | Vector 요소 접근 (2): " + indent + velement.getValue());
        vector3.setVectorElement(2, "7.7");
        System.out.println(indent + "수정 후 (2): " + vector3.getVectorElement(2).getValue());

        // no.13 | Matrix 행 수 확인
        System.out.println("no.13 | Matrix 행 수: " + indent + matrix4.getMatrixRowCount());

        // no.14 | Matrix 열 수 확인
        System.out.println("no.14 | Matrix 열 수: " + indent + matrix4.getMatrixColumnCount());

        // no.15 | Vector 크기 확인
        System.out.println("no.15 | Vector 크기: " + indent + vector3.getVectorSize());

        // no.16 | Scalar 값 설정 및 확인
        scalar1.setValue("6.28");
        System.out.println("no.16 | Scalar 값 변경 후: " + indent + scalar1.getValue());

        // no.17 | Matrix toString
        System.out.println("no.17 | Matrix toString:\n" + indent + matrix4.toString());

        // no.18 | Matrix toString(boolean)
        System.out.println("no.18 | Matrix toString(true):\n" + indent + matrix4.toString(true));

        // no.19 | equals 테스트
        Matrix matrix5 = Factory.createMatrixFromArray(new String[][]{
                {"1.0", "9.9"},
                {"3.0", "4.0"}
        });
        System.out.println("no.19 | Matrix equals 결과: " + indent + matrix4.equals(matrix5));

        // no.20 | clone 테스트
        Matrix cloned = matrix4.clone();
        System.out.println("no.20 | Matrix clone:\n" + indent + cloned.toString());

        // no.21 | 비정적 Matrix add
        matrix1.add(matrix5);
        System.out.println("no.21 | Matrix add (비정적):\n" + indent + matrix1.toString());

        // no.22 | 비정적 Matrix multiply
        matrix1.multiply(matrix5);
        System.out.println("no.22 | Matrix multiply (비정적):\n" + indent + matrix1.toString());

        // no.23 | 정적 Matrix add
        Matrix resultAdd = Matrix.add(matrix1, matrix5);
        System.out.println("no.23 | Matrix add (정적):\n" + indent + resultAdd.toString());

        // no.24 | 정적 Matrix multiply
        Matrix resultMul = Matrix.multiply(matrix1, matrix5);
        System.out.println("no.24 | Matrix multiply (정적):\n" + indent + resultMul.toString());

        // no.25 | Vector add
        vector1.add(vector3);
        System.out.println("no.25 | Vector add: " + indent + vector1.toString());

        // no.26 | Vector 정적 덧셈
        Vector vA = Factory.createVectorFromArray(new String[]{"1.0", "2.0", "3.0"});
        Vector vB = Factory.createVectorFromArray(new String[]{"4.0", "5.0", "6.0"});
        Vector vAdd = Vector.add(vA, vB);
        System.out.println("no.26 | Vector 정적 add 결과: " + indent + vAdd.toString());

        // no.27 | 전달받은 두 스칼라와 벡터의 곱셈이 가능하다.
        Scalar scalar = Factory.createScalar("-1.0");
        Vector vector = Factory.createVector(3, "2.0");

        System.out.println("no.27 | Scalar x Vector 곱셈 가능 여부 확인");
        System.out.println(indent + "vector =\t\t" + vector.toString());
        System.out.println(indent + "scalar =\t\t" + scalar.getValue());
        System.out.println(indent + "scalar x vector =\t" + Tensors.multiply(scalar, vector).toString());


        // no.28 | Matrix 정적 덧셈
        Matrix mA = Factory.createMatrixFromArray(new String[][]{
                {"1.0", "2.0"},
                {"3.0", "4.0"}
        });
        Matrix mB = Factory.createMatrixFromArray(new String[][]{
                {"5.0", "6.0"},
                {"7.0", "8.0"}
        });
        Matrix mAdd = Matrix.add(mA, mB);
        System.out.println("no.28 | Matrix 정적 add 결과:\n" + indent + mAdd.toString());

        // no.29 | Matrix 정적 곱셈
        Matrix mX = Factory.createMatrixFromArray(new String[][]{
                {"1.0", "2.0", "3.0"},
                {"4.0", "5.0", "6.0"}
        });
        Matrix mY = Factory.createMatrixFromArray(new String[][]{
                {"7.0", "8.0"},
                {"9.0", "10.0"},
                {"11.0", "12.0"}
        });
        Matrix mMul = Matrix.multiply(mX, mY);
        System.out.println("no.29 | Matrix 정적 multiply 결과:\n" + indent + mMul.toString());

    }
}