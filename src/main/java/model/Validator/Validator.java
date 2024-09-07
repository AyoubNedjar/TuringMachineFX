package model.Validator;

import model.Code;

/**
 * The abstract class Validator represents a blueprint for different types of validators used in the game.
 */
public abstract class Validator {

    /**
     * Represents the secret code associated with each validator.
     */
    protected Code secretCode;

    /**
     * Describes the functionality or purpose of the validator.
     */
    protected String description;

    /**
     * Constructor for Validator class.
     *
     * @param secretCode The secret code associated with the validator.
     */
    public Validator(Code secretCode) {
        this.secretCode = secretCode;
    }

    /**
     * Abstract method that needs to be implemented by subclasses.
     * Defines the logic to validate a player's code against the secret code.
     *
     * @param codePlayer The code entered by the player to be validated.
     * @return True if the player's code matches the secret code; otherwise, false.
     */
    public abstract boolean check(Code codePlayer);

    /**
     * Checks whether a given number is even.
     *
     * @param number The number to be checked for evenness.
     * @return True if the number is even; otherwise, false.
     */
    public boolean isPair(int number) {
        return number % 2 == 0;
    }

    /**
     * Retrieves the name of the index (first, second, or third) based on the provided index value.
     *
     * @param index The index value (0 for the first, 1 for the second, and 2 for the third).
     * @return The name of the index (first, second, or third).
     */
    public String getName(int index) {
        String place = "";
        switch (index) {
            case 0:
                place = "first";
                break;
            case 1:
                place = "second";
                break;
            case 2:
                place = "third";
                break;
        }
        return place;
    }

    /**
     * Retrieves the secret code associated with the validator.
     *
     * @return The secret code.
     */
    public Code getSecretCode() {
        return secretCode;
    }

    /**
     * Retrieves the description of the validator.
     *
     *
     * @return The description of the validator..
     */
    public String getDescription() {
        return description;
    }
}
