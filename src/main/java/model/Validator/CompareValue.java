package model.Validator;

import model.Code;

/**
 * The CompareValue class represents a Validator that compares a specific number in the code with a value.
 * Extends Validator class.
 */
public class CompareValue extends Validator {

    private final int value;
    private final int index;

    /**
     * Constructs a CompareValue Validator with the secret code, index of the number, and comparison value.
     *
     * @param secretCode The secret code against which the validation will be performed.
     * @param index      The index of the number in the code to compare.
     * @param value      The value to compare the number with.
     */
    public CompareValue(Code secretCode, int index, int value) {
        super(secretCode);
        this.index = index;
        this.value = value;

        String place = "";
        switch (index) {
            case 0:
                place = "premier";
                break;
            case 1:
                place = "deuxieme";
                break;
            case 2:
                place = "troisieme";
        }
        this.description = "Compare le " + place + " chiffre du code avec " + value;
    }

    /**
     * Checks whether the comparison of a specific number in the player's code matches the secret code's comparison.
     *
     * @param codeJoueur The player's code to be checked.
     * @return True if the comparison matches, false otherwise.
     */
    @Override
    public boolean check(Code codeJoueur) {
        int number = getSecretCode().getNumber(index);
        Category categSecret = getCategory(number);
        Category categJoueur = getCategory(codeJoueur.getNumber(index));

        return categSecret == categJoueur;
    }

    /**
     * Determines the Category based on the comparison of a number with a value.
     *
     * @param number The number to compare.
     * @return The Category based on the comparison.
     */
    public Category getCategory(int number) {
        // Comparison of the number with the specified value
        if (number < value) {
            return Category.PLUS_PETIT;
        } else if (number == value) {
            return Category.EGAL;
        } else {
            return Category.PLUS_GRAND;
        }
    }
}
