/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinkedStack;

/**
 *
 * @author n_fon
 */
public class Node<T> {
    private T element;
    private Node next;
    
    public Node(T element){
        this.element = element;
        this.next = null;
    }
    
    public Node(){
        this.element = null;
        this.next = null;        
    }

    public Node(T element, Node next) {
        this.element = element;
        this.next = next;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
    
    public String toString(){
        return this.element.toString();
    }
    
}
