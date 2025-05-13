package tensor;

public interface Vector extends Cloneable {

    // 11. 요소 조회 및 설정
    Scalar getVectorElement(int index);
    void setVectorElement(int index, String value);

    // 13. 크기 정보
    int getVectorSize();

    // 17. 깊은 복사
    Vector clone();

    // 14. 문자열 출력
    @Override
    String toString();
    String toString(boolean rounding);

    // 15. 동등성 비교
    @Override
    boolean equals(Object obj);

    // 22. 비정적 연산
    void multiply(Scalar scalar);
    void add(Vector b);

    // 31~32. 변환
    Matrix toColumnMatrix(); // <-- 추가
    Matrix toRowMatrix();    // <-- 추가
}