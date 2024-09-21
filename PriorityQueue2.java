/*-----------------------------------------------------------------------
THOMAS TRAN CSI 2110
Priority queue implemented with a max heap array
Uses the Comparator implemented in LabelledPoint to compares keys
It is created using the Priority Queues Heap already implemented from CSI2110 LAB 4
 ---------------------------------------------------------------------*/

//IMPORTS
import java.util.ArrayList;

public class PriorityQueue2 implements PriorityQueueIF{

    /* CLASS VARIABLES */

    //Creates an empty storage array for the queue
    private LabelledPoint[] queue;
    //Variable for the tail
    private int tail;
    //Variable for capacity
    private int capacity;

    /* CONSTRUCTORS */

    //Constructor to create empty queues
    public PriorityQueue2(int capacity){
        queue = new LabelledPoint[capacity];
        this.capacity = capacity;
        tail = -1;
    }

    //Constructor that creates a queue from a List
    public PriorityQueue2(ArrayList<LabelledPoint> list){
        int size = list.size();
        queue = new LabelledPoint[size];
        this.capacity = size;
        tail = -1;
        for (int i = 0; i < size; i++){
            offer(list.get(i));
        }
    }

    /*OVERRIDE METHODS PROVIDED BY PRIORITY QUEUE INTERFACE */

    //Method to insert elements into the queue, checks if the size hasn't exceeded capacity
    //If it can insert, inserts at the bottom of the heap and triggers upHeap operations
    @Override
    public boolean offer(Object o) {
        if (tail != capacity-1) {
            queue[++tail] = (LabelledPoint) o;
            upHeap(tail);
            return true;
        } else {
            return false;
        }
    }

    //Removes the element with max priority (the root) and triggers recursive downHeap operations
    @Override
    public Object poll() {
        if(isEmpty()) return null;
        LabelledPoint ret = queue[0];
        if(tail == 0){
            tail = -1;
            queue[0] = null;
            return ret;
        }
        queue[0] = queue[tail];
        queue[tail--] = null;
        downHeap(0);
        return ret;
    }

    //Checks the max priority element
    @Override
    public Object peek() {
        if (isEmpty()) return null;
        return queue[0];
    }

    //Returns size
    @Override
    public int size() {
       return tail+1;
    }

    //Checks if the queue is empty
    @Override
    public boolean isEmpty() {
        return (tail == -1);
    }

    /* HEAP OPERATION METHODS */
    /* FOLLOWING METHODS ARE BASED ON LAB 4 */

    //Recursive upHeap operations
    private void upHeap(int location){
        if(location == 0) return;
        int parent = parent(location);

        //Checks if parent's is smaller than the child's distance
        if(queue[parent].compareTo(queue[location]) < 0){
            swap(location,parent);
            upHeap(parent);
        }
    }

    //Recursive downHeap operations
    private void downHeap(int location){
        int left = (location*2) +1;
        int right = (location*2) +2;

        if(left > tail) return;
        if(left == tail){
            //Checks if the parent is smaller than its chid's distance
            if(queue[location].compareTo(queue[left]) < 0){
                swap(location,left);
            }
            return;
        }
        int toSwap= (queue[left].compareTo(queue[right]) > 0)? left:right;
        if(queue[location].compareTo(queue[toSwap]) < 0){
            swap(location,toSwap);
            downHeap(toSwap);
        }
    }

    //Swapping elements
    private void swap(int location1, int location2){
        LabelledPoint temp = queue[location1];
        queue[location1] = queue[location2];
        queue[location2] = temp;
    }


    //Finding parent given an element index
    private int parent(int location){
        return (location-1)/2;
    }
}
