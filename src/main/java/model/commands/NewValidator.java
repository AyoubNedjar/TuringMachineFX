package model.commands;

import model.Game;
import model.Validator.Validator;

/**
 * The NewValidator class represents a command to choose a new validator in the game.
 * It implements the Command interface to execute and unexecute this action.
 */
public class NewValidator implements Command {

    private Game game;
    private final int index;
    private boolean result;

    /**
     * Constructs a NewValidator command with the provided Game instance and index.
     *
     * @param game  The Game instance associated with this command.
     * @param index The index of the validator to choose.
     */
    public NewValidator(Game game, int index) {
        this.game = game;
        this.index = index;
    }

    /**
     * Executes the command by choosing a validator at the specified index and adding it to the game.
     */
    @Override
    public void execute() {
        Validator validator = game.getValidatorPossibles().get(index);
        game.addValidatorInList(index);
        result = validator.check(game.getPlayerCode());
        game.addResult(index, result);
    }

    /**
     * Undoes the executed command by removing the chosen validator from the game.
     */
    @Override
    public void unexecute() {
        game.removeValidatorPlayed(index);
        game.removeResult(index);
    }

    /**
     * Retrieves the result of the validation process.
     *
     * @return The result of the validation as a boolean value.
     */
    public boolean isResult() {
        return result;
    }
}
