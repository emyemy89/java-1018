package lab3;

public interface Queue {
    boolean isEmpty ();
    int size ();
    void put(char element);
    char take () throws IllegalStateException;
}
