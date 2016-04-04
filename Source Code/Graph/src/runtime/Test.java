// Test.java
// Kaê Angeli Coutinho
// MIT license

package runtime;

import datastructures.Graph;
import datastructures.Vertex;

public class Test extends Object
{
    public static void main(String [] args) throws Exception
    {
        // Initialize graph vertices
        
        Vertex vertexA = new Vertex("A");
        Vertex vertexB = new Vertex("B");
        Vertex vertexC = new Vertex("C");
        Vertex vertexD = new Vertex("D");
        
        // Initialize graphs
        
        Graph graph = new Graph("G",'G',vertexA,vertexB,vertexC,vertexD);
        Graph operationGraph;

        // Set all vertices' edges
        
        vertexA.addAdjacentVertex(vertexB);
        vertexA.addAdjacentVertex(vertexC);
        vertexB.addAdjacentVertex(vertexC);
        vertexC.addAdjacentVertex(vertexD);
        
        // Shows graph's info
        
        graph.printInfo();
        
        // Calculate a subgraph by removing a set of vertices and print its info
        
        operationGraph = graph.getSubraphByRemovingSet(vertexA,vertexD);
        operationGraph.printInfo();
        
        // Calculate a subgraph by placing a set of vertices and print its info
        
        operationGraph = graph.getSetsSubgraph(vertexA,vertexD);
        operationGraph.printInfo();
        
        // Calculate the complement graph of the original graph and print its info
        
        operationGraph = graph.getComplement();
        operationGraph.printInfo();
        
        // Calculate the complete graph of the original graph and print its info
        
        operationGraph = graph.getCompleteGraph();
        operationGraph.printInfo();
        
        // Clear all vertices of a graph and print it
        
        graph.clear(false);
        graph.printInfo();
        
        // Clear all vertices of a graph and print it
        
        operationGraph.clear(true);
        operationGraph.printInfo();
    }
}
