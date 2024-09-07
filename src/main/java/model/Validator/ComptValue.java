package model.Validator;

import model.Code;

/**
 * The ComptValue class represents a Validator that counts the occurrences of a specific value in the code.
 * Extends Validator class.
 */
public class ComptValue extends Validator {

    private final int valueToCompt;

    /**
     * Constructs a ComptValue Validator with the secret code and the value to count occurrences.
     * Verifies that the value is not greater than 5 and not smaller than 1.
     *
     * @param secretCode   The secret code against which the validation will be performed.
     * @param valueToCompt The value to count occurrences of in the code.
     */
    public ComptValue(Code secretCode, int valueToCompt) {
        super(secretCode);
        if (valueToCompt > 5 || valueToCompt < 1) {
            throw new IllegalArgumentException("La valeur doit être comprise entre 1 et 5 inclusivement.");
        }
        this.valueToCompt = valueToCompt;
        this.description = "Compte combien de fois la valeur " + valueToCompt + " apparaît\n" +
                "dans le code";
    }

    /**
     * Checks whether the count of occurrences of a specific value in the player's code matches the secret code's count.
     *
     * @param codePlayer The player's code to be checked.
     * @return True if the count matches, false otherwise.
     */
    @Override
    public boolean check(Code codePlayer) {
        int countSecret = countOccurrences(getSecretCode(), valueToCompt);
        int countPlayer = countOccurrences(codePlayer, valueToCompt);

        return countSecret == countPlayer;
    }

    /**
     * Counts the occurrences of a specific value in the given code.
     *
     * @param code  The code to count occurrences from.
     * @param value The value to count occurrences of.
     * @return The count of occurrences of the value.
     */
    private int countOccurrences(Code code, int value) {
        int count = 0;
        for (int num : code.getListNb()) {
            if (num == value) {
                count++;
            }
        }
        return count;
    }
}
