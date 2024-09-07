package model;

import model.commands.Command;
import model.commands.EnterNewCode;
import model.commands.NewValidator;

import java.util.*;

/**
 * The GameFacade class acts as a facade for managing game operations and maintaining game state.
 * It implements the Observable interface to notify registered Observers about state changes.
 */
public class GameFacade implements Observable {

    private Game game;
    private Problem problem;
    private Stack<Command> history;
    private Stack<Command> redoStack;
    private Code precedent;
    private Map<Integer, Boolean> lastResult;
    private Set<Observer> obs = new HashSet<>();

    /**
     * Starts a new game based on the provided problem.
     *
     * @param pb The problem ID to load the game.
     */
    public void start(int pb) {
        problem = LoadGameFromFile.getInstance().getProblem(pb);
        game = new Game(problem);
        history = new Stack<>();
        redoStack = new Stack<>();
        notifyObservers(1);
    }

    /**
     * Enters a new code into the game.
     *
     * @param nb1 The first number of the code.
     * @param nb2 The second number of the code.
     * @param nb3 The third number of the code.
     */
    public void enterCode(int nb1, int nb2, int nb3) {
        Command enterNewCode = new EnterNewCode(game, new Code(nb1, nb2, nb3));
        enterNewCode.execute();
        history.push(enterNewCode);
        notifyObservers(2);
    }

    /**
     * Chooses a validator for the game.
     *
     * @param index The index of the validator to choose.
     */
    public void chooseValidator(int index) {
        Command chooseValidator = new NewValidator(game, index);
        chooseValidator.execute();
        history.push(chooseValidator);
        notifyObservers(3);
    }

    /**
     * Proceeds to the next round in the game.
     */
    public void nextRound() {
        if (game.getPlayerCode() != null) {
            precedent = new Code(game.getPlayerCode());
        }
        lastResult = new HashMap<>(game.getValidatorResult());
        game.setValidatorResult(new HashMap<>());
        game.initValidatorPlayed();
        game.setRound(game.getRound() + 1);
        game.initCodePlayer();
        notifyObservers(4);
    }

    /**
     * Checks if the player's code is correct and wins the game.
     *
     * @return True if the player's code is correct, false otherwise.
     */
    public boolean isCorrect() {
        return game.isWin();
    }

    /**
     * Quits the game and exits the application.
     */
    public void quitGame() {
        System.exit(0);
    }

    /**
     * Undoes the last action in the game.
     */
    public void undo() {
        if (!history.empty()) {
            Command cmd = history.pop();
            cmd.unexecute();
            redoStack.push(cmd);
            notifyObservers(5);
        } else {
            System.out.println("Nothing to undo.");
        }
    }

    /**
     * Redoes the last undone action in the game.
     */
    public void redo() {
        if (!redoStack.empty()) {
            Command cmd = redoStack.pop();
            cmd.execute();
            history.push(cmd);
            notifyObservers(6);
        } else {
            System.out.println("Nothing to redo.");
        }
    }

    /**
     * Retrieves the current game instance.
     *
     * @return The current Game instance.
     */
    public Game getGame() {
        return game;
    }

    /**
     * Retrieves the problem associated with the game.
     *
     * @return The Problem instance associated with the game.
     */
    public Problem getProblem() {
        return problem;
    }

    /**
     * Retrieves the previous code entered in the game.
     *
     * @return The previous Code instance.
     */
    public Code getPrecedent() {
        return precedent;
    }

    /**
     * Retrieves the result of the last game action.
     *
     * @return The last result as a map of validator index to boolean value.
     */
    public Map<Integer, Boolean> getLastResult() {
        return lastResult;
    }

    @Override
    public void addObserver(Observer o) {
        obs.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        obs.remove(o);
    }

    @Override
    public void notifyObservers(int message) {
        for (Observer observer : obs) {
            observer.update(this, message);
        }
    }
}
