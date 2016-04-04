// QueueIterator.java
// Kaê Angeli Coutinho
// MIT license

package datastructures;

import java.io.Serializable;
import java.util.Iterator;

public class QueueIterator<Type> extends Object implements Iterator<Type>, Serializable
{
    // Properties
    
    private QueueNode<Type> current;

    // Constructors
    
    public QueueIterator(QueueNode<Type> node)
    {
        this.current = node;
    }

    // Instance Methods
    
    @Override
    public boolean hasNext()
    {
        return this.current != null;
    }

    @Override
    public Type next()
    {
        if(!hasNext())       
        {
            try
            {
                throw new Exception("Out of bounds");
            }
            catch (Exception exception)
            {
                System.err.println(exception);
            }
        }
        Type data = this.current.getData();
        this.current = current.getNext();
        return data;
    }	
}
