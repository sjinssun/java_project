package tensor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class VectorImpl implements Vector {
    private final List<Scalar> elements;

    // 03. 고정값 생성자
    VectorImpl(int dimension, String value) {
        elements = new ArrayList<>();
        for (int i = 0; i < dimension; i++) {
            elements.add(Factory.createScalar(value));
        }
    }

    // 04. 랜덤값 생성자
    VectorImpl(int dimension, String min, String max) {
        elements = new ArrayList<>();
        for (int i = 0; i < dimension; i++) {
            elements.add(Factory.createRandomScalar(min, max));
        }
    }

    // 05. 배열 생성자
    VectorImpl(String[] values) {
        elements = new ArrayList<>();
        for (String value : values) {
            elements.add(Factory.createScalar(value));
        }
    }

    @Override
    public Scalar getVectorElement(int index) {
        if (index < 0 || index >= elements.size()) {
            throw new TensorInvalidIndexException ("Invalid index: " + index);
        }
        return elements.get(index);
    }

    @Override
    public void setVectorElement(int index, Scalar value) {
        if (index < 0 || index >= elements.size()) {
            throw new InvalidVectorAccessException("Invalid index: " + index);
        }
        elements.set(index, value);
    }

    @Override
    public int getVectorSize() {
        return elements.size();
    }

    @Override
    public Vector clone() {
        String[] copy = new String[elements.size()];
        for (int i = 0; i < elements.size(); i++) {
            copy[i] = elements.get(i).getValue();
        }
        return new VectorImpl(copy);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vector)) return false;
        Vector other = (Vector) obj;
        if (this.getVectorSize() != other.getVectorSize()) return false;
        for (int i = 0; i < this.getVectorSize(); i++) {
            if (!this.getVectorElement(i).equals(other.getVectorElement(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < elements.size(); i++) {
            sb.append(elements.get(i).getValue());
            if (i < elements.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public void add(Vector other) {
        if (this.getVectorSize() != other.getVectorSize()) {
            throw new TensorSizeMismatchException("Vector sizes do not match.");
        }
        for (int i = 0; i < elements.size(); i++) {
            elements.get(i).add(other.getVectorElement(i));
        }
    }

    @Override
    public void multiply(Scalar scalar) {
        for (Scalar s : elements) {
            s.multiply(scalar);
        }
    }

    @Override
    public Matrix toColumnMatrix() {
        int rows = elements.size();
        String[][] values = new String[rows][1];
        for (int i = 0; i < rows; i++) {
            values[i][0] = elements.get(i).getValue();
        }
        return Factory.createMatrixFromArray(values);
    }

    @Override
    public Matrix toRowMatrix() {
        int cols = elements.size();
        String[][] values = new String[1][cols];
        for (int i = 0; i < cols; i++) {
            values[0][i] = elements.get(i).getValue();
        }
        return Factory.createMatrixFromArray(values);
    }

    // 26. static add(Vector a, Vector b)
    public static Vector add(Vector a, Vector b) {
        if (a.getVectorSize() != b.getVectorSize()) {
            throw new TensorSizeMismatchException("Vector sizes do not match.");
        }
        Vector result = a.clone();
        result.add(b);
        return result;
    }

    // 27. static multiply(Scalar, Vector)
    public static Vector multiply(Scalar scalar, Vector vector) {
        Vector result = vector.clone();
        result.multiply(scalar);
        return result;
    }


    @Override
    public int compareTo(Vector o) {
        return 0;
    }
}