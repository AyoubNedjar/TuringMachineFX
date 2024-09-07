package model;

import model.Validator.*;

/**
 * The ValidatorFactory class provides a factory method to create Validator objects based on given IDs.
 */
public class ValidatorFactory {

    /**
     * Creates a Validator object based on the provided ID and secret code.
     *
     * @param id          The ID of the Validator to create.
     * @param secretCode  The secret code associated with the Validator.
     * @return A Validator object corresponding to the given ID.
     * @throws IllegalArgumentException If the provided ID does not match any Validator.
     */
    public static Validator getValidator(int id, Code secretCode) {
        switch (id) {
            case 1:
                return new CompareValue(secretCode, 0, 1);
            case 2:
                return new CompareValue(secretCode, 0, 3);
            case 3:
                return new CompareValue(secretCode, 1, 3);
            case 4:
                return new CompareValue(secretCode, 1, 4);
            case 5:
                return new Parite(secretCode, 0);
            case 6:
                return new Parite(secretCode, 1);
            case 7:
                return new Parite(secretCode, 2);
            case 8:
                return new ComptValue(secretCode, 1);
            case 9:
                return new ComptValue(secretCode, 3);
            case 10:
                return new ComptValue(secretCode, 4);
            case 11:
                return new CompareTwoNumbersValidator(secretCode, 0, 1);
            case 12:
                return new CompareTwoNumbersValidator(secretCode, 0, 2);
            case 13:
                return new CompareTwoNumbersValidator(secretCode, 1, 2);
            case 14:
                return new Extremum(secretCode, 0);
            case 15:
                return new Extremum(secretCode, 1);
            case 16:
                return new ParityMajorityChecker(secretCode);
            case 17:
                return new ComptPair(secretCode);
            case 18:
                return new SumParite(secretCode);
            case 19:
                return new CompareSumTwoNumber(secretCode, 0, 1, 6);
            case 20:
                return new Doublon(secretCode);
            case 21:
                return new NumberTwins(secretCode);
            case 22:
                return new Croissant(secretCode);
            default:
                throw new IllegalArgumentException("Validator with id " + id + " not found");
        }
    }
}
