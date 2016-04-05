// Edge.java
// Kaê Angeli Coutinho
// MIT license

package datastructures;

import java.io.Serializable;

public class Edge extends Object implements Serializable
{
    // Properties
    
    private Vertex vertexA;
    private Vertex vertexB;
    
    // Constructors
    
    public Edge()
    {
        this(null,null);
    }
    
    public Edge(Vertex vertexA, Vertex vertexB)
    {
        super();
        this.vertexA = vertexA;
        this.vertexB = vertexB;
    }
    
    // Instance Methods
    
    public void setVertexA(Vertex vertexA)
    {
        this.vertexA = vertexA;
    }
    
    public void setVertexB(Vertex vertexB)
    {
        this.vertexB = vertexB;
    }
    
    public Vertex getVertexA()
    {
        return this.vertexA;
    }
    
    public Vertex getVertexB()
    {
        return this.vertexB;
    }
    
    public Boolean isEdgeIdentical(Edge edge)    
    {
        return ((this.vertexA.getName().equals(edge.vertexA.getName()) && this.vertexB.getName().equals(edge.vertexB.getName())) || (this.vertexA.getName().equals(edge.vertexB.getName()) && this.vertexB.getName().equals(edge.vertexA.getName())));
    }
    
    public Boolean hasVertex(Vertex vertex)
    {
        return ((this.vertexA.getName().equals(vertex.getName())) || (this.vertexB.getName().equals(vertex.getName())) || (this.vertexA.getName().equals(vertex.getName()) && this.vertexB.getName().equals(vertex.getName())));
    }
    
    public Boolean isLoop()
    {
        return (this.vertexA.getName().equals(this.vertexB.getName()));
    }
    
    public String getName()
    {
        return this.vertexA.getName().concat(this.vertexB.getName());
    }
    
    public Vertex getOppositeVertex(Vertex vertex)
    {
        if(!this.hasVertex(vertex))
        {
            return null;
        }
        return ((this.vertexA.getName().equals(vertex.getName())) ? this.vertexB : this.vertexA);
    }
    
    public void clear()
    {
        this.vertexA = null;
        this.vertexB = null;
    }
    
    @Override
    public String toString()
    {
        return "Edge" +
               "\n{" + 
               "\n\tvertexA: " + this.vertexA.getName() +
               "\n\tvertexB: " + this.vertexB.getName() +
               "\n}";
    }
}
