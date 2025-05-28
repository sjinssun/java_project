package tensor;

import java.math.BigDecimal;

class ScalarImpl implements Scalar {
    private BigDecimal value;

    // 01. 고정 값 생성자
    ScalarImpl(String value) {
        try {
            this.value = new BigDecimal(value);
        } catch (NumberFormatException e) {
            throw new TensorInvalidInputException(value);
        }
    }
    // 02. 랜덤 범위 생성자
    ScalarImpl(String min, String max) {
        try {
            BigDecimal minVal = new BigDecimal(min);
            BigDecimal maxVal = new BigDecimal(max);
            if (minVal.compareTo(maxVal) > 0) {
                throw new TensorInvalidInputException();
            }
            BigDecimal range = maxVal.subtract(minVal);
            BigDecimal rand = minVal.add(range.multiply(BigDecimal.valueOf(Math.random())));
            this.value = rand;
        } catch (NumberFormatException e) {
            throw new TensorInvalidInputException();
        }
    }

    @Override
    public String getValue() {
        return value.stripTrailingZeros().toPlainString();
    }

    @Override
    public void setValue(String value) {
        try {
            this.value = new BigDecimal(value);
        } catch (NumberFormatException e) {
            throw new TensorInvalidInputException();
        }
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
        if (obj instanceof Scalar s) {
            return getValue().equals(s.getValue()); // 단순 문자열 비교
        } else {
            return false;
        }
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
