/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import ArrayList.ArrayUnorderedList;
import Heap.LinkedHeap;
import LinkedStack.EmptyCollectionException;
import LinkedStack.LinkedStack;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Utilizador
 */
public class Network<T> extends Graph<T> implements NetworkADT<T> {

    protected double[][] wAdjMatrix;

    public Network() {
        super();
        this.wAdjMatrix = (new double[DEFAULT_CAPACITY][DEFAULT_CAPACITY]);
        for (int i = 0; i < this.numVertices; i++) {
            for (int j = 0; j < this.numVertices; j++) {
                wAdjMatrix[i][j] = Double.POSITIVE_INFINITY;
            }
        }
    }

    public double[][] getMatrix(){
        return this.wAdjMatrix;
    }
    
    @Override
    public void addEdge(T vertex1, T vertex2, double weight) {
        addEdge(getIndex(vertex1), getIndex(vertex2), weight);
    }

    public void addEdge(int index1, int index2, double weight) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            wAdjMatrix[index1][index2] = weight;
            wAdjMatrix[index2][index1] = weight;
        }
    }

   /******************************************************************
     Returns the weight of the least weight path in the network.  
     Returns positive infinity if no path is found.
   ******************************************************************/
   public double shortestPathWeight(int startIndex, int targetIndex)
   {
      double result = 0;
      if (!indexIsValid(startIndex) || !indexIsValid(targetIndex))
         return Double.POSITIVE_INFINITY;

      int index1, index2;
      Iterator<Integer> it = iteratorShortestPathIndices(startIndex,
                             targetIndex);

      if (it.hasNext())
         index1 = ((Integer)it.next()).intValue();
      else
         return Double.POSITIVE_INFINITY;

      while (it.hasNext())
      {
         index2 = (it.next()).intValue();
         result += wAdjMatrix[index1][index2];
         index1 = index2;
      }
      
      return result;
   }

   /******************************************************************
     Returns the weight of the least weight path in the network.  
     Returns positive infinity if no path is found.
   ******************************************************************/
   public double shortestPathWeight(T startVertex, T targetVertex)
   {
      return shortestPathWeight(getIndex(startVertex),
                                getIndex(targetVertex));
   }

       /******************************************************************
     Returns an iterator that contains the indices of the vertices 
     that are in the shortest path between the two given vertices.
   ******************************************************************/
   protected Iterator<Integer> iteratorShortestPathIndices
                              (int startIndex, int targetIndex)
   {
      int index;
      double weight;
      int[] predecessor = new int[numVertices];
      LinkedHeap<Double> traversalMinHeap = new LinkedHeap<Double>();
      ArrayUnorderedList<Integer> resultList = 
                                  new ArrayUnorderedList<Integer>();
      LinkedStack<Integer> stack = new LinkedStack<Integer>();

      int[] pathIndex = new int[numVertices];
      double[] pathWeight = new double[numVertices];
      for (int i = 0; i < numVertices; i++)
         pathWeight[i] = Double.POSITIVE_INFINITY;

      boolean[] visited = new boolean[numVertices];
      for (int i = 0; i < numVertices; i++)
         visited[i] = false;

      if (!indexIsValid(startIndex) || !indexIsValid(targetIndex) || 
                       (startIndex == targetIndex) || isEmpty())
         return resultList.iterator();

      pathWeight[startIndex] = 0;
      predecessor[startIndex] = -1;      
      visited[startIndex] = true;
      weight = 0;

      /** Update the pathWeight for each vertex except the 
          startVertex. Notice that all vertices not adjacent 
          to the startVertex  will have a pathWeight of 
          infinity for now. */
      for (int i = 0; i < numVertices; i++)
      {
         if (!visited[i])
         {
            pathWeight[i] = pathWeight[startIndex] + 
                            wAdjMatrix[startIndex][i];
            predecessor[i] = startIndex;
            traversalMinHeap.addElement(new Double(pathWeight[i]));
         }
      }

      do 
      {
         weight = (traversalMinHeap.removeMin()).doubleValue();
         traversalMinHeap.removeAllElements();
         if (weight == Double.POSITIVE_INFINITY)  // no possible path
            return resultList.iterator();
         else 
         {
            index = getIndexOfAdjVertexWithWeightOf(visited, pathWeight, 
                                                    weight);
            visited[index] = true;
         }

         /** Update the pathWeight for each vertex that has has not been 
             visited and is adjacent to the last vertex that was visited.
             Also, add each unvisited vertex to the heap. */
         for (int i = 0; i < numVertices; i++)
         {
            if (!visited[i])
            {
               if((wAdjMatrix[index][i] < Double.POSITIVE_INFINITY) && (pathWeight[index] + wAdjMatrix[index][i]) < pathWeight[i])
               {
                  pathWeight[i] = pathWeight[index] + wAdjMatrix[index][i];
                  predecessor[i] = index;
               }        
               traversalMinHeap.addElement(new Double(pathWeight[i]));
            }
         }
      } while (!traversalMinHeap.isEmpty() && !visited[targetIndex]);

      index = targetIndex;
      stack.push(new Integer(index));
      do
      {
         index = predecessor[index];
         stack.push(new Integer(index));
      } while (index != startIndex);
      
      while (!stack.isEmpty())
          try {
              resultList.addRear((stack.pop()));
          } catch (EmptyCollectionException ex) {
          }

      return resultList.iterator();
   }

   /******************************************************************
     Returns the index of the the vertex that that is adjacent to
     the vertex with the given index and also has a pathWeight equal
     to weight.
   ******************************************************************/
   protected int getIndexOfAdjVertexWithWeightOf(boolean[] visited, double[] pathWeight, double weight)
   {
      for (int i = 0; i < numVertices; i++)
         if ((pathWeight[i] == weight) && !visited[i])
            for (int j = 0; j < numVertices; j++)
               if ((wAdjMatrix[i][j] < Double.POSITIVE_INFINITY) && visited[j])
                  return i;

      return -1;  // should never get to here
   }

   /******************************************************************
     Returns an iterator that contains the shortest path between
     the two vertices.
   ******************************************************************/
   public Iterator<T> iteratorShortestPath(int startIndex, int targetIndex)
   {
      ArrayUnorderedList templist = new ArrayUnorderedList();
      if (!indexIsValid(startIndex) || !indexIsValid(targetIndex))
         return templist.iterator();

      Iterator<Integer> it = iteratorShortestPathIndices(startIndex, 
                             targetIndex);      
      while (it.hasNext())
         templist.addRear(vertices[(it.next()).intValue()]);
      return templist.iterator();
   }

   /******************************************************************
     Returns an iterator that contains the shortest path between
     the two vertices.
   ******************************************************************/
   public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex)
   {
      return iteratorShortestPath(getIndex(startVertex), 
                                  getIndex(targetVertex));
   }

    /**
     * Returns a minimum spanning tree of the network.
     *
     * @return a minimum spanning tree of the network
     */
    public Network mstNetwork() {
        int x, y;
        int index;
        double weight;
        int[] edge = new int[2];
        LinkedHeap<Double> minHeap = new LinkedHeap<Double>();
        Network<T> resultGraph = new Network<T>();
        if (isEmpty() || !isConnected()) {
            return resultGraph;
        }
        resultGraph.wAdjMatrix = new double[numVertices][numVertices];
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                resultGraph.wAdjMatrix[i][j] = Double.POSITIVE_INFINITY;
            }
        }
        resultGraph.vertices = (T[]) (new Object[numVertices]);
        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        edge[0] = 0;
        resultGraph.vertices[0] = this.vertices[0];
        resultGraph.numVertices++;
        visited[0] = true;
        /**
         * Add all edges, which are adjacent to the starting vertex, to the heap
         */
        for (int i = 0; i < numVertices; i++) {
            minHeap.addElement(new Double(wAdjMatrix[0][i]));
        }
        while ((resultGraph.size() < this.size()) && !minHeap.isEmpty()) {
            /**
             * Get the edge with the smallest weight that has exactly one vertex
             * already in the resultGraph
             */
            do {
                weight = (minHeap.removeMin()).doubleValue();
                edge = getEdgeWithWeightOf(weight, visited);
            } while (!indexIsValid(edge[0]) || !indexIsValid(edge[1]));
            x = edge[0];
            y = edge[1];
            if (!visited[x]) {
                index = x;
            } else {
                index = y;
            }
            /**
             * Add the new edge and vertex to the resultGraph
             */
            resultGraph.vertices[index] = this.vertices[index];
            visited[index] = true;
            resultGraph.numVertices++;
            resultGraph.adjMatrix[x][y] = this.adjMatrix[x][y];
            resultGraph.adjMatrix[y][x] = this.adjMatrix[y][x];
            /**
             * Add all edges, that are adjacent to the newly added vertex, to
             * the heap
             */
            for (int i = 0; i < numVertices; i++) {
                if (!visited[i] && (this.wAdjMatrix[i][index]
                        < Double.POSITIVE_INFINITY)) {
                    edge[0] = index;
                    edge[1] = i;
                    minHeap.addElement(new Double(wAdjMatrix[index][i]));
                }
            }
        }
        return resultGraph;
    }

    protected int[] getEdgeWithWeightOf(double weight, boolean[] visited) {
        int[] edge = new int[2];
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if ((wAdjMatrix[i][j] == weight) && (visited[i] ^ visited[j])) {
                    edge[0] = i;
                    edge[1] = j;
                    return edge;
                }
            }
        }

        /**
         * Will only get to here if a valid edge is not found
         */
        edge[0] = -1;
        edge[1] = -1;
        return edge;
    }
}
