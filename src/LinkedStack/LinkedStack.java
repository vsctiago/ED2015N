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
public class LinkedStack<T> implements StackADT<T>{

    private Node<T> top;
    int count;
    
    public LinkedStack(){
        this.count =0;
        this.top = new Node();
    }
    public LinkedStack(T firstElement){
        this.top = new Node(firstElement);
    }
    
    
    @Override
    public void push(T element) { //add to head
        Node newNode = new Node(element);
        if(this.isEmpty()){
            this.top = newNode;
            this.count++;
        }else{
            newNode.setNext(this.top);
            this.top = newNode;
            this.count++;
        }
       
    }

    @Override
    public T pop() throws EmptyCollectionException { //return head and delete
        if(!this.isEmpty()){
            T poped = this.top.getElement();
            this.top = this.top.getNext();
            this.count--;
            return poped;
        }else{
            throw new EmptyCollectionException("Stack vazia");
        }
    }

    @Override
    public T peek() throws EmptyCollectionException {
        if(!this.isEmpty()){
            return this.top.getElement();
        }
        throw new EmptyCollectionException("Stack vazia");
    }

    @Override
    public boolean isEmpty() {
        if(this.count==0){
            return true;
        }
        return false;
    }

    @Override
    public int size() throws EmptyCollectionException {
        return this.count;
    }
    
    @Override
    public String toString(){
        String result="";
        if(!this.isEmpty()){
            Node current = this.top;
            try{
            for (int i = 0; i < this.size(); i++) {
                result += current.toString() + "\n\n";
                current = current.getNext();
            }
            }catch(EmptyCollectionException ex){
                ex.getMessage();
            }
        }
        
        
        return result;
    }
}
