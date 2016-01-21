/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Heap;

import Exceptions.EmptyCollectionException;
import Exceptions.NotFoundCollectionException;
import java.util.Iterator;
import ArrayList.ArrayUnorderedList;

/**
 *
 * @author aluno
 */
public class LinkedBinaryTree<T> implements BinaryTreeADT<T> {
    
    protected int count;
    protected BinaryTreeNode<T> root;

    public LinkedBinaryTree() {
        this.count = 0;
        this.root = null;
    }

    public LinkedBinaryTree(T element) {
        this.root = new BinaryTreeNode<T>(element);
        this.count = 1;
    }
    
    @Override
    public T getRoot() throws EmptyCollectionException {
        if(isEmpty())
            throw new EmptyCollectionException("Nao tem root");
            
        return this.root.element;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int size() {
        return this.count;
    }

    @Override
    public boolean contains(T targetElement) throws NotFoundCollectionException {
        return find(targetElement) != null;
    }

    @Override
    public T find(T targetElement) throws NotFoundCollectionException {
        BinaryTreeNode<T> current = findAgain( targetElement, root );
        if( current == null )
            throw new NotFoundCollectionException("There's no current!");

        return (current.element);
    }

    private BinaryTreeNode<T> findAgain(T targetElement, BinaryTreeNode<T> next) {
        if (next == null)
            return null;

        if (next.element.equals(targetElement))
            return next;

        BinaryTreeNode<T> temp = findAgain(targetElement, next.left);

        if (temp == null)
            temp = findAgain(targetElement, next.right);

        return temp;
    }
    
    @Override
    public Iterator<T> iteratorInOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        inorder (root, tempList);

        return tempList.iterator();
    }

    protected void inorder (BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            inorder (node.left, tempList);
            tempList.addRear(node.element);
            inorder (node.right, tempList);
        }
    }
    
    @Override
    public Iterator<T> iteratorPreOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        preorder (root, tempList);

        return tempList.iterator();
    }
    
    protected void preorder (BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            tempList.addRear(node.element);
            preorder (node.left, tempList);
            preorder (node.right, tempList);
        }
    }

    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        postorder (root, tempList);

        return tempList.iterator();
    }
    
    protected void postorder (BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            postorder (node.left, tempList);
            postorder (node.right, tempList);
            tempList.addRear(node.element);
        }
    }

    @Override
    public Iterator<T> iteratorLevelOrder() throws EmptyCollectionException {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        levelorder(root, tempList);
        
        return tempList.iterator();
    }
    
    protected void levelorder (BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) throws EmptyCollectionException {
        CircularArrayQueue nodes = new CircularArrayQueue();
        if(!isEmpty()){
            nodes.enqueue(node);
            while(!nodes.isEmpty()) {
                nodes.dequeue();
                tempList.addRear(node.element);
                if(node.getLeft() != null) {
                    nodes.enqueue(node.getLeft());
                }
                if(node.getRight() != null) {
                    nodes.enqueue(node.getRight());
                }
            }
        }
    }

    
    
    
}
