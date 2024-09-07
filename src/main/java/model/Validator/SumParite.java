package model.Validator;

import model.Code;

/**
 * The SumParite class is a Validator that determines if the sum of the digits in the code is even or odd.
 * Extends Validator class.
 */
public class SumParite extends Validator {

    /**
     * Constructs a SumParite Validator.
     *
     * @param secretCode The secret code against which the validation will be performed.
     */
    public SumParite(Code secretCode) {
        super(secretCode);
        this.description = "Détermine si la somme des chiffres du code est paire ou impaire";
    }

    /**
     * Checks whether the sum of digits in the player's code is even or odd compared to the secret code.
     *
     * @param codePlayer The player's code to be checked.
     * @return True if the sum of digits is even or odd compared to the secret code, false otherwise.
     */
    @Override
    public boolean check(Code codePlayer) {
        boolean isPairAllSecret = isPairSum(getSecretCode());
        boolean isPairAllPlayer = isPairSum(codePlayer);

        return (isPairAllSecret == isPairAllPlayer);
    }

    public boolean isPairSum(Code code){
            int sum  = 0;
        for (int nb : code.getListNb() ) {
            sum+=nb;
        }
        return isPair(sum);
    }



}
