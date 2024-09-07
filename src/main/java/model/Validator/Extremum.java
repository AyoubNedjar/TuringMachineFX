package model.Validator;

import model.Code;

/**
 * The Extremum class represents a Validator that determines the extreme number (strictly smallest or largest) in the code.
 * Extends Validator class.
 */
public class Extremum extends Validator {

    private int index;

    /**
     * Constructs an Extremum Validator with the secret code and the index representing which extremum to evaluate (0 for strictly smallest, 1 for strictly largest).
     *
     * @param secretCode The secret code against which the validation will be performed.
     * @param index      The index representing whether to determine the smallest (0) or largest (1) number in the code.
     */
    public Extremum(Code secretCode, int index) {
        super(secretCode);

        if (index == 0) {
            this.description = "Détermine quel chiffre est strictement le plus petit";
        } else {
            this.description = "Détermine quel chiffre est strictement le plus grand";
        }

    }

    /**
     * Checks whether the extreme number (strictly smallest or largest) in the player's code matches the secret code's extreme number.
     *
     * @param codePlayer The player's code to be checked.
     * @return True if the extreme number matches, false otherwise.
     */
    @Override
    public boolean check(Code codePlayer) {

        Category secretCategory = getMaxCategory(getSecretCode());
        Category playerCategory = getMaxCategory(codePlayer);

        return secretCategory == playerCategory;
    }

    /**
     * Determines the category of the extreme number (smallest or largest) in the code.
     *
     * @param code The code to check for the extreme number.
     * @return Category.AUCUN_CHIFFRE if duplicates are found, Category.PREM_CHIFFRE if the extreme number is the first, Category.DEUX_FOiS if the extreme number is the second, Category.TROIS_CHIFRE if the extreme number is the third.
     */
    private Category getMaxCategory(Code code) {

        int maxNumberIndex = 0;
        boolean hasDuplicates = false;

        for (int i = 1; i < code.getListNb().size(); i++) {
            if (code.getNumber(i) > code.getNumber(maxNumberIndex)) {
                maxNumberIndex = i;
                hasDuplicates = false;
            } else if (code.getNumber(i) == code.getNumber(maxNumberIndex)) {
                hasDuplicates = true;
            }
        }

        if (hasDuplicates) {
            return Category.AUCUN_CHIFFRE;
        } else {
            if (maxNumberIndex == 0) {
                return Category.PREM_CHIFFRE;
            } else if (maxNumberIndex == 1) {
                return Category.DEUX_FOiS;
            } else {
                return Category.TROIS_CHIFRE;
            }
        }
    }

}
