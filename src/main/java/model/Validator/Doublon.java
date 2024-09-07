package model.Validator;

import model.Code;

/**
 * The Doublon class represents a Validator that determines if a digit in the code repeats and, if so, how many times.
 * Extends Validator class.
 */
public class Doublon extends Validator {

    /**
     * Constructs a Doublon Validator with the secret code.
     *
     * @param secretCode The secret code against which the validation will be performed.
     */
    public Doublon(Code secretCode) {
        super(secretCode);
        this.description = "Détermine si un chiffre du code se répète,\n" +
                "et si oui, combien de fois";
    }

    /**
     * Checks whether the digit repetition in the player's code matches the secret code's repetition.
     *
     * @param codePlayer The player's code to be checked.
     * @return True if the repetition matches, false otherwise.
     */
    @Override
    public boolean check(Code codePlayer) {
        Category catPlayer = getCategory(codePlayer);
        Category catSecretPlayer = getCategory(getSecretCode());

        return catPlayer == catSecretPlayer;
    }

    /**
     * Determines the category of digit repetition in the code (no repetition, repeated digits, or triple digits).
     *
     * @param code The code to check for digit repetition.
     * @return Category.PAS_DOUBLON if no digit is repeated, Category.DOUBLON if a digit is repeated twice, Category.CHIFFRE_TRIPLE if a digit is repeated three times.
     */
    public Category getCategory(Code code) {
        int cptnb1 = howMuch(code.getNb1(), code);
        int cptNb2 = howMuch(code.getNb2(), code);
        int cptnb3 = howMuch(code.getNb3(), code);

        if ((code.getNb1() != code.getNb2()) && (code.getNb1() != code.getNb3())
                && (code.getNb2() != code.getNb3())) {
            return Category.PAS_DOUBLON;
        } else if (cptNb2 == 2 || cptnb1 == 2 || cptnb3 == 2) {
            return Category.DOUBLON;
        } else {
            return Category.CHIFFRE_TRIPLE;
        }
    }

    /**
     * Counts how many times a specific digit appears in the code.
     *
     * @param nb   The digit to count occurrences of.
     * @param code The code to check for digit occurrences.
     * @return The count of occurrences of the digit in the code.
     */
    private int howMuch(int nb, Code code) {
        int cpt = 0;
        for (int chiffre : code.getListNb()) {
            if (chiffre == nb) {
                cpt++;
            }
        }
        return cpt;
    }
}
