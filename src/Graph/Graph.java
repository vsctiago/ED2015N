/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import ArrayList.UnorderedArrayList;
import LinkedQueue.LinkedQueue;
import LinkedStack.EmptyCollectionException;
import LinkedStack.LinkedStack;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author n_fon
 */
public class Graph<T> implements GraphADT<T> {

    protected final int DEFAULT_CAPACITY = 10;
    protected int numVertices; // number of vertices in the graph
    protected boolean[][] adjMatrix; // adjacency matrix
    protected T[] vertices; // values of vertices
    
    /**
     * Creates an empty graph.
     */
    public Graph() {
        numVertices = 0;
        this.adjMatrix = new boolean[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    @Override
    public void addVertex(T vertex) {
        if (numVertices == vertices.length) {
            expandCapacity();
        }
        vertices[numVertices] = vertex;
        for (int i = 0; i <= numVertices; i++) {
            adjMatrix[numVertices][i] = false;
            adjMatrix[i][numVertices] = false;
        }
        numVertices++;
    }

    public void expandCapacity() {
        this.numVertices += this.DEFAULT_CAPACITY;
        T[] newVertices = (T[]) (new Object[this.numVertices]);
        boolean[][] newMatrix = new boolean[this.numVertices][this.numVertices];
        for (int i = 0; i < this.adjMatrix.length; i++) {
            newVertices[i] = this.vertices[i];
            for (int j = 0; j < this.adjMatrix.length; j++) {
                newMatrix[i][j] = this.adjMatrix[i][j];
            }
        }
        this.vertices = newVertices;
        this.adjMatrix = newMatrix;
    }

    @Override
    public void removeVertex(T vertex) {
        //find vertex
        int index = getIndex(vertex);
        if (index >= 0) {
            for (int i = index; i < this.numVertices; i++) {
                this.vertices[i] = this.vertices[i + 1];
            }
            for (int i = index; i < this.numVertices; i++) {
                for (int j = 0; j <= this.numVertices; j++) {
                    this.adjMatrix[i][j] = this.adjMatrix[i + 1][j];
                }
            }
            for (int i = index; i < this.numVertices; i++) {
                for (int j = 0; j < this.numVertices; j++) {
                    this.adjMatrix[j][i] = this.adjMatrix[j][i + 1];
                }
            }
        }
    }

    @Override
    public void addEdge(T vertex1, T vertex2) {
        addEdge(getIndex(vertex1), getIndex(vertex2));
    }

    public int getIndex(T vertex) {
        for (int i = 0; i < this.vertices.length; i++) {
            if (vertex.equals(this.vertices[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Inserts an edge between two vertices of the graph.
     *
     * @param index1 the first index
     * @param index2 the second index
     */
    public void addEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = true;
            adjMatrix[index2][index1] = true;
        }
    }

    public boolean indexIsValid(int index) {
        if (index >= 0 && index < this.adjMatrix.length) {
            return true;
        }
        return false;
    }

    @Override
    public void removeEdge(T vertex1, T vertex2) {
        removeEdge(getIndex(vertex1), getIndex(vertex2));
    }

    protected void removeEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = false;
            adjMatrix[index2][index1] = false;
        }
    }

    @Override
    public Iterator iteratorBFS(T startVertex) {
        int startIndex = getIndex(startVertex);
        Integer x;
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<Integer>();
        UnorderedArrayList<T> resultList = new UnorderedArrayList<T>();
        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }
        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalQueue.enqueue(new Integer(startIndex));
        visited[startIndex] = true;
        while (!traversalQueue.isEmpty()) {
            x = traversalQueue.dequeue();
            resultList.addRear(vertices[x.intValue()]);

            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[x.intValue()][i] && !visited[i]) {
                    traversalQueue.enqueue(new Integer(i));
                    visited[i] = true;
                }
            }
        }
        return resultList.iterator();

    }


    @Override
    public Iterator iteratorDFS(T startVertex) {
        int startIndex = getIndex(startVertex);
        
        Integer x = null;
        boolean found;
        LinkedStack<Integer> traversalStack = new LinkedStack<Integer>();
        UnorderedArrayList<T> resultList = new UnorderedArrayList<T>();
        boolean[] visited = new boolean[numVertices];
        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalStack.push(new Integer(startIndex));
        resultList.addRear(vertices[startIndex]);
        visited[startIndex] = true;
        
        while (!traversalStack.isEmpty()) {
            try {
                x = traversalStack.peek();
            } catch (EmptyCollectionException ex) {
                Logger.getLogger(Graph.class.getName()).log(Level.SEVERE, null, ex);
            }
            found = false;
            /**
             * Find a vertex adjacent to x that has not been visited and push it
             * on the stack
             */
            for (int i = 0; (i < numVertices) && !found; i++) {
                if (adjMatrix[x.intValue()][i] && !visited[i]) {
                    traversalStack.push(new Integer(i));
                    resultList.addRear(vertices[i]);
                    visited[i] = true;
                    found = true;
                }
            }
            if (!found && !traversalStack.isEmpty()) {
                try {
                    traversalStack.pop();
                } catch (EmptyCollectionException ex) {
                    Logger.getLogger(Graph.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return resultList.iterator();
    }

    @Override
    public Iterator iteratorShortestPath(T startVertex, T targetVertex) {
        int pesos[][] = new int[this.numVertices][this.numVertices];
        
        
        
        
        
        
        return null;
    }

    @Override
    public boolean isEmpty() {
        if (this.size() <= 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isConnected() { //É CONEXO??
        int count=0;
        if(this.isEmpty()){
           return false; 
        }
        Iterator<T> it = iteratorDFS(this.vertices[0]);
        while(it.hasNext()){
            it.next();
            count++;
        }
        if(count==this.numVertices){
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return this.numVertices;
    }

}
