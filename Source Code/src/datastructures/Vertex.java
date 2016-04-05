// Vertex.java
// Kaê Angeli Coutinho
// MIT license

package datastructures;

import java.io.Serializable;
import java.util.Objects;

public class Vertex extends Object implements Serializable
{
    // Constants
    
    public static final Boolean BLACK_COLOR = true;
    public static final Boolean WHITE_COLOR = false;
    
    // Properties
    
    private String name;
    private LinkedList<Edge> adjacentVertices;
    private Boolean color;
    
    // Constructors
    
    public Vertex()
    {
        this(null);
    }
    
    public Vertex(String name)
    {
        this(name,new LinkedList<>(),Vertex.WHITE_COLOR);
    }
    
    public Vertex(String name, LinkedList<Edge> adjacentVertices, Boolean color)
    {
        super();
        this.name = name;
        this.adjacentVertices = adjacentVertices;
        this.color = color;
    }
    
    public Vertex(String name, Edge ... edges)
    {
        this(name);
        for(Integer index = 0; index < edges.length; index++)
        {
            this.adjacentVertices.addLast(edges[index]);
        }
    }
    
    // Instance Methods
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setAdjacentVertices(LinkedList<Edge> adjacentVertices)
    {
        this.adjacentVertices = adjacentVertices;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public void setColor(Boolean color)
    {
        this.color = color;
    }
   
    public Boolean getColor()
    {
        return this.color;
    }
    
    public Boolean isBlack()
    {
        return (Objects.equals(this.color,Vertex.BLACK_COLOR));
    }
    
    public Boolean isIdentical(Vertex vertex)
    {
        return (this.name.equals(vertex.getName()));
    }
    
    public LinkedList<Edge> getAdjacentVertices()
    {
        return this.adjacentVertices;
    }
    
    public void addAdjacentVertex(Vertex vertex)
    {
        this.adjacentVertices.addLast(new Edge(this,vertex));
    }
    
    public void clear(Boolean fullCleanUp)
    {
        if(fullCleanUp)
        {
            this.name = null;
        }
        this.adjacentVertices.clear();
    }
    
    @Override
    public String toString()
    {
        return "Vertex" +
               "\n{" + 
               "\n\tname: " + this.name +
               "\n\tadjacentVertices: " + this.adjacentVertices.printToString() +
               "\n\tcolor: " + ((this.color) ? "black" : "white") +
               "\n}";
    }
}
