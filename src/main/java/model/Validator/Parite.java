package model.Validator;

import model.Code;

/**
 * The Parite class represents a Validator that checks the parity (odd or even) of a digit in the code.
 * Extends Validator class.
 */
public class Parite extends Validator {

    private final int index;

    /**
     * Constructs a Parite Validator for the specific digit position in the code.
     *
     * @param secretCode The secret code against which the validation will be performed.
     * @param index      The position of the digit to be checked for parity.
     */
    public Parite(Code secretCode, int index) {
        super(secretCode);
        this.index = index;
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
        this.description = "Vérifie la parité du " + place + " chiffre du code";
    }

    /**
     * Checks if the parity of the specific digit in the player's code matches the parity in the secret code.
     *
     * @param codeJoueur The player's code to be checked.
     * @return True if the parity of the digit matches between player and secret code, false otherwise.
     */
    @Override
    public boolean check(Code codeJoueur) {
        int number = getSecretCode().getNumber(index);
        boolean isPairSecret = isPair(number);
        boolean isPairPlayer = isPair(codeJoueur.getNumber(index));

        return (isPairPlayer == isPairSecret);
    }


}
