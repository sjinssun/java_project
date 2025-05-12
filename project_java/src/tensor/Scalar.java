package tensor;
import java.math.BigDecimal;

public interface Scalar extends Comparable<Scalar>, Cloneable {
    // 값 접근 및 설정
    String getValue();
    void setValue(String value);   // 스칼라 값 설정

    // 연산 (non-static)
    void add(Scalar other);        // this += other
    void multiply(Scalar other);   // this *= other

    // 복제 및 비교
    Scalar clone();
    @Override boolean equals(Object obj);
    @Override String toString();
    @Override int compareTo(Scalar other);
}
