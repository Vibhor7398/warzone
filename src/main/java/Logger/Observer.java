package Logger;

/**
 * The Observer interface represents objects that should be notified of changes in the Observable.
 */
public interface Observer {

    /**
     * This method is called by the Observable when it has been updated.
     *
     * @param p_logState The Observable object representing the state of the log.
     */
    public void update(Observable p_logState);
}
