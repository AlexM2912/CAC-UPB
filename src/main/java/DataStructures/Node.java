package DataStructures;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Node<T> implements NodeInterface<T> {

    T object;

    public Node(T object){
        this.object = object;
    }

    public Node(){
        this.object = null;
    }

    @Override
    public boolean setObject(T object) {
        if (object != null){
            try {
                this.object = object;
                return true;
            }catch (Exception e) {
                Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
            }
        }
        return false;
    }

    @Override
    public T getObject() {
        return this.object;
    }

    @Override
    public boolean isEquals(Object object) {
        try {
            return this.object.toString().equals(object.toString());
        }catch (Exception e){
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        }
        return false;
    }

    @Override
    public NodeInterface<T> getClone() {
        try {
            if (object != null) {
                return (NodeInterface<T>) this.getClone();
            }
        } catch (Exception e){
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        }
        return null;
    }

    @Override
    public String ToString() {
        return "Node[object=" + object + "]";
    }

}
