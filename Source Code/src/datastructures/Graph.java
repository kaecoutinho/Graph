// Graph.java
// Kaê Angeli Coutinho
// MIT license

package datastructures;

import java.io.Serializable;
import java.util.Objects;
import org.apache.commons.lang.SerializationUtils;

public class Graph extends Object implements Cloneable, Serializable
{
    // Constants
    
    public static final Integer NOT_FOUND = -1;
    
    // Properties
    
    private String name;
    private Character symbol;
    private LinkedList<Vertex> vertices;

    // Constructors
    
    public Graph()
    {
        this(null,null);
    }
    
    public Graph(String name, Character symbol)
    {
        this(name,symbol,new LinkedList<>());
    }
    
    public Graph(String name, Character symbol, LinkedList<Vertex> vertices)
    {
        super();
        this.name = name;
        this.symbol = symbol;
        this.vertices = vertices;
    }
    
    public Graph(String name, Character symbol, Vertex ... vertices)
    {
        this(name,symbol);
        for(Integer index = 0; index < vertices.length; index++)
        {
            if(!this.hasVertex(vertices[index]))
            {
                this.vertices.addLast(vertices[index]);
            }
        }
    }
    
    // Instance Methods
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public void setSymbol(Character symbol)
    {
        this.symbol = symbol;
    }
    
    public Character getSymbol()
    {
        return this.symbol;
    }
    
    public void setVertices(LinkedList<Vertex> vertices)
    {
        this.vertices = vertices;
    }
    
    public LinkedList<Vertex> getVertices()
    {
        return this.vertices;
    }
    
    public Integer getVerticesNumber()
    {
        return this.vertices.size();
    }
    
    public Integer getEdgesNumber()
    {
        return this.getEdges().size();
    }
    
    public Vertex getVertex(String name)
    {
        Vertex result = null;
        for(Vertex vertex : this.vertices)
        {
            if(vertex.getName().equals(name))
            {
                result = vertex;
            }
        }
        return result;
    }
    
    public LinkedList<Edge> getEdges()
    {
        LinkedList<Edge> edges = new LinkedList<>();
        for(Vertex vertex : this.vertices)
        {
            for(Edge edge : vertex.getAdjacentVertices())
            {
                edges.addLast(edge);
            }
        }
        return edges;
    }
    
    public Integer getVertexDegree(Vertex vertex)
    {
        Integer degree = 0;
        for(Edge edge : this.getEdges())
        {
            if(edge.hasVertex(vertex))
            {
                degree += ((edge.isLoop()) ? 2 : 1);
            }
        }
        return degree;
    }
    
    public Graph getCompleteGraph() throws Exception
    {
        Graph graph = (Graph)SerializationUtils.clone(this);
        if(!this.isComplete())
        {
            LinkedList<Edge> edges;
            Edge current, edge;
            Boolean removed;
            graph.clearEdges();
            for(Integer currentIndex = 0; currentIndex < graph.getVertices().size(); currentIndex++)
            {
                for(Integer index = 0; index < graph.getVertices().size(); index++)
                {
                    if(!Objects.equals(currentIndex,index))
                    {
                        graph.getVertices().getElement(currentIndex).addAdjacentVertex(graph.getVertices().getElement(index));
                    }
                }
            }
            edges = graph.getEdges();
            for(Integer currentIndex = 0; currentIndex < edges.size(); currentIndex++)
            {   
                current = edges.getElement(currentIndex);
                removed = false;
                for(Integer index = 0; index < edges.size(); index++)
                {
                    edge = edges.getElement(index);
                    if(current != edge)
                    {
                        if(current.isEdgeIdentical(edge))
                        {
                            edges.removeElement(edge);
                            index--;
                            removed = true;
                        }
                    }
                }
                if(removed)
                {
                    currentIndex--;
                }
            }
            graph.clearEdges();
            for(Edge currentEdge : edges)
            {
                Vertex vertexA = graph.getVertex(currentEdge.getVertexA().getName());
                Vertex vertexB = graph.getVertex(currentEdge.getVertexB().getName());
                if(vertexA != null && vertexB != null)
                {
                    vertexA.addAdjacentVertex(vertexB);
                }
            }
        }
        graph.setName(this.name + " complete");
        return graph;
    }
    
    public Graph getComplement() throws Exception
    {
        Graph graph = this.getCompleteGraph();
        for(Edge edge : this.getEdges())
        {
            graph.removeEdge(edge);
        }
        graph.setName(this.name + " complement");
        return graph;
    }
    
    public Graph getSubraphByRemovingSet(Vertex ... vertices) throws Exception
    {
        LinkedList<Vertex> aux = new LinkedList<>();
        for(Integer index = 0; index < vertices.length; index++)
        {
            aux.addLast(vertices[index]);
        }
        return this.getSubraphByRemovingSet(aux);
    }
    
    public Graph getSubraphByRemovingSet(LinkedList<Vertex> vertices) throws Exception
    {
        Graph subgraph = (Graph)SerializationUtils.clone(this);
        String aux = "{";
        for(Vertex vertex : vertices)
        {
            aux += vertex.getName() + ",";
        }
        aux = aux.substring(0,aux.length() - 1).concat("}");
        subgraph.setName(subgraph.getName() + "/" + aux);
        for(Integer index = 0; index < vertices.size(); index++)
        {
            for(Vertex vertex : subgraph.getVertices())
            {
                if(vertices.getElement(index).getName().equals(vertex.getName()))
                {
                    subgraph.removeVertex(vertex);
                }
            }
        }
        return subgraph;
    }
    
    public Graph getSetsSubgraph(Vertex ... vertices) throws Exception
    {
        LinkedList<Vertex> aux = new LinkedList<>();
        for(Integer index = 0; index < vertices.length; index++)
        {
            aux.addLast(vertices[index]);
        }
        return this.getSetsSubgraph(aux);
    }
    
    public Graph getSetsSubgraph(LinkedList<Vertex> vertices) throws Exception
    {
        Graph subgraph = (Graph)SerializationUtils.clone(this);
        String aux = "[{";
        for(Vertex vertex : vertices)
        {
            aux += vertex.getName() + ",";
        }
        aux = aux.substring(0,aux.length() - 1).concat("}]");
        for(Integer index = 0; index < vertices.size(); index++)
        {
            Vertex vertex = subgraph.getVertex(vertices.getElement(index).getName());
            if(vertex != null)
            {
                subgraph.removeVertex(vertex);
            }
        }
        subgraph = this.getSubraphByRemovingSet(subgraph.getVertices());
        subgraph.setName(this.getName() + aux);
        return subgraph;
    }
    
    public Integer getMaximumDegree() throws Exception
    {
        Integer result = 0;
        if(!this.isEmpty())
        {
            Integer aux;
            result = this.getVertexDegree(this.vertices.getFirstElement());
            for(Vertex vertex : this.vertices)
            {
                aux = this.getVertexDegree(vertex);
                if(aux >= result)
                {
                    result = aux;
                }
            }
        }
        return result;
    }
    
    public Integer getMinimumDegree() throws Exception
    {
        Integer result = 0;
        if(!this.isEmpty())
        {
            Integer aux;
            result = this.getVertexDegree(this.vertices.getFirstElement());
            for(Vertex vertex : this.vertices)
            {
                aux = this.getVertexDegree(vertex);
                if(aux <= result)
                {
                    result = aux;
                }
            }
        }
        return result;
    }
    
    public Boolean addVertex(Vertex vertex)
    {
        Boolean result = false;
        if(!this.hasVertex(vertex))
        {
            this.vertices.addLast(vertex);
            result = true;
        }
        return result;
    }
    
    public Boolean removeVertex(Vertex vertex) throws Exception
    {
        Boolean result = false;
        if(this.hasVertex(vertex))
        {
            for(Vertex aux : this.vertices)
            {
                for(Edge edge : aux.getAdjacentVertices())
                {
                    if(edge.hasVertex(vertex))
                    {
                        aux.getAdjacentVertices().removeElement(edge);
                    }
                }
            }
            this.vertices.removeElement(vertex);
            result = true;
        }
        return result;
    }
    
    public Boolean removeVertex(String name) throws Exception
    {
        Boolean result = false;
        Vertex vertex = this.getVertex(name);
        if(vertex != null)
        {
            if(this.hasVertex(vertex))
            {
                for(Vertex aux : this.vertices)
                {
                    for(Edge edge : aux.getAdjacentVertices())
                    {
                        if(edge.hasVertex(vertex))
                        {
                            aux.getAdjacentVertices().removeElement(edge);
                        }
                    }
                }
                this.vertices.removeElement(vertex);
                result = true;
            }
        }
        return result;
    }
    
    public Boolean removeEdge(Edge edge) throws Exception
    {
        Boolean result = false;
        if(this.hasEdge(edge))
        {
            for(Vertex aux : this.vertices)
            {
                for(Edge currentEdge : aux.getAdjacentVertices())
                {
                    if(edge.isEdgeIdentical(currentEdge))
                    {
                        aux.getAdjacentVertices().removeElement(currentEdge);
                        result = true;
                    }
                }
            }
        }
        return result;
    }
    
    public void printInfo() throws Exception
    {
        String aux;
        Integer verticesNumber = this.getVerticesNumber();
        Integer edgesNumber = this.getEdgesNumber();
        System.out.println("Graph " + this.name);
        System.out.println("\tSimple: " + ((this.isSimple()) ? "Yes" : "No"));
        System.out.println("\tRegular: " + ((this.isRegular()) ? ("Yes (" + this.getMaximumDegree() + "-regular)") : "No"));
        System.out.println("\tComplete: " + ((this.isComplete()) ? ("Yes (K" + this.getVerticesNumber()+ ")") : "No"));
        System.out.println("\tCycle: " + ((this.isCycle()) ? "Yes" : "No"));
        System.out.println("\tConnected: " + ((this.isConnected()) ? "Yes" : "No"));
        System.out.println("\tTree: " + ((this.isTree()) ? "Yes" : "No"));
        aux = "\t" + this.symbol + "V = {";
        for(Vertex vertex : vertices)
        {
            aux += vertex.getName() + ",";
        }
        aux = ((verticesNumber != 0) ? aux.substring(0,aux.length() - 1).concat("}") : aux.concat("}"));
        System.out.println(aux);
        aux = "\t" + this.symbol + "E = {";
        for(Edge edge : this.getEdges())
        {
            aux += edge.getName() + ",";
        }
        aux = ((edgesNumber != 0) ? aux.substring(0,aux.length() - 1).concat("}") : aux.concat("}"));
        System.out.println(aux);
        aux = "\t|" + this.symbol + "V| = " + this.getVerticesNumber();
        System.out.println(aux);
        aux = "\t|" + this.symbol + "E| = " + this.getEdgesNumber();
        System.out.println(aux);
        if(verticesNumber > 0)
        {
            System.out.println("\tΔ(" + this.symbol + ") = " + this.getMaximumDegree());
            System.out.println("\tδ(" + this.symbol + ") = " + this.getMinimumDegree());
            System.out.println("\tVertices' degrees");
            for(Vertex vertex : vertices)
            {
                aux = "\t\tρ(" + vertex.getName() + ") = " + this.getVertexDegree(vertex);
                System.out.println(aux);
            }
        }
    }
    
    private Boolean hasVertex(Vertex vertex)
    {
        Boolean result = false;
        for(Vertex aux : this.vertices)
        {
            if(aux.getName().equals(vertex.getName()))
            {
                result = true;
            }
        }
        return result;
    }
    
    public Boolean hasEdge(Edge edge)
    {
        Boolean result = false;
        for(Edge aux : this.getEdges())
        {
            if(aux.isEdgeIdentical(edge))
            {
                result = true;
            }
        }
        return result;
    }
    
    public void clear(Boolean fullCleanUp)
    {
        if(fullCleanUp)
        {
            this.name = null;
            this.symbol = null;
        }
        this.clearEdges();
        this.vertices.clear();
    }
    
    public void clearColors() throws Exception
    {
        for(Integer index = 0; index < this.vertices.size(); index++)
        {
            this.vertices.getElement(index).setColor(Vertex.WHITE_COLOR);
        }
    }
    
    public void clearEdges()
    {
        for(Vertex vertex : this.vertices)
        {
            vertex.getAdjacentVertices().clear();
        }
    }
    
    public Boolean hasLoops()
    {
        Boolean result = false;
        for(Edge edge : this.getEdges())
        {
            if(edge.isLoop())
            {
                result = true;
                break;
            }
        }
        return result;
    }
    
    public Boolean hasMultipleEdges() throws Exception
    {
        Boolean result = false;
        Edge current;
        LinkedList<Edge> edges = this.getEdges();
        for(Integer index = 0; index < edges.size(); index++)
        {
            current = edges.getElement(index);
            for(Integer aux = 0; aux < edges.size(); aux++)
            {
                if((!Objects.equals(aux,index)) && (current.isEdgeIdentical(edges.getElement(aux))))
                {
                    result = true;
                    break;
                }
            }
            if(result)
            {
                break;
            }
        }
        return result;
    }
    
    public Boolean isSimple() throws Exception
    {
        return (!this.hasLoops() && !this.hasMultipleEdges());
    }
    
    public Boolean areVerticesBlack() throws Exception
    {
        Boolean result = true;
        for(Integer index = 0; index < this.vertices.size(); index++)
        {
            if(!this.vertices.getElement(index).isBlack())
            {
                result = false;
                break;
            }
        }
        return result;
    }
    
    public Boolean isConnected() throws Exception
    {
        Boolean result = false;
        Queue<Vertex> aux = new Queue<>();
        this.clearColors();
        if(!this.isEmpty())
        {
            aux.push(this.vertices.getFirstElement());
            this.vertices.getFirstElement().setColor(Vertex.BLACK_COLOR);
            while(!aux.isEmpty())
            {
                Vertex vertex = aux.front();
                for(Edge edge : this.getVertexEdges(vertex))
                {
                    Vertex oppositeVertex = edge.getOppositeVertex(vertex);
                    if(oppositeVertex != null)
                    {
                        if(!oppositeVertex.isBlack())
                        {
                            aux.push(oppositeVertex);
                            oppositeVertex.setColor(Vertex.BLACK_COLOR);
                        }
                    }
                    else
                    {
                        throw new Exception("Null vertex");
                    }
                }
                aux.pop();
            }
            result = this.areVerticesBlack();
            this.clearColors();
        }
        return result;
    }
    
    public Boolean isTree() throws Exception
    {
        return (this.isConnected() && this.isSimple() && this.getEdgesNumber() == (this.getVerticesNumber() - 1) && !this.isEmpty());
    }
    
    public Boolean isCycle()
    {
        Boolean result = true;
        if(!this.isEmpty())
        {
            for(Vertex vertex : this.vertices)
            {
                if(this.getVertexDegree(vertex) != 2)
                {
                    result = false;
                    break;
                }
            }
        }
        else
        {
            result = false;
        }
        return result;
    }
    
    public Boolean isRegular() throws Exception
    {
        Boolean result = true;
        if(!this.isEmpty())
        {
            Integer factor = this.getVertexDegree(this.vertices.getFirstElement());
            for(Vertex vertex : this.vertices)
            {
                if(!Objects.equals(this.getVertexDegree(vertex),factor))
                {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
    
    public Boolean isComplete() throws Exception
    {
        return ((this.isRegular() && (this.getMaximumDegree() == this.getVerticesNumber() - 1) && this.isSimple()) || this.isEmpty());
    }
    
    public LinkedList<Edge> getVertexEdges(Vertex vertex) throws Exception
    {
        LinkedList<Edge> vertexEdges = new LinkedList<>();
        LinkedList<Edge> edges = this.getEdges();
        for(Integer index = 0; index < edges.size(); index++)
        {
            Edge edge = edges.getElement(index);
            if(edge.hasVertex(vertex))
            {
                vertexEdges.addLast(edge);
            }
        }
        return vertexEdges;
    }
    
    public Integer calculateVertexDistance(Vertex source, Vertex destination) throws Exception
    {
        Integer distance = Graph.NOT_FOUND;
        if(this.hasVertex(source) && this.hasVertex(destination))
        {
            if(!this.getVertexEdges(source).isEmpty() && !this.getVertexEdges(destination).isEmpty())
            {   
                Boolean found = false;
                Queue<Vertex> aux = new Queue<>();
                distance = 0;
                if(!source.isIdentical(destination))
                {
                    this.clearColors();
                    if(!this.isEmpty())
                    {
                        aux.push(this.vertices.getElement(this.vertices.indexOfElement(source)));
                        this.vertices.getElement(this.vertices.indexOfElement(source)).setColor(Vertex.BLACK_COLOR);
                        while(!aux.isEmpty())
                        {
                            Vertex vertex = aux.front();
                            for(Edge edge : this.getVertexEdges(vertex))
                            {
                                Vertex oppositeVertex = edge.getOppositeVertex(vertex);
                                if(oppositeVertex != null)
                                {
                                    if(!oppositeVertex.isBlack())
                                    {
                                        aux.push(oppositeVertex);
                                        oppositeVertex.setColor(Vertex.BLACK_COLOR);
                                    }
                                }
                                else
                                {
                                    throw new Exception("Null vertex");
                                }
                                if(oppositeVertex.getName().equals(destination.getName()))
                                {
                                    found = true;
                                }
                            }
                            aux.pop();
                            distance++;
                            if(found)
                            {
                                break;
                            }
                        }
                        this.clearColors();
                    }
                    distance = ((found) ? Math.abs(distance) : Graph.NOT_FOUND);
                }
            }
        }
        return distance;
    }
    
    public void printVertexDistance(Vertex source, Vertex destination) throws Exception
    {
        Integer distance = this.calculateVertexDistance(source,destination);
        String aux = "d(" + source.getName() + "," + destination.getName() + ") = ";
        aux += ((!Objects.equals(distance,Graph.NOT_FOUND)) ? distance : "not found");
        System.out.println(aux);
    }
    
    public Boolean isEmpty()
    {
        return (this.vertices.isEmpty());
    }
    
    public Boolean hasPath(Vertex source, Vertex destination) throws Exception
    {
        return (!Objects.equals(this.calculateVertexDistance(source,destination),Graph.NOT_FOUND));
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        Graph graph = (Graph)super.clone();
        graph.setVertices(new LinkedList<>());
        return graph;
    }
    
    @Override
    public String toString()
    {
        return "Graph" +
               "\n{" + 
               "\n\tname: " + this.name +
               "\n\tsymbol: " + this.symbol +
               "\n\tvertices: " + this.vertices +
               "\n}";
    }
}
