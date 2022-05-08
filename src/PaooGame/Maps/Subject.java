package PaooGame.Maps;

/*! \interface Subject
    \brief An interface for implementing the Observer Design Pattern
 */
public interface Subject {

    public void registerObserver(Observer o);
    public void unregisterObserver(Observer o);
    public void notifyObservers();
}
