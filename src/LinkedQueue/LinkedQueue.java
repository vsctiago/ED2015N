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
public class LinkedQueue<T> implements QueueADT<T>{
    private LinkedNode<T> front;
    private LinkedNode<T> rear;
    private int count;

    public LinkedQueue(LinkedNode<T> front, LinkedNode<T> rear) {
        this.front = front;
        this.rear = rear;
        this.front.setNext(rear);
        this.count = 2;
    }
    public LinkedQueue() {
        this.front = null;
        this.rear = null;
        this.count = 0;
    }

    public LinkedNode<T> getFront() {
        return front;
    }

    public void setFront(LinkedNode<T> front) {
        this.front = front;
    }

    public LinkedNode<T> getRear() {
        return rear;
    }

    public void setRear(LinkedNode<T> rear) {
        this.rear = rear;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public void enqueue(T element) {
        if(this.isEmpty()){
            LinkedNode<T> newNode = new LinkedNode<T>(element);
            this.front=newNode;
            this.rear=front;
            this.count++;
        }else {
            LinkedNode<T> newNode = new LinkedNode<T>(element);
            this.rear.setNext(newNode);
            rear = newNode;
            this.count++;
        }
    }

    @Override
    public T dequeue() {
        if(!this.isEmpty()){
            T element = this.front.getElement();
            this.front=this.front.getNext();
            this.count--;
            return element;            
        }
        return null;
    }

    @Override
    public T first() {
        if(!this.isEmpty()){
            return this.front.getElement();
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        if(this.count == 0){
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return this.count;
    }
   
}
