/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArrayList;

/**
 *
 * @author n_fon
 */
public class ArrayUnorderedList<T> extends ArrayList<T> implements UnorderedListADT<T> {

    public ArrayUnorderedList(int size) {
        super(size);
    }

    public ArrayUnorderedList() {
        super();
    }

    @Override
    public void addFront(T element) {
        if (this.rear < this.collection.length) {
            for (int i = this.rear; i > 0; i--) {
                this.collection[i] = this.collection[i - 1];
            }
            this.collection[0] = element;
            this.rear++;
        }
    }

    @Override
    public void addRear(T element) {
        if (this.rear < this.collection.length) {
            this.collection[this.rear]=element;
            this.rear++;
        }
    }

    @Override
    public void addAfter(T element, T target) {
        for (int i = 0; i <this.rear; i++) {
            if(this.collection[i].equals(target)){
                for (int j = this.rear; j > i+2; j--) {
                    this.collection[j]=this.collection[j-1];
                }
                this.collection[i+1]=element;
                this.rear++;
            }
        }
    }

}
