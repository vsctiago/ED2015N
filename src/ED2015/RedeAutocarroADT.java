/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ED2015;

import Graph.GraphADT;

/**
 *
 * @author Utilizador
 */
public interface RedeAutocarroADT<T> extends GraphADT<T> {
    
    public void addEdge(T vertex1, T vertex2, Peso weight);
    
    public Peso shortestPathWeight(T vertex1, T vertex2);
    
}
