package DataStructures;

public class SinglyLinkedNode<T> extends Node<T>{

    SinglyLinkedNode<T> next;

    public SinglyLinkedNode() {
        this.next = null;
    }

    public SinglyLinkedNode(T object) {
        super(object);
        this.next = null;
    }

    public SinglyLinkedNode(T object, SinglyLinkedNode<T> next) {
        super(object);
        this.next = next;
    }

    public SinglyLinkedNode<T> getNext() {
        return this.next;
    }

    public void setNext(SinglyLinkedNode<T> next) {
        this.next = next;
    }

}
