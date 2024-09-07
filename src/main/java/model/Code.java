package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The Code class represents a set of three numbers.
 */
public class Code {
    private int nb1;
    private int nb2;
    private int nb3;

    /**
     * Constructs a Code object with three numbers.
     *
     * @param num1 The first number.
     * @param num2 The second number.
     * @param num3 The third number.
     */
    public Code(int num1, int num2, int num3) {
        this.nb1 = num1;
        this.nb2 = num2;
        this.nb3 = num3;
    }

    /**
     * Constructs a Code object from an existing Code object.
     *
     * @param code The Code object to copy.
     */
    public Code(Code code) {
        this.nb1 = code.nb1;
        this.nb2 = code.nb2;
        this.nb3 = code.nb3;
    }

    /**
     * Gets the number at the specified index in the Code object.
     *
     * @param index The index of the number to retrieve (0, 1, or 2).
     * @return The number at the specified index.
     */
    public int getNumber(int index) {
        switch (index) {
            case 0:
                return nb1;
            case 1:
                return nb2;
            case 2:
                return nb3;
            default:
                return 0;
        }
    }

    /**
     * Gets a list containing all three numbers in the Code object.
     *
     * @return A list of the numbers in the Code object.
     */
    public List<Integer> getListNb() {
        List<Integer> numberList = new ArrayList<>();
        numberList.add(nb1);
        numberList.add(nb2);
        numberList.add(nb3);
        return numberList;
    }

    /**
     * Gets the first number in the Code object.
     *
     * @return The first number.
     */
    public int getNb1() {
        return nb1;
    }

    /**
     * Gets the second number in the Code object.
     *
     * @return The second number.
     */
    public int getNb2() {
        return nb2;
    }

    /**
     * Gets the third number in the Code object.
     *
     * @return The third number.
     */
    public int getNb3() {
        return nb3;
    }

    @Override
    public String toString() {
        return "" + nb1 + nb2 + nb3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Code code = (Code) o;
        return nb1 == code.nb1 && nb2 == code.nb2 && nb3 == code.nb3;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nb1, nb2, nb3);
    }
}
