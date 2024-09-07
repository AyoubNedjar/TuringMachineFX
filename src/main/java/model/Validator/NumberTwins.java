package model.Validator;

import model.Code;

/**
 * The NumberTwins class represents a Validator that checks whether a digit in the code occurs in exactly two instances (but not three).
 * Extends Validator class.
 */
public class NumberTwins extends Validator {


    /**
     * Constructs a NumberTwins Validator with the secret code to perform the validation against.
     *
     * @param secretCode The secret code against which the validation will be performed.
     */
    public NumberTwins(Code secretCode) {
        super(secretCode);
        this.description = "Détermine si un chiffre du code se trouve\n" +
                "en exactement deux exemplaires dans le\n" +
                "code (mais pas trois)\n";
    }

    /**
     * Checks if the occurrence of a digit in the player's code matches the occurrence in the secret code.
     *
     * @param codePlayer The player's code to be checked.
     * @return True if a digit occurs exactly twice in both codes, false otherwise.
     */
    @Override
    public boolean check(Code codePlayer) {
        return isTwins(codePlayer) == isTwins(getSecretCode());
    }

    /**
     * Determines if a digit occurs exactly twice in the code.
     *
     * @param code The code to check for the occurrence of a digit exactly twice.
     * @return True if a digit occurs exactly twice, false otherwise.
     */
    public boolean isTwins(Code code) {
        int nb1 = code.getNb1();
        int nb2 = code.getNb2();
        int nb3 = code.getNb3();

        // Checks if two digits are identical
        if ((nb1 == nb2 && nb1 != nb3) || (nb1 == nb3 && nb1 != nb2) || (nb2 == nb3 && nb2 != nb1)) {
            return true;
        }

        return false;
    }

}
