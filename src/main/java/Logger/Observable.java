package Logger;

import java.util.ArrayList;
import java.util.List;

public class Observable {
    private List<Observer> d_logObservers = new ArrayList<Observer>();


    public void addObserver(Observer p_observer) {
        this.d_logObservers.add(p_observer);
    }

    public void removeObserver(Observer p_observer) {
        if (!d_logObservers.isEmpty()) {
            d_logObservers.remove(p_observer);
        }
    }

    public void notifyAll(Observable p_logManager) {
        for (Observer l_observer : d_logObservers) {
            l_observer.update(p_logManager);
        }
    }
}
