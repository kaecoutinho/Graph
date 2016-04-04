// LinkedListNode.java
// Kaê Angeli Coutinho
// MIT license

package datastructures;

import java.io.Serializable;

public class LinkedListNode<Type> extends Object implements Serializable
{
    // Properties
    
    private Type data;
    private LinkedListNode<Type> previous;
    private LinkedListNode<Type> next;
    
    // Constructors
    
    public LinkedListNode()
    {
        this(null,null,null);
    }
    
    public LinkedListNode(Type data)
    {
        this(data,null,null);
    }
    
    public LinkedListNode(Type data, LinkedListNode<Type> previous, LinkedListNode<Type> next)
    {
        super();
        this.data = data;
        this.previous = previous;
        this.next = next;
    }
    
    // Instance Methods
    
    public void setData(Type data)
    {
        this.data = data;
    }
    
    public void setPrevious(LinkedListNode<Type> previous)
    {
        this.previous = previous;
    }
    
    public void setNext(LinkedListNode<Type> next)
    {
        this.next = next;
    }
    
    public Type getData()
    {
        return this.data;
    }
    
    public LinkedListNode<Type> getPrevious()
    {
        return this.previous;
    }
    
    public LinkedListNode<Type> getNext()
    {
        return this.next;
    }
    
    public void print(Boolean techinicalDetails)
    {
        String message = "\n LinkedListNode\n";
        if(techinicalDetails)
        {
            message += "\n" + (((this.previous == null) ? " null <- " : " [" + this.previous.data + "] <- ") + "[" +  this.data + "]" + ((this.next == null) ? " -> null" : " -> [" + this.next.data + "]"));
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
        return "LinkedListNode" +
               "\n{" + 
               "\n\tdata: " + this.data +
               "\n\tprevious: " + ((this.previous == null) ? "null" : this.previous.data) +
               "\n\tnext: " + ((this.next == null) ? "null" : this.next.data) +
               "\n}";
    }
}
