package tensor;

import java.math.BigDecimal;
import java.util.Random;

public class Factory {

    // 01. 스칼라: 지정한 값으로 생성
    public static Scalar createScalar(String value) {
        return new ScalarImpl(value);
    }

    // 02. 스칼라: 랜덤 생성
    public static Scalar createRandomScalar(String min, String max) {
        return new ScalarImpl(min, max);
    }

    // 03. 벡터: 고정값으로 생성
    public static Vector createVector(int dimension, String value) {
        return new VectorImpl(dimension, value);
    }

    // 04. 벡터: 랜덤값으로 생성 랜덤값은 impl에서 구현
    public static Vector createRandomVector(int dimension, String min, String max) {
        return new VectorImpl(dimension, min, max);
    }

    // 05. 벡터: 배열로 생성
    public static Vector createVectorFromArray(String[] values) {
        return new VectorImpl(values);
    }

    // 06. 행렬: 고정값으로 생성
    public static Matrix createMatrix(int rows, int cols, String value) {
        return new MatrixImpl(rows, cols, value);
    }

    // 07. 행렬: 랜덤값으로 생성 랜덤값은 impl에서 구현
    public static Matrix createRandomMatrix(int rows, int cols, String min, String max) {
        return new MatrixImpl(rows, cols, min, max);
    }

    // 08. 행렬: CSV 파일로부터 생성
    public static Matrix createMatrixFromCsv(String filepath) {
        return new MatrixImpl(filepath);
    }

    // 09. 행렬: 2차원 배열로 생성
    public static Matrix createMatrixFromArray(String[][] values) {
        return new MatrixImpl(values);
    }

    // 10. 단위 행렬 생성
    public static Matrix createIdentityMatrix(int size) {
        return new MatrixImpl(size);
    }
}
