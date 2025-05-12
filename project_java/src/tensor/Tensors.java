package tensor;

/**
 * Tensors.java
 * Static utility class wrapping only the default/static operations for Scalar, Vector, and Matrix (24–29).
 */
public class Tensors {

    // 24. 전달받은 두 스칼라의 덧셈
    public static Scalar add(Scalar a, Scalar b) {
        return ScalarImpl.add(a, b);
    }

    // 25. 전달받은 두 스칼라의 곱셈
    public static Scalar multiply(Scalar a, Scalar b) {
        return ScalarImpl.multiply(a, b);
    }

    // 26. 전달받은 두 벡터의 덧셈
    public static Vector add(Vector a, Vector b) {
        return VectorImpl.add(a, b);
    }

    // 27. 전달받은 스칼라와 벡터의 곱셈
    public static Vector multiply(Scalar scalar, Vector vector) {
        return VectorImpl.multiply(scalar, vector);
    }

    // 28. 전달받은 두 행렬의 덧셈
    public static Matrix add(Matrix a, Matrix b) {
        return Matrix.add(a, b); // MatrixImpl에서 내부 구현 위임
    }

    // 29. 전달받은 두 행렬의 곱셈
    public static Matrix multiply(Matrix a, Matrix b) {
        return Matrix.multiply(a, b); // MatrixImpl에서 내부 구현 위임
    }

    // 32. 전달받은 두 행렬의 가로 연결 (concat columns)
    public static Matrix concatColumns(Matrix a, Matrix b) {
        return MatrixImpl.concatColumns(a, b);
    }

    // 33. 전달받은 두 행렬의 세로 연결 (concat rows)
    public static Matrix concatRows(Matrix a, Matrix b) {
        return MatrixImpl.concatRows(a, b);
    }
}