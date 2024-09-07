package model.commands;

import model.Code;
import model.Game;

/**
 * The EnterNewCode class represents a command to enter a new code in the game.
 * It implements the Command interface to execute and unexecute this action.
 */
public class EnterNewCode implements Command {

    private Game game;
    private Code nvCode;
    private Code lastCode;
    private int round;

    /**
     * Constructs an EnterNewCode command with the provided Game instance and new code.
     *
     * @param game    The Game instance associated with this command.
     * @param newCode The new Code to be entered.
     */
    public EnterNewCode(Game game, Code newCode) {
        this.game = game;
        nvCode = newCode;
    }

    /**
     * Executes the command by setting the player's code in the game.
     */
    @Override
    public void execute() {
        lastCode = game.getPlayerCode();
        round = game.getRound();
        game.setPlayerCode(nvCode);
    }

    /**
     * Undoes the executed command by reverting the player's code and round to their previous values.
     */
    @Override
    public void unexecute() {
        if (lastCode != null) {
            game.setPlayerCode(lastCode);
            game.setRound(round);
        }
    }

    /**
     * Retrieves the last code entered.
     *
     * @return The last code entered as a Code object.
     */
    public Code getLastCode() {
        return lastCode;
    }
}
