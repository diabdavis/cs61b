public class ArrayDeque<Item>
{

//         0 1  2 3 4 5 6 7
// items: [6 9 -1 2 0 0 0 0 ...]
// size: 5

/* Invariants:
 addLast: The next item we want to add, will go into position size
 getLast: The item we want to return is in position size - 1
 size: The number of items in the list should be size.
*/
    private Item[] items;
    private int size;
    private int front;
    private int back;
    private int back_placeholder;

    /** Creates an empty list. */
    public ArrayDeque()
    {
        items = (Item[]) new Object[8];
        size = 0;
        front = 0;
        back = 0;
        back_placeholder = items.length;
    }

    /** Resizes the underlying array to the target capacity. */
    private void resize(int capacity)
    {

        Item[] a = (Item[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, back + 1);
        int x = items.length - front;
        System.arraycopy(items, front, a, a.length - x, x);
        items = a;
        front = a.length - x;
        back_placeholder = items.length;
    }

    /** Method that halfs the size of the array
    if usage factor < 0.25 */
    private void checkUsageFactor()
    {
        if (items.length >= 16)
        {
            if (100 * size / items.length < 25)
            {
                this.resize(items.length / 2);
            }
        }
    }

    /** Inserts x into the front of the list. */
    public void addFirst(Item x)
    {
        if (size == items.length)
        {
            resize(size * 2);
        }
        else
        {

        }
        if (size == 0)
        {
            items[0] = x;
            size++;
        }
        else if (items[items.length - 1] == null)
        {
            items[items.length - 1] = x;
            front = items.length - 1;
            size++;
        }
        else
        {
            items[front - 1] = x;
            front--;
            size++;
        }
    }

    /** Inserts x into the back of the list. */
    public void addLast(Item x)
    {
        if (size == items.length)
        {
            resize(size * 2);
        }
        else
        {

        }
        if (size == 0)
        {
            items[0] = x;
            front = 0;
            back = 0;
            size++;
        }
        else
        {
            items[back + 1] = x;
            back++;
            size++;
        }
    }

     /** Method to tell us whether or not our array
    deque is empty */
     public boolean isEmpty()
    {
        if (size == 0)
        {
            return true;
        }
        return false;
    }

    /** Returns the number of items in the list. */
    public int size()
    {
        return size;
    }

    /** Prints out the array in order */
    public void printDeque()
    {
        int x = front;
        for(int i = 0; i < size; i++)
        {
            if (i == 0)
            {
                System.out.print(items[x]);
                x++;
            }
            else if (x >= items.length)
            {
               x = 0;
               System.out.print(" " + items[x]);
               x++;
            }
            else
            {
               System.out.print(" " + items[x]);
               x++;
            }
        }
        System.out.println();
    }

    /** Deletes item from the front of the array and
      * returns deleted item. */
    public Item removeFirst()
    {
        Item x = items[front];
        items[front] = null;
        if (front == items.length - 1 | front == 0)
        {
            front = 0;
        }
        else
        {
            front++;
        }
        size--;
        this.checkUsageFactor();
        return x;

    }

    /** Deletes item from the back of the array and
      * returns deleted item. */
    public Item removeLast()
    {
        Item x = items[back];
        items[back] = null;
        if (back == 0)
        {
            if (items[back] == null & back_placeholder >= 0)
            {
                items[back_placeholder] = null;
                back_placeholder--;
            }
            else
            {
                items[0] = null;
            }
        }
        else
        {
            back--;
        }
        size--;
        this.checkUsageFactor();
        return x;
    }

    /** Gets the ith item in the list (0 is the front). */
    public Item get(int i)
    {
        if (i > size - 1)
        {
            return null;
        }
        else if (front + i > items.length - 1)
        {
            int x = items.length - front;
            return items[i - x];
        }
        else
        {
            return items[front + i];
        }
    }

    public static void main(String[] args)
    {
        ArrayDeque<Integer> L = new ArrayDeque<>();
        L.addFirst(0);
        L.addFirst(1);
        L.addFirst(2);
        L.addLast(3);
        L.addFirst(5);
        L.addLast(15);
        L.addLast(10);
        L.addFirst(25);
        L.addFirst(20);
        L.removeLast();
        L.removeFirst();
        L.removeFirst();
        L.removeFirst();
        L.removeLast();
        L.removeLast();
        System.out.println(L.get(0));
        System.out.println(L.get(1));
        System.out.println(L.get(2));
        System.out.println(L.get(3));
        System.out.println(L.get(4));
        System.out.println(L.get(5));
        System.out.println(L.get(6));
    }

}
