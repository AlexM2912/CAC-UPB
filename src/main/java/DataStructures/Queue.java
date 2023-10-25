package DataStructures;

import java.io.Serializable;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Queue<T> implements Serializable {

    SinglyLinkedNode<T> head;
    SinglyLinkedNode<T> tail;
    SinglyLinkedNode<T> inode;
    int size = 0;

    public boolean insert(T data){
        if (data != null) {
            if (isEmpty()) {
                tail = head = new SinglyLinkedNode<T>(data);
            } else {
                tail.setNext(new SinglyLinkedNode<T>(data));
                tail = tail.getNext();
            }
            size++;
            return true;
        }
        return false;
    }

    public boolean insert(T[] data){
        for (T element:data){
            insert(element);
        }
        return true;
    }

    public T peek(){
        return head.getObject();
    }

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

    public String toString() {
        if (!isEmpty()) {
            Iterator iterator = iterator();
            StringBuilder stringList = new StringBuilder();
            inode = head;
            while (iterator.hasNext()) {
                stringList.append(inode.ToString());
                iterator.next();
                if (iterator.hasNext()){
                    stringList.append(" -> ");
                }
            }
            return stringList.toString();
        }
        return null;
    }

    public boolean isEmpty(){
        return head == null;
    }

    public int size(){
        return size;
    }
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
