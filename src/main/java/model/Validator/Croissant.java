package model.Validator;

import model.Code;

/**
 * The Croissant class represents a Validator that determines if the three digits of the code are in ascending or descending order.
 * Extends Validator class.
 */
public class Croissant extends Validator {

    /**
     * Constructs a Croissant Validator with the secret code.
     *
     * @param secretCode The secret code against which the validation will be performed.
     */
    public Croissant(Code secretCode) {
        super(secretCode);
        this.description = "Détermine si les trois chiffres du code sont\n" +
                "en ordre croissant ou décroissant";
    }

    /**
     * Checks whether the order of the three digits in the player's code matches the secret code's order.
     *
     * @param codePlayer The player's code to be checked.
     * @return True if the order matches, false otherwise.
     */
    @Override
    public boolean check(Code codePlayer) {
        Category catPlayer = isSorted(codePlayer);
        Category catSecret = isSorted(getSecretCode());

        return catSecret == catPlayer;
    }

    /**
     * Determines if the digits of the code are in ascending or descending order.
     *
     * @param code The code to check for ascending or descending order.
     * @return Category.CROISSANT if digits are in ascending order, Category.DECROISSANT if in descending order, or Category.AUCUN if neither.
     */
    public Category isSorted(Code code) {
        int nb1 = code.getNb1();
        int nb2 = code.getNb2();
        int nb3 = code.getNb3();

        // Checks for ascending order
        if (nb1 < nb2 && nb2 < nb3) {
            return Category.CROISSANT;
        }

        // Checks for descending order
        if (nb1 > nb2 && nb2 > nb3) {
            return Category.DECROISSANT;
        }
        return Category.AUCUN;
    }
}
