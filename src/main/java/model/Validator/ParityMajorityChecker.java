package model.Validator;

import model.Code;

/**
 * The ParityMajorityChecker class is a Validator that determines if there are more even or odd numbers in the code.
 * Extends Validator class.
 */
public class ParityMajorityChecker extends Validator {

    /**
     * Constructs a ParityMajorityChecker Validator.
     *
     * @param secretCode The secret code against which the validation will be performed.
     */
    public ParityMajorityChecker(Code secretCode) {
        super(secretCode);
        this.description = "Détermine s’il y a plus de chiffre pairs ou impairs dans le code";
    }

    /**
     * Checks whether there are more even or odd numbers in the player's code compared to the secret code.
     *
     * @param codePlayer The player's code to be checked.
     * @return True if there is a majority of even or odd numbers compared to the secret code, false otherwise.
     */
    @Override
    public boolean check(Code codePlayer) {
        int cptPairSecret = countNumber(getSecretCode());
        int cptImpairSecret = getSecretCode().getListNb().size() - cptPairSecret;

        int cptPairPlayer = countNumber(codePlayer);
        int cptImpairPlayer = codePlayer.getListNb().size() - cptPairPlayer;

        Category CateSecret = getCategory(cptPairSecret, cptImpairSecret);
        Category CatePlayer = getCategory(cptPairPlayer, cptImpairPlayer);

        return (CateSecret == CatePlayer);
    }

    /**
     * Counts the number of even (pair) digits in the given code.
     *
     * @param code The code to count the even digits from.
     * @return The count of even digits in the code.
     */
    private int countNumber(Code code) {
        int cpt = 0;
        for (int value : code.getListNb()) {
            if (isPair(value)) {
                cpt++;
            }
        }
        return cpt;
    }

    /**
     * Determines the category based on the count of even and odd numbers.
     *
     * @param cptPair   The count of even digits.
     * @param cptImpair The count of odd digits.
     * @return The category (PAIR or IMPAIR) based on the counts.
     */
    private Category getCategory(int cptPair, int cptImpair) {
        if (cptPair > cptImpair) {
            return Category.PAIR;
        } else {
            return Category.IMPAIR;
        }
    }
}
