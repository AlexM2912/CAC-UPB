package DataStructures;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SinglyLinkedList<T> implements LinkedListInterface<T> {

    SinglyLinkedNode<T> head;
    SinglyLinkedNode<T> tail;
    SinglyLinkedNode<T> inode;
    int size = 0;


    @Override
    public boolean add(T object) {
        if (object != null) {
            if (isEmpty()) {
                tail = head = new SinglyLinkedNode<T>(object);
            } else {
                tail.setNext(new SinglyLinkedNode<T>(object));
                tail = tail.getNext();
            }
            size++;
            return true;
        }
        return false;
    }

    @Override
    public boolean add(SinglyLinkedNode<T> node, T object) {
        if (node != null) {
            SinglyLinkedNode<T> newNode = new SinglyLinkedNode<>(object);

            if (node.getNext() == null) {
                node.setNext(newNode);
            } else {
                newNode.setNext(node.getNext());
                node.setNext(newNode);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean add(SinglyLinkedNode<T> node, SinglyLinkedNode<T> next) {
        if (node != null && next != null) {
            add(node, next.getObject());
            return true;
        }
        return false;
    }

    @Override
    public boolean add(T[] objects) {
        for (T element:objects){
            add(element);
        }
        return true;
    }

    @Override
    public boolean add(SinglyLinkedNode<T> node, T[] objects) {
        if (node != null && objects != null) {
            for (T elements:objects) {
                SinglyLinkedNode<T> newNode = new SinglyLinkedNode<>(elements);
                if (node.getNext() == null) {
                    node.setNext(newNode);
                } else {
                    newNode.setNext(node.getNext());
                    node.setNext(newNode);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean addOnHead(T object) {
        if (object!=null) {
            if (head == null){
                head = tail = new SinglyLinkedNode<>(object);
            }else {
                SinglyLinkedNode<T> newnode = new SinglyLinkedNode<>(object);
                newnode.setNext(head);
                head = newnode;
            }
            size++;
            return true;
        }
        return false;
    }

    @Override
    public boolean addOnHead(T[] objects) {
        for (T element:objects){
            addOnHead(element);
        }
        return true;
    }

    @Override
    public boolean clear() {
        if (isEmpty()){
            return false;
        }
        head = null;
        return true;
    }

    @Override
    public SinglyLinkedList<T> cloneList() {
        SinglyLinkedList linkedList = null;
        try {
            linkedList = new SinglyLinkedList();
            Iterator iterator = this.iterator();

            while(iterator.hasNext()) {
                linkedList.add(this.inode.getObject());
                iterator.next();
            }
        } catch (Exception var3) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, var3.getMessage(), var3);
        }

        return linkedList;
    }

    @Override
    public boolean contains(T object) {
        Iterator iterator = iterator();
        while (iterator.hasNext()) {
            if (object.toString().equals(inode.getObject().toString())){
                return true;
            }
            iterator.next();
        }
        return false;
    }

    @Override
    public boolean contains(T[] objects) {
        if (objects != null) {
            Iterator iterator = iterator();
            while (iterator.hasNext()) {
                for (T object : objects) {
                    if (!contains(object)) {
                        return false;
                    }
                }
                iterator.next();
            }
        }
        return true;
    }

    @Override
    public SinglyLinkedNode<T> nodeOf(T object) {
        if (object != null) {
            try {
                Iterator iterator = iterator();
                while(iterator.hasNext()) {
                    if (inode.getObject().toString().equals(object.toString())) {
                        return inode;
                    }
                    iterator.next();
                }
            } catch (Exception var3) {
                Logger.getLogger(this.getClass().getName()).log(Level.WARNING, var3.getMessage(), var3);
            }
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public T get() {
        return head.getObject();
    }

    @Override
    public T[] get(int n) {
        if (n <= 0) {
            return null;
        }

        if (n > size) {
            n = size;
        }

        @SuppressWarnings("unchecked")
        T[] array = (T[]) new Object[n];

        SinglyLinkedNode<T> current = head;
        int index = 0;

        while (current != null && index < n) {
            array[index] = current.getObject();
            current = current.getNext();
            index++;
        }

        return array;
    }


    @Override
    public T getPrevious(SinglyLinkedNode<T> node) {
        return null;
    }

    @Override
    public T getFromEnd() {
        if (isEmpty()){
            return null;
        }else {
            return tail.getObject();
        }
    }

    @Override
    public T[] getFromEnd(int n) {
        return null;
    }

    @Override
    public T pop() {
        try {
            inode = head;
            head = head.getNext();
            size--;
            return inode.getObject();
        } catch (Exception var2) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, var2.getMessage(), var2);
            return null;
        }
    }

    @Override
    public boolean remove(T object) {
        try {
            if (head != null && head.getObject().equals(object)) {
                head = head.getNext();
                size--;
                return true;
            }
            Iterator iterator = iterator();
            while(iterator.hasNext()) {
                if (inode.next.getObject().equals(object)) {
                    inode.setNext(inode.getNext().getNext());
                    --this.size;
                    return true;
                }else {
                    this.inode = this.inode.getNext();
                }
            }
        } catch (Exception var3) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Objeto no se encuentra en lista");
        }

        return false;
    }

    @Override
    public boolean remove(SinglyLinkedNode<T> node) {
        return false;
    }

    @Override
    public boolean removeAll(T[] objects) {
        return false;
    }

    @Override
    public boolean retainAll(T[] objects) {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public LinkedListInterface<T> subList(SinglyLinkedNode<T> from, SinglyLinkedNode<T> to) {
        return null;
    }

    @Override
    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] array = (T[]) new Object[size];

        SinglyLinkedNode<T> current = head;
        int index = 0;

        while (current != null) {
            array[index] = current.getObject();
            current = current.getNext();
            index++;
        }

        return array;
    }



    @Override
    public boolean sortObjectsBySize() {
        return false;
    }

    @Override
    public String toString() {
        if (!isEmpty()) {
            Iterator iterator = iterator();
            StringBuilder stringList = new StringBuilder();
            inode = head;
            while (iterator.hasNext()) {
                stringList.append(inode.getObject().toString());
                iterator.next();
                if (iterator.hasNext()) {
                    stringList.append("\n");
                }
            }
            return stringList.toString();
        }
        return "";
    }


    @Override
    public Iterator<SinglyLinkedNode<T>> iterator() {
        inode = head;
        return  new Iterator<SinglyLinkedNode<T>>() {
            @Override
            public boolean hasNext() {
                return inode != null;
            }

            @Override
            public SinglyLinkedNode<T> next() {
                SinglyLinkedNode<T> node = null;
                if (inode != null){
                    node = inode;
                    inode = inode.getNext();
                }
                return node;
            }
        };
    }
}
