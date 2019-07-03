package models.net;

public interface MyObserver<T> {
    void onStateChanged(T newState);
}
