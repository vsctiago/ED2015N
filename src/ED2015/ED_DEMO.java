/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ED2015;

import Graph.Network;
import java.util.Iterator;

/**
 *
 * @author n_fon
 */
public class ED_DEMO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Network<String> a = new Network();
        a.addVertex("a");
        a.addVertex("b");
        a.addVertex("c");
        a.addVertex("d");
        a.addVertex("e");
        a.addVertex("f");
        a.addEdge("a", "b", 2);
        a.addEdge("b", "c", 5);
        a.addEdge("c", "f", 3);
        a.addEdge("a", "d", 2);
        a.addEdge("d", "e", 4);
        a.addEdge("e", "c", 2);
        System.out.println(a.toString());
        //System.out.println(a.toString());
        
        Iterator it = a.iteratorShortestPath("d", "f");
        
        while(it.hasNext()){
            System.out.println(it.next());
        }

    }
    
}
