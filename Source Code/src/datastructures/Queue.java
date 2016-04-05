// Queue.java
// Kaê Angeli Coutinho
// MIT license

package datastructures;

import java.io.Serializable;
import java.util.Iterator;

public class Queue<Type> extends Object implements Iterable<Type>, Serializable
{
    // Properties

    private Integer size;
    private QueueNode<Type> head;
    private QueueNode<Type> tail;
    
    // Constructors
    
    public Queue()
    {
        super();
        this.size = 0;
        this.head = null;
        this.tail = null;
    }
    
    // Instance Methods
    
    public Boolean isEmpty()
    {
        return head == null;
    }
    
    public Integer size()
    {
        return this.size;
    }
    
    public Integer length()
    {
        return this.size();
    }
    
    public Integer elementOccurrences(Type data)
    {
        QueueNode<Type> aux;
        Integer counter = 0;
        if(!this.isEmpty())
        {
            aux = this.head;
            while(aux != null)
            {
                if(aux.getData().equals(data))
                {
                    counter++;
                }
                aux = aux.getNext();
            }
        }
        return counter;
    }
    
    public Boolean containsElement(Type data) throws Exception
    {
        if(this.isEmpty())
        {
            throw new Exception("Empty queue");
        }
        else
        {
            QueueNode<Type> aux;
            Boolean result = false;
            aux = this.head;
            while(aux != null)
            {
                if(aux.getData().equals(data))
                {
                    result = true;
                }
                aux = aux.getNext();
            }
            return result;
        }
    }
    
    public Type front() throws Exception
    {
        if(this.isEmpty())
        {
            throw new Exception("Empty queue");
        }
        else
        {
            return this.head.getData();
        }
    }
    
    public Type back() throws Exception
    {
        if(this.isEmpty())
        {
            throw new Exception("Empty queue");
        }
        else
        {
            return this.tail.getData();
        }
    }
    
    public void push(Type data)
    {
        QueueNode<Type> aux;
        aux = this.tail;
        this.tail = new QueueNode<>(data,null);
        if(this.isEmpty())
        {
            this.head = this.tail;
        }
        else
        {
            aux.setNext(this.tail);
        }
        this.size++;
    }
    
    public void multiPush(Type ... data)
    {
        for(Type item : data)
        {
            this.push(item);
        }
    }
    
    public void pop() throws Exception
    {
        QueueNode<Type> aux;
        if(this.isEmpty())
        {
            throw new Exception("Empty queue");
        }
        aux = this.head;
        this.head = aux.getNext();
        aux.setNext(null);
        this.size--;
        if(this.isEmpty())
        {
            this.tail = null;
        }
    }
    
    public void multiPop(Integer times) throws Exception
    {
        for(Integer index = 0; index < times; index++)
        {
            if(!this.isEmpty())
            {
                this.pop();
            }
        }
    }
    
    public void print(Boolean techinicalPrint)
    {
        String message;
        QueueNode<Type> aux;
        if(!this.isEmpty())
        {
            message = "\n Queue\n" + ((!techinicalPrint) ? "\n [" : "");
            aux = this.head;
            while(aux != null)
            {
                if(techinicalPrint)
                {
                    message += "\n [" +  aux.getData() + "]" + ((aux.getNext()== null) ? " ->\n null" : " -> ");
                }
                else
                {
                    message += aux.getData() + ((aux.getNext() == null) ? "]" : ", ");
                }
                aux = aux.getNext();
            }
        }
        else
        {
            message = "\n Empty Queue\n";
        }
        System.out.println(message);
    }
    
    public Type [] toArray() throws Exception
    {
        Type [] array = (Type [])new Object[this.size()];
        Integer index = 0;
        QueueNode<Type> aux;
        if(this.isEmpty())
        {
            throw new Exception("Empty queue");
        }
        else
        {
            aux = this.head;
            while(aux != null)
            {
                array[index++] = aux.getData();
                aux = aux.getNext();
            }
            return array;
        }
    }
    
    public void clear()
    {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }
    
    public void reverse() throws Exception
    {
        LinkedList<Type> auxList = new LinkedList<>();
        auxList.addMultiple(this.toArray());
        this.clear();
        auxList.reverse();
        this.multiPush(auxList.toArray());
    }
    
    @Override
    public Iterator<Type> iterator() 
    {
        return (Iterator<Type>)new QueueIterator<>(this.head);
    }
    
    @Override
    public String toString()
    {
        return "Queue" +
               "\n{" + 
               "\n\tsize: " + this.size +
               "\n\thead: " + this.head +
               "\n\ttail: " + this.tail +
               "\n}";
    }
}
