package model;

/**
 * The Observable interface defines the contract for an observable object that can be observed by Observers.
 */
public interface Observable {

    /**
     * Adds an Observer to the list of observers.
     *
     * @param o The Observer to be added.
     */
    void addObserver(Observer o);

    /**
     * Removes an Observer from the list of observers.
     *
     * @param o The Observer to be removed.
     */
    void removeObserver(Observer o);

    /**
     * Notifies all registered Observers about a change in the observable object.
     *
     * @param message An integer message indicating the type of change or update.
     */
    void notifyObservers(int message);
}
