// QueueNode.java
// Kaê Angeli Coutinho
// MIT license

package datastructures;

import java.io.Serializable;

public class QueueNode<Type> extends Object implements Serializable
{
    // Properties
    
    private Type data;
    private QueueNode<Type> next;
    
    // Constructors
    
    public QueueNode()
    {
        this(null,null);
    }
    
    public QueueNode(Type data)
    {
        this(data,null);
    }
    
    public QueueNode(Type data, QueueNode<Type> next)
    {
        super();
        this.data = data;
        this.next = next;
    }
    
    // Instance Methods
    
    public void setData(Type data)
    {
        this.data = data;
    }
    
    public void setNext(QueueNode<Type> next)
    {
        this.next = next;
    }
    
    public Type getData()
    {
        return this.data;
    }
    
    public QueueNode<Type> getNext()
    {
        return this.next;
    }
    
    public void print(Boolean techinicalDetails)
    {
        String message = "\n QueueNode\n";
        if(techinicalDetails)
        {
            message += "\n [" +  this.data + "]" + ((this.next == null) ? " -> null" : " -> [" + this.next.data + "]");
        }
        else
        {
            message += "\n [" + this.data + "]";
        }
        System.out.println(message);
    }
    
    @Override
    public String toString()
    {
        return "QueueNode" +
               "\n{" + 
               "\n\tdata: " + this.data +
               "\n\tnext: " + ((this.next == null) ? "null" : this.next.data) +
               "\n}";
    }
}
