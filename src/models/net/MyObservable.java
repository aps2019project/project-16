package models.net;

import java.util.ArrayList;

public class MyObservable<T> {
    private T state;

    private ArrayList<MyObserver<? super T>> observers = new ArrayList<>();

    public MyObservable(T state) {
        this.state = state;
    }

    public void addObserver(MyObserver<? super T> observer) {
        observers.add(observer);
        notifyObserver(observer);
    }

    public void removeObserver(MyObserver<? super T> observer) {
        observers.remove(observer);
    }

    public T getState() {
        return state;
    }

    public synchronized void setState(T state) {
        this.state = state;
        notifyAllObservers();
    }

    private void notifyObserver(MyObserver<? super T> observer) {
        observer.onStateChanged(getState());
    }

    private void notifyAllObservers() {
        observers.forEach(observer -> observer.onStateChanged(getState()));
    }
}