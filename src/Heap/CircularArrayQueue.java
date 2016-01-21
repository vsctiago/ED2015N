/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Heap;

import Exceptions.EmptyCollectionException;
import LinkedQueue.QueueADT;

/**
 *
 * @author 8120152
 */
public class CircularArrayQueue<T> implements QueueADT<T> {

    private static final int DEFAULT_SIZE = 100;
    private int front;
    private int rear;
    private int count;
    private T[] queue;

    public CircularArrayQueue() {
        this.front = this.rear = this.count = 0;
        this.queue = (T[])(new Object[DEFAULT_SIZE]);
    }

    public int getFront() {
        return front;
    }

    public void setFront(int front) {
        this.front = front;
    }

    public int getRear() {
        return rear;
    }

    public void setRear(int rear) {
        this.rear = rear;
    }

    public int getCount() {
        return count;
    }
    
    private void expandCapacity() {
        T[] temp = (T[]) (new Object[queue.length * 2]);
        for (int i = 0; i < queue.length; i++) {
            temp[i] = queue[i];
        }
        queue = temp;
    }
    
    @Override
    public void enqueue(T element) {
        if(count == queue.length) {
            expandCapacity();
        } else {
            this.queue[rear] = element;
            rear = (rear + 1) % this.queue.length;
            count++;
        }
    }

    @Override
    public T dequeue() {
        if(isEmpty()) {
            return null;
        } else {
            T oldElement = this.queue[front];
            this.front = (front + 1) % this.queue.length;
            this.count--;
            return oldElement;
        }
    }

    @Override
    public T first() {
        return queue[front];
               
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int size() {
        return count;
    }
    
}
