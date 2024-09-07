package model.Validator;

import model.Code;

/**
 * The CompareSumTwoNumber class represents a Validator that compares the sum of two numbers from the code with a specified value.
 * Extends Validator class.
 */
public class CompareSumTwoNumber extends Validator {

    private int value;
    private int first;
    private int second;

    /**
     * Constructs a CompareSumTwoNumber Validator with the secret code, first number index, second number index, and comparison value.
     *
     * @param secretCode The secret code against which the validation will be performed.
     * @param first      The index of the first number in the code.
     * @param second     The index of the second number in the code.
     * @param value      The value to compare the sum of the two numbers with.
     */
    public CompareSumTwoNumber(Code secretCode, int first, int second, int value) {
        super(secretCode);
        this.first = first;
        this.second = second;
        this.value = value;

        String prem = getName(first);
        String sec = getName(second);
        this.description = "Compare la somme " + prem + " du\n" + sec + "échiffre du code avec la valeur 6";
    }

    /**
     * Checks whether the sum of two numbers in the player's code matches the secret code's sum.
     *
     * @param codePlayer The player's code to be checked.
     * @return True if the sum matches, false otherwise.
     */
    @Override
    public boolean check(Code codePlayer) {
        Category catSecret = checkSum(getSecretCode());
        Category catPlayer = checkSum(codePlayer);

        return (catPlayer == catSecret);
    }

    /**
     * Checks the sum of two numbers from the code against a specified value.
     *
     * @param code The code to check the sum against.
     * @return The Category based on the comparison of the sum with the value.
     */
    private Category checkSum(Code code) {
        int result = code.getNumber(first) + code.getNumber(second);

        if (result < value) {
            return Category.PLUS_PETIT;
        } else if (result > value) {
            return Category.PLUS_GRAND;
        } else {
            return Category.EGAL;
        }
    }
}
