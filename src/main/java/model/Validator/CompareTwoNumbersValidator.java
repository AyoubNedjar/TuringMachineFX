package model.Validator;

import model.Code;

/**
 * The CompareTwoNumbersValidator class represents a Validator that compares two numbers from the code.
 * Extends Validator class.
 */
public class CompareTwoNumbersValidator extends Validator {

    private final int firstIndex;
    private final int secondIndex;

    /**
     * Constructs a CompareTwoNumbersValidator with the secret code, indices of two numbers to compare.
     *
     * @param secretCode   The secret code against which the validation will be performed.
     * @param firstIndex   The index of the first number in the code.
     * @param secondIndex  The index of the second number in the code.
     */
    public CompareTwoNumbersValidator(Code secretCode, int firstIndex, int secondIndex) {
        super(secretCode);
        this.firstIndex = firstIndex;
        this.secondIndex = secondIndex;

        String prem = getName(firstIndex);
        String sec = getName(secondIndex);
        this.description = "Compare le " + prem + " chiffre du code avec le\n" +
                sec;
    }

    /**
     * Checks whether the comparison of two numbers in the player's code matches the secret code's comparison.
     *
     * @param codePlayer The player's code to be checked.
     * @return True if the comparison matches, false otherwise.
     */
    @Override
    public boolean check(Code codePlayer) {
        int firstNumberSecret = getSecretCode().getNumber(firstIndex);
        int secondNumberSecret = getSecretCode().getNumber(secondIndex);

        int firstNumberPlayer = codePlayer.getNumber(firstIndex);
        int secondNumberPlayer = codePlayer.getNumber(secondIndex);

        return (getCategory(firstNumberSecret, secondNumberSecret)
                == getCategory(firstNumberPlayer, secondNumberPlayer));
    }

    /**
     * Determines the Category based on the comparison of two numbers.
     *
     * @param firstNumber  The first number.
     * @param secondNumber The second number.
     * @return The Category based on the comparison.
     */
    private Category getCategory(int firstNumber, int secondNumber) {
        if (firstNumber < secondNumber) {
            return Category.PLUS_PETIT;
        } else if (firstNumber == secondNumber) {
            return Category.EGAL;
        } else {
            return Category.PLUS_GRAND;
        }
    }
}
