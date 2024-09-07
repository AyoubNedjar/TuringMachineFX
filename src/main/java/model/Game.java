package model;

import model.Validator.Validator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Game class represents an instance of a game with associated validators,
 * secret code, and player's attempts to solve the code.
 */
public class Game {
    private Problem pb;
    private List<Validator> validatorPossibles;
    private List<Validator> validatorPlayed;
    private Code secretCode;
    private Code playerCode;
    private int round;
    private Map<Integer, Boolean> validatorResult;

    /**
     * Constructs a Game instance associated with the provided problem.
     *
     * @param pb The problem instance related to the game.
     */
    public Game(Problem pb) {
        this.pb = pb;
        validatorPossibles = new ArrayList<>();
        validatorPlayed = new ArrayList<>();
        secretCode = this.pb.getSecretCode();
        validatorResult = new HashMap<>();
        initValidators();
    }

    /**
     * Initializes the available validators based on the problem's valid index list.
     */
    public void initValidators() {
        for (var i : pb.getValidIndexList()) {
            validatorPossibles.add(ValidatorFactory.getValidator(i, secretCode));
        }
    }

    /**
     * Initializes the list of validators played by the player.
     */
    public void initValidatorPlayed() {
        this.validatorPlayed = new ArrayList<>();
    }

    /**
     * Adds a validator to the list of validators played by the player.
     *
     * @param index The index of the validator to add.
     * @throws IndexOutOfBoundsException    if the chosen validator index does not exist.
     * @throws IllegalArgumentException     if the chosen validator has already been selected
     *                                      or if the maximum number of validators (3) has been reached.
     */
    public void addValidatorInList(int index) {
        if (index >= validatorPossibles.size()) {
            throw new IndexOutOfBoundsException("Chosen validator index does not exist.");
        }
        if (validatorPlayed.contains(validatorPossibles.get(index))) {
            throw new IllegalArgumentException("The validator has already been chosen.");
        }
        if (validatorPlayed.size() >= 3) {
            throw new IllegalArgumentException("Maximum number of validators (3) reached.");
        }
        validatorPlayed.add(validatorPossibles.get(index));
    }

    /**
     * Removes a validator from the list of validators played by the player.
     *
     * @param index The index of the validator to remove.
     */
    public void removeValidatorPlayed(int index) {
        validatorPlayed.remove(validatorPossibles.get(index));
    }

    /**
     * Checks if the player has won the game.
     *
     * @return True if the player's code matches the secret code, false otherwise.
     */
    public boolean isWin() {
        return secretCode.equals(playerCode);
    }

    /**
     * Gets the player's code.
     *
     * @return The player's code.
     */
    public Code getPlayerCode() {
        return playerCode;
    }

    /**
     * Sets the player's code for the game.
     *
     * @param playerCode The code input by the player.
     * @throws IllegalArgumentException if the input code contains numbers outside the range of 1 to 5.
     */
    public void setPlayerCode(Code playerCode) {
        for (int nb : playerCode.getListNb()) {
            if (nb > 5 || nb < 1) {
                throw new IllegalArgumentException("Invalid code: Numbers should be between 1 and 5.");
            }
        }
        this.playerCode = playerCode;
    }

    /**
     * Initializes the player's code to null.
     */
    public void initCodePlayer() {
        this.playerCode = null;
    }

    /**
     * Sets the current round of the game.
     *
     * @param round The round number.
     */
    public void setRound(int round) {
        this.round = round;
    }

    /**
     * Gets the current round of the game.
     *
     * @return The round number.
     */
    public int getRound() {
        return round;
    }

    /**
     * Gets the map containing validator results for the player's attempts.
     *
     * @return The map of validator results.
     */
    public Map<Integer, Boolean> getValidatorResult() {
        return validatorResult;
    }

    /**
     * Sets the map containing validator results for the player's attempts.
     *
     * @param validatorResult The map of validator results to set.
     */
    public void setValidatorResult(Map<Integer, Boolean> validatorResult) {
        this.validatorResult = validatorResult;
    }

    /**
     * Gets the list of available validators.
     *
     * @return The list of available validators.
     */
    public List<Validator> getValidatorPossibles() {
        return validatorPossibles;
    }

    /**
     * Gets the list of validators played by the player.
     *
     * @return The list of validators played by the player.
     */
    public List<Validator> getValidatorPlayed() {
        return validatorPlayed;
    }

    /**
     * Adds a result for a specific validator index.
     *
     * @param index  The index of the validator.
     * @param result The result (true/false) associated with the validator index.
     */
    public void addResult(int index, boolean result) {
        validatorResult.put(index, result);
    }

    /**
     * Removes the result associated with a specific validator index.
     *
     * @param key The index of the validator result to be removed.
     */
    public void removeResult(int key) {
        validatorResult.remove(key);
    }
}
