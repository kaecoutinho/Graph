// LinkedList.java
// Kaê Angeli Coutinho
// MIT license

package datastructures;

import java.io.Serializable;
import java.util.Iterator;

public class LinkedList<Type> extends Object implements Iterable<Type>, Serializable
{
    // Properties
    
    private LinkedListNode<Type> root;
    
    // Constructors
    
    public LinkedList()
    {
        this(null);
    }
    
    public LinkedList(LinkedListNode<Type> root)
    {
        super();
        this.root = root;
    }
    
    // Instance Methods
    
    public Boolean isEmpty()       
    {
        return this.root == null;
    }
    
    public Integer size()
    {
        LinkedListNode<Type> aux;
        Integer counter = 0;
        if(!this.isEmpty())
        {
            aux = this.root;
            while(aux != null)
            {
                counter++;
                aux = aux.getNext();
            }
        }
        return counter;
    }
    
    public Integer length()
    {
        return this.size();
    }
    
    public Integer elementOccurrences(Type data)
    {
        LinkedListNode<Type> aux;
        Integer counter = 0;
        if(!this.isEmpty())
        {
            aux = this.root;
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
    
    public Type getFirstElement() throws Exception
    {
        if(this.isEmpty())
        {
            throw new Exception("Empty list");
        }
        else
        {
            return this.root.getData();
        }
    }
    
    public Type getLastElement() throws Exception
    {
        if(this.isEmpty())
        {
            throw new Exception("Empty list");
        }
        else
        {
            LinkedListNode<Type> aux;
            aux = this.root;
            while(aux.getNext() != null)
            {
                aux = aux.getNext();
            }
            return aux.getData();
        }
    }
    
    public Type getElement(Integer index) throws Exception
    {
        if(this.isEmpty())
        {
            throw new Exception("Empty list");
        }
        else
        {
            LinkedListNode<Type> aux;
            Integer counter = 0;
            aux = this.root;
            while(aux != null)
            {
                if(counter.equals(index))
                {
                    return aux.getData();
                }
                counter++;
                aux = aux.getNext();
            }
            throw new Exception("Index out of bounds");
        }
    }
    
    public LinkedListNode<Type> getFirstNode() throws Exception
    {
        if(this.isEmpty())
        {
            throw new Exception("Empty list");
        }
        else
        {
            return this.root;
        }
    }
    
    public LinkedListNode<Type> getLastNode() throws Exception
    {
        if(this.isEmpty())
        {
            throw new Exception("Empty list");
        }
        else
        {
            LinkedListNode<Type> aux;
            aux = this.root;
            while(aux.getNext() != null)
            {
                aux = aux.getNext();
            }
            return aux;
        }
    }
    
    public LinkedListNode<Type> getNode(Integer index) throws Exception
    {
        if(this.isEmpty())
        {
            throw new Exception("Empty list");
        }
        else
        {
            LinkedListNode<Type> aux;
            Integer counter = 0;
            aux = this.root;
            while(aux != null)
            {
                if(counter.equals(index))
                {
                    return aux;
                }
                counter++;
                aux = aux.getNext();
            }
            throw new Exception("Index out of bounds");
        }
    }
    
    public Integer indexOfElement(Type data) throws Exception
    {
        if(this.isEmpty())
        {
            throw new Exception("Empty list");
        }
        else
        {
            LinkedListNode<Type> aux;
            Integer index = 0;
            aux = this.root;
            while(aux != null)
            {
                if(aux.getData().equals(data))
                {
                    return index;
                }
                index++;
                aux = aux.getNext();
            }
            return null;
        }
    }
    
    public Boolean containsElement(Type data) throws Exception
    {
        if(this.isEmpty())
        {
            throw new Exception("Empty list");
        }
        else
        {
            LinkedListNode<Type> aux;
            Boolean result = false;
            aux = this.root;
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
    
    public void replaceElement(Type data, Integer index) throws Exception
    {
        this.getNode(index).setData(data);
    }
    
    public void addFirst(Type data)
    {
        LinkedListNode<Type> newNode = new LinkedListNode<>(data,null,this.root);
        if(!this.isEmpty())
        {
            this.root.setPrevious(newNode);
        }
        this.root = newNode;
    }
    
    public void addLast(Type data)
    {
        LinkedListNode<Type> newNode;
        LinkedListNode<Type> aux;
        if(this.isEmpty())
        {
            newNode = new LinkedListNode<>(data,null,null);
            this.root = newNode;
        }
        else
        {
            aux = this.root;
            while(aux.getNext() != null)
            {
                aux = aux.getNext();
            }
            newNode = new LinkedListNode<>(data,aux,null);
            aux.setNext(newNode);
        }
    }
    
    public void add(Type data, Integer index) throws Exception
    {
        LinkedListNode<Type> newNode;
        LinkedListNode<Type> aux;
        Boolean result = false;
        Integer counter = 0;
        if(this.isEmpty())
        {
            if(counter.equals(index))
            {
                newNode = new LinkedListNode<>(data,null,null);
                this.root = newNode;
            }
            else
            {
                throw new Exception("Empty list");
            }
        }
        else
        {
            aux = this.root;
            while(aux != null)
            {
                if(counter.equals(index))
                {
                    break;
                }
                counter++;
                aux = aux.getNext();
            }
            if(counter.equals(index) && aux != null)
            {
                if(aux.getPrevious() != null)
                {
                    newNode = new LinkedListNode<>(data,aux.getPrevious(),aux);
                    aux.getPrevious().setNext(newNode);
                }
                else
                {
                    newNode = new LinkedListNode<>(data,null,aux);
                    this.root = newNode;
                }
                aux.setPrevious(newNode);
            }
            else
            {
                throw new Exception("Index out of bounds");
            }
        }
    }
    
    public void addMultiple(Type ... data)
    {
        for(Type item : data)
        {
            this.addLast(item);
        }
    }
    
    public void removeFirst() throws Exception
    {
        LinkedListNode<Type> aux;
        if(this.isEmpty())
        {
            throw new Exception("Empty list");
        }
        {
            if(this.root.getNext() == null)
            {
                this.root = null;
            }
            else
            {
                this.root.getNext().setPrevious(null);
                aux = this.root.getNext();
                this.root.setNext(null);
                this.root = aux;
            }
        }
    }
    
    public void removeLast() throws Exception
    {
        LinkedListNode<Type> aux;
        if(this.isEmpty())
        {
            throw new Exception("Empty list");
        }
        {
            aux = this.root;
            while(aux.getNext() != null)
            {
                aux = aux.getNext();
            }
            if(aux.getPrevious() == null)
            {
                this.root = null;
            }
            else
            {
                aux.getPrevious().setNext(null);
                aux.setPrevious(null);
            }
        }
    }
    
    public void remove(Integer index) throws Exception
    {
        LinkedListNode<Type> aux;
        Integer counter = 0;
        if(this.isEmpty())
        {
            throw new Exception("Empty list");
        }
        else
        {
            aux = this.root;
            while(aux != null)
            {
                if(counter.equals(index))
                {
                    break;
                }
                counter++;
                aux = aux.getNext();
            }
            if(aux != null && counter.equals(index))
            {
                if(aux.getPrevious() == null)
                {
                    if(aux.getNext() == null)
                    {
                        this.root = null;
                    }
                    else
                    {
                        aux.getNext().setPrevious(null);
                        this.root = aux.getNext();
                        aux.setNext(null);
                    }
                }
                else
                {
                    if(aux.getNext() == null)
                    {
                        aux.getPrevious().setNext(null);
                        aux.setPrevious(null);
                    }
                    else
                    {
                        aux.getPrevious().setNext(aux.getNext());
                        aux.getNext().setPrevious(aux.getPrevious());
                        aux.setPrevious(null);
                        aux.setNext(null);
                    }
                }
            }
            else
            {
                throw new Exception("Index out of bounds");
            }
        }
    }
    
    public Boolean removeElement(Type data) throws Exception
    {
        LinkedListNode<Type> aux;
        Boolean result = false;
        if(this.isEmpty())
        {
            throw new Exception("Empty list");
        }
        else
        {
            aux = this.root;
            while(aux != null)
            {
                if(aux.getData().equals(data))
                {
                    break;
                }
                aux = aux.getNext();
            }
            if(aux != null)
            {
                if(aux.getPrevious() == null)
                {
                    if(aux.getNext() == null)
                    {
                        this.root = null;
                    }
                    else
                    {
                        aux.getNext().setPrevious(null);
                        this.root = aux.getNext();
                        aux.setNext(null);
                    }
                }
                else
                {
                    if(aux.getNext() == null)
                    {
                        aux.getPrevious().setNext(null);
                        aux.setPrevious(null);
                    }
                    else
                    {
                        aux.getPrevious().setNext(aux.getNext());
                        aux.getNext().setPrevious(aux.getPrevious());
                        aux.setPrevious(null);
                        aux.setNext(null);
                    }
                }
                result = true;
            }
            return result;
        }
    }
    
    public void print(Boolean techinicalPrint)
    {
        String message;
        LinkedListNode<Type> aux;
        if(!this.isEmpty())
        {
            aux = this.root;
            message = "\n LinkedList\n" + ((!techinicalPrint) ? "\n [" : "");
            while(aux != null)
            {
                if(techinicalPrint)
                {
                    message += "\n" + (((aux.getPrevious() == null) ? " null\n <- " : " <- ") + "[" +  aux.getData() + "]" + ((aux.getNext() == null) ? " ->\n null" : " -> "));
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
            message = "\n Empty LinkedList\n";
        }
        System.out.println(message);
    }
    
    public String printToString()
    {
        String message = "LinkedList [";
        LinkedListNode<Type> aux;
        if(!this.isEmpty())
        {
            aux = this.root;
            while(aux != null)
            {
                message += aux.getData() + ((aux.getNext() == null) ? "]" : ", ");
                aux = aux.getNext();
            }
        }
        else
        {
            message = "Empty LinkedList";
        }
        return message;
    }
    
    public Type [] toArray() throws Exception
    {
        Type [] array = (Type [])new Object[this.size()];
        Integer index = 0;
        LinkedListNode<Type> aux;
        if(this.isEmpty())
        {
            throw new Exception("Empty list");
        }
        else
        {
            aux = this.root;
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
        this.root = null;
    }
    
    public void reverse() throws Exception
    {
        LinkedList<Type> auxList;
        LinkedListNode<Type> aux;
        if(this.isEmpty())
        {
            throw new Exception("Empty list");
        }
        else
        {
            auxList = new LinkedList<>(this.root);
            this.root = null;
            aux = auxList.root;
            while(aux != null)
            {
                this.addFirst(aux.getData());
                aux = aux.getNext();
            }
        }
    }
    
    @Override
    public Iterator<Type> iterator() 
    {
        return (Iterator<Type>)new LinkedListIterator<>(this.root);
    }
    
    @Override
    public String toString()
    {
        return "LinkedList" +
               "\n{" + 
               "\n\troot: " + this.root +
               "\n}";
    }
}
