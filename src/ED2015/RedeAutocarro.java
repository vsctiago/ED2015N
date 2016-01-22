/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ED2015;

import Graph.Graph;
import Graph.NetworkADT;
import ED2015.Peso;

/**
 *
 * @author Utilizador
 * @param <T>
 */
public class RedeAutocarro<T> extends Graph<T> implements RedeAutocarroADT<T> {
    
    protected Peso[][] wAdjMatrix;

    public RedeAutocarro(Peso[][] wAdjMatrix) {
        super();
        this.wAdjMatrix = wAdjMatrix;
    }
    
    /******************************************************************
     Returns a string representation of the adjacency matrix. 
   ******************************************************************/
    @Override
   public String toString()
   {
      if (numVertices == 0)
         return "Graph is empty";

      String result = new String("");

      /** Print the adjacency Matrix */
      result += "Adjacency Matrix\n";
      result += "----------------\n";
      result += "index\t";

      for (int i = 0; i < numVertices; i++) 
      {
         result += "" + i;
         if (i < 10)
            result += " ";
      }
      result += "\n\n";

      for (int i = 0; i < numVertices; i++)
      {
         result += "" + i + "\t";
      
         for (int j = 0; j < numVertices; j++)
         {
            if (wAdjMatrix[i][j] < Double.POSITIVE_INFINITY)
               result += "1 ";
            else
               result += "0 ";
         }
         result += "\n";
      }

      /** Print the vertex values */
      result += "\n\nVertex Values";
      result += "\n-------------\n";
      result += "index\tvalue\n\n";

      for (int i = 0; i < numVertices; i++)
      {
         result += "" + i + "\t";
         result += vertices[i].toString() + "\n";
      }

      /** Print the weights of the edges */
      result += "\n\nWeights of Edges";
      result += "\n----------------\n";
      result += "index\tweight\n\n";

      for (int i = 0; i < numVertices; i++)
      {
         for (int j = numVertices-1; j > i; j--)
         {
            if (adjMatrix[i][j] < Double.POSITIVE_INFINITY)
            {
               result += i + " to " + j + "\t";
               result += adjMatrix[i][j] + "\n";
            }
         }
      }

      result += "\n";
      return result;
   }
    
    @Override
    public void addEdge(T vertex1, T vertex2, Peso weight) {
        addEdge(getIndex(vertex1), getIndex(vertex2), weight);
    }
    
    public void addEdge(int index1, int index2, Peso weight) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            wAdjMatrix[index1][index2] = weight;
        }
    }

    @Override
    public Peso shortestPathWeight(T vertex1, T vertex2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
