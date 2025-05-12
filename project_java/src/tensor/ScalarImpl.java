package tensor;

import java.math.BigDecimal;

class ScalarImpl implements Scalar {
    private BigDecimal value;

    // 01. 고정 값 생성자
    ScalarImpl(String value) {
        if (!isValidNumber(value)) {
            throw new TensorInvalidInputException("Invalid scalar input: " + value);
        }
        this.value = new BigDecimal(value);
    }

    // 02. 랜덤 범위 생성자
    ScalarImpl(String min, String max) {
        if (!isValidNumber(min) || !isValidNumber(max)) {
            throw new TensorInvalidInputException("Invalid scalar range: " + min + ", " + max);
        }
        BigDecimal minVal = new BigDecimal(min);
        BigDecimal maxVal = new BigDecimal(max);
        BigDecimal range = maxVal.subtract(minVal);
        BigDecimal rand = minVal.add(range.multiply(BigDecimal.valueOf(Math.random())));
        this.value = rand;
    }

    @Override
    public String getValue() {
        return value.stripTrailingZeros().toPlainString();
    }

    @Override
    public void setValue(String value) {
        if (!isValidNumber(value)) {
            throw new TensorInvalidInputException("Invalid scalar input: " + value);
        }
        this.value = new BigDecimal(value);
    }

    // 22. 비정적 add
    @Override
    public void add(Scalar other) {
        this.value = this.value.add(new BigDecimal(other.getValue()));
    }

    // 23. 비정적 multiply
    @Override
    public void multiply(Scalar other) {
        this.value = this.value.multiply(new BigDecimal(other.getValue()));
    }

    // 26. 정적 add
    public static Scalar add(Scalar a, Scalar b) {
        BigDecimal result = new BigDecimal(a.getValue()).add(new BigDecimal(b.getValue()));
        return Factory.createScalar(result.toPlainString());
    }

    // 27. 정적 multiply
    public static Scalar multiply(Scalar a, Scalar b) {
        BigDecimal result = new BigDecimal(a.getValue()).multiply(new BigDecimal(b.getValue()));
        return Factory.createScalar(result.toPlainString());
    }

    @Override
    public Scalar clone() {
        return new ScalarImpl(this.value.toPlainString());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ScalarImpl)) return false;
        ScalarImpl other = (ScalarImpl) obj;
        return this.value.compareTo(other.value) == 0;
    }

    @Override
    public int compareTo(Scalar other) {
        return this.value.compareTo(new BigDecimal(other.getValue()));
    }

    @Override
    public String toString() {
        return value.stripTrailingZeros().toPlainString();
    }

    private boolean isValidNumber(String val) {
        try {
            new BigDecimal(val);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
