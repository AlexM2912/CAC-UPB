package DataStructures;

import java.io.Serializable;
import java.util.Arrays;

public class ColaArray<T extends Serializable> implements QueueInterface<T>, Serializable {

    private T[] arr;
    private int max;
    private int tamano;
    private int head;
    private int tail;

    public ColaArray(int max) {
        this.max = max;
        tamano = 0;
        head = 0;
        tail = 0;
        arr = (T[]) new Serializable[max];
    }

    @Override
    public boolean clear() {
        Arrays.fill(arr, null);
        tamano = 0;
        head = 0;
        tail = 0;
        return true;
    }

    @Override
    public boolean isEmpty() {
        if (head == 0) {
            return false;
        }
        return true;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return arr[head];
    }

    @Override
    public T extract() {
        if (!isEmpty()) {
            T pop = arr[head];
            arr[head] = null;
            head = (head + 1) % max;
            tamano--;
            return pop;
        }
        return null;
    }

    @Override
    public boolean insert(T object) {
        if (tamano < max) {
            arr[tail] = object;
            tail = (tail + 1) % max;
            tamano++;
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return tamano;
    }

    @Override
    public boolean search(T object) {
        for (int i = 0; i < tamano; i++) {
            int index = (head + i) % max;
            if (arr[index] != null && arr[index].equals(object)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean sort() {
        return false;
    }

    @Override
    public boolean reverse() {
        return false;
    }
}

