package model.commands;

/**
 * The Command interface represents an action or operation that can be executed and undone.
 */
public interface Command {

    /**
     * Executes the command or action.
     */
    void execute();

    /**
     * Undoes the executed command or action to revert its effects.
     */
    void unexecute();
}
