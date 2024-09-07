package model;

/**
 * The Observer interface defines the contract for an observer that can receive updates from an Observable.
 */
public interface Observer {

    /**
     * Receives an update from the Observable.
     *
     * @param observable The Observable object sending the update.
     * @param arg        An integer argument representing the update information.
     */
    void update(Observable observable, int arg);
}
