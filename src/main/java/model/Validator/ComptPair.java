package model.Validator;

import model.Code;

/**
 * The ComptPair class represents a Validator that counts the number of even numbers in the code.
 * Extends Validator class.
 */
public class ComptPair extends Validator {

    /**
     * Constructs a ComptPair Validator with the secret code.
     *
     * @param secretCode The secret code against which the validation will be performed.
     */
    public ComptPair(Code secretCode) {
        super(secretCode);
        this.description = "Compte combien de chiffres dans le code\n" +
                "sont pairs";
    }

    /**
     * Checks whether the count of even numbers in the player's code matches the secret code's count.
     *
     * @param codePlayer The player's code to be checked.
     * @return True if the count matches, false otherwise.
     */
    @Override
    public boolean check(Code codePlayer) {
        int cptPairsSecret = nbNumberPairs(getSecretCode());
        int cptPairPlayer = nbNumberPairs(codePlayer);

        return (cptPairPlayer == cptPairsSecret);
    }

    /**
     * Counts the number of even numbers in the given code.
     *
     * @param code The code to count even numbers from.
     * @return The count of even numbers.
     */
    private int nbNumberPairs(Code code) {
        int cpt = 0;
        for (int value : code.getListNb()) {
            if (isPair(value)) {
                cpt++;
            }
        }
        return cpt;
    }


}
