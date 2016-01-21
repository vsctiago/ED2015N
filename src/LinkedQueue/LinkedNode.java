/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinkedQueue;

/**
 *
 * @author n_fon
 */
public class LinkedNode<T> {
    private LinkedNode<T> next;
    private T element;

    public LinkedNode(LinkedNode<T> next, T element) {
        this.next = next;
        this.element = element;
    }
    public LinkedNode(T element){
        this.element=element;
        this.next = null;
    }

    public LinkedNode<T> getNext() {
        return next;
    }

    public void setNext(LinkedNode<T> next) {
        this.next = next;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }
    
    
}
