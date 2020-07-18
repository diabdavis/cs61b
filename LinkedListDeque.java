/** Linked List Deque class */
public class LinkedListDeque<Object_Type>
{
    /** Helper class that defines how we will construct lists
    under the hood */
    private class List
    {
        public Object_Type item;
        public List next;
        public List prev;

        public List(Object_Type i, List n)
        {
            item = i;
            next = n;
        }
    }

    /** The first item is at sentinel.next if it exists */
    private List sentinel;
    private int size;

    /** Creates an empty Linked List Deque */
    public LinkedListDeque()
    {
        sentinel = new List(null, null);
        size = 0;
    }

    /** Create Linked List Deque */
    public LinkedListDeque(Object_Type x)
    {
        sentinel = new List(null, null);
        sentinel.next = new List(x, sentinel);
        sentinel.prev = sentinel.next;
        sentinel.next.prev = sentinel;
        size = 1;
    }

     /** Returns the ith item of the list using iteration */
    public Object_Type get(int i)
    {
        if (i > size - 1)
        {
            return null;
        }
        List p = sentinel;
        for (int n = 0; n < i; n++)
        {
            p = p.next;
        }
        return p.next.item;
    }

    /** Helper method for recursive get method */
    private Object_Type helper(int i, List p)
    {
        if (i == 0)
        {
            return p.item;
        }
        else
        {
            return helper(i - 1, p.next);
        }
    }

    /** Returns the ith item of the list using recursion */
    public Object_Type getRecursive(int i)
    {
       if (i > size - 1)
       {
          return null;
       }
       else
       {
          return helper(i , sentinel.next);
       }
    }

    /** Adds x to the front of the list. */
    public void addFirst(Object_Type x)
    {
        sentinel.next = new List(x, sentinel.next);
        sentinel.next.prev = sentinel;
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    /** Adds an x to the end of the list. */
    public void addLast(Object_Type x)
    {
        sentinel.prev.next = new List(x, sentinel);
        sentinel.prev.next.prev = sentinel.prev;
        sentinel.prev = sentinel.prev.next;
        size += 1;
    }

    /** Method to tell us whether or not our linked list
    deque is empty */
    public boolean isEmpty()
    {
        if (size == 0)
        {
            return true;
        }
        return false;
    }

    /** Removes and returns the first item in the list and connects the
    list to the rest of itself. */
    public Object_Type removeFirst()
    {
        if (size == 1)
        {
            sentinel.next = null;
            sentinel.prev = null;
            return null;
        }
        else if (size > 1)
        {
            List p = new List(sentinel.next.item, sentinel.next);
            sentinel.next.next.prev = sentinel;
            sentinel.next.prev = null;
            sentinel.next = sentinel.next.next;
            size -= 1;
            return p.item;
        }
        else
        {
            return null;
        }
    }

    /** Removes and returns the last item in the list and connects the
    list to the rest of itself. */
    public Object_Type removeLast()
    {
        if (size == 1)
        {
            sentinel.next = null;
            sentinel.prev = null;
            return null;
        }
        else if (size > 1)
        {
            List p = new List(sentinel.prev.item, sentinel.next);
            sentinel.prev.prev.next = sentinel;
            sentinel.prev.next = null;
            sentinel.prev = sentinel.prev.prev;
            size -= 1;
            return p.item;
        }
        else
        {
            return null;
        }
    }

    /** Returns the size of the list */
    public int size()
    {
        return size;
    }

    /** Prints out the list in order */
    public void printDeque()
    {
        for(int i = 0; i < size; i++)
        {
            if (i == 0)
            {
                System.out.print(get(i));
            }
            else
            {
               System.out.print(" " + get(i));
            }
        }
        System.out.println();
    }

    public static void main(String[] args)
    {
        /* Creates a list of one integer, namely 10 */
        LinkedListDeque<Integer> L = new LinkedListDeque<>(5);
        L.addFirst(10);
        L.addFirst(15);
        L.addLast(0);
        L.printDeque();
        System.out.println(L.removeFirst());
        L.printDeque();
        L.addFirst(20);
        L.printDeque();
        System.out.println(L.removeLast());
        L.printDeque();
    }
}



