package DataStructures;

import java.io.Serializable;

public class PriorityQueue<T> implements Serializable {
    private SinglyLinkedList<Task<T>>[] priorityQueues;

    public PriorityQueue(int maxPriority) {
        if (maxPriority <= 0) {
            throw new IllegalArgumentException("La prioridad máxima debe ser mayor que 0.");
        }

        priorityQueues = new SinglyLinkedList[maxPriority + 1];
        for (int i = 0; i <= maxPriority; i++) {
            priorityQueues[i] = new SinglyLinkedList<>();
        }
    }

    public void insert(T item, int priority) {
        if (priority < 0 || priority >= priorityQueues.length) {
            throw new IllegalArgumentException("Prioridad fuera de rango.");
        }

        Task<T> task = new Task<>(item, priority);
        priorityQueues[priority].add(task);
    }

    public T peek() {
        for (SinglyLinkedList<Task<T>> queue : priorityQueues) {
            if (!queue.isEmpty()) {
                return queue.get().getItem();
            }
        }
        return null;
    }

    public T pop() {
        for (SinglyLinkedList<Task<T>> queue : priorityQueues) {
            if (!queue.isEmpty()) {
                return queue.pop().getItem();
            }
        }
        return null;
    }

    public boolean isEmpty() {
        for (SinglyLinkedList<Task<T>> queue : priorityQueues) {
            if (!queue.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < priorityQueues.length; i++) {
            sb.append("Priority ").append(i).append(": [");
            SinglyLinkedList<Task<T>> queue = priorityQueues[i];
            boolean isFirst = true;
            while (!queue.isEmpty()) {
                if (!isFirst) {
                    sb.append(", ");
                }
                sb.append(queue.get().getItem());
                isFirst = false;
            }
            sb.append("]\n");
        }
        return sb.toString();
    }
    public SinglyLinkedList<T> recorrer() {
        SinglyLinkedList<T> elementos = new SinglyLinkedList<>();

        for (SinglyLinkedList<Task<T>> queue : priorityQueues) {
            while (!queue.isEmpty()) {
                elementos.add(queue.pop().getItem());
            }
        }

        return elementos;
    }


    public interface Comparador<T> {
        boolean igualQue(T q);
        boolean menorQue(T q);
        boolean menorIgualQue(T q);
        boolean mayorQue(T q);
        boolean mayorIgualQue(T q);
    }

    public class Task<T> {
        private T item;
        private int priority;

        public Task(T item, int priority) {
            this.item = item;
            this.priority = priority;
        }

        public T getItem() {
            return item;
        }

        public int getPriority() {
            return priority;
        }
    }
}

