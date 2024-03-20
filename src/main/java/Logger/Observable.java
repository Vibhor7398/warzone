/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghimire, Inderjeet Singh Chauhan, Mohammad Zaid Shaikh
 * @version 2.0
 */
package Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * The Observable class represents an observable object, or "data" in the model-view paradigm.
 * It can be observed by other objects that implement the Observer interface.
 */
public class Observable {
    private List<Observer> d_logObservers = new ArrayList<Observer>();

    /**
     * Adds an observer to the list of observers.
     * @param p_observer the observer to be added
     */
    public void addObserver(Observer p_observer) {
        this.d_logObservers.add(p_observer);
    }

    /**
     * Removes an observer from the list of observers.
     * @param p_observer the observer to be removed
     */
    public void removeObserver(Observer p_observer) {
        if (!d_logObservers.isEmpty()) {
            d_logObservers.remove(p_observer);
        }
    }

    /**
     * Notifies all observers that the observable object has been updated.
     * @param p_logManager the observable object that has been updated
     */
    public void notifyAll(Observable p_logManager) {
        for (Observer l_observer : d_logObservers) {
            l_observer.update(p_logManager);
        }
    }
}
