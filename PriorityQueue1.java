/*-----------------------------------------------------------------------
THOMAS TRAN  CSI 2110
Priority queue implemented with a basic ordered array with capacity
Uses the Comparator implemented in LabelledPoint to compares keys
 ---------------------------------------------------------------------*/

//IMPORTS
import java.util.ArrayList;

public class PriorityQueue1 implements PriorityQueueIF{

    /* CLASS VARIABLES */

    //Creates an empty storage array for the queue
    private LabelledPoint[] queue;
    //Variable for size
    private int size;
    //Variable for Capacity
    private int capacity;

    /* CONSTRUCTORS */

    //Constructor to create empty queues
    public PriorityQueue1(int capacity) {

        queue = new LabelledPoint[capacity];
        this.capacity = capacity;
        size = 0;
    }

    //Constructor that creates a queue from a List
    public PriorityQueue1(ArrayList<LabelledPoint> list){
        int size = list.size();
        this.size = 0;
        capacity = size;
        queue = new LabelledPoint[size];
        for (int i = 0; i < size; i++){
            offer(list.get(i));
        }
    }

    /*METHODS PROVIDED BY PRIORITY QUEUE INTERFACE*/

    //Method to insert elements into the queue, checks if the size hasn't exceeded capacity
    @Override
    public boolean offer(Object o) {
        if (size != capacity) {
            LabelledPoint x = (LabelledPoint) o;

            //Using Comparator, finds the index element to insert starting from the Front
            //Check if Insertion's distance is smaller than array's element
            int index = -1;
            for (int i = 0 ; i < size; i ++){
                if (x.compareTo(queue[i]) < 0){
                    index = i;
                    break;
                }
            }

            //Inserts the element by iterating through the back, moving elements one index forward, then inserts
            if (index != -1) {
                for (int i = size; i > index; i--) {
                    queue[i] = queue[i - 1];
                }
                queue[index] = x;
            } else {
                //If index still remains to be -1, it means the new element is max priority (highest distance)
                queue[size] = x;
            }
            size++;
            return true;
        }
        return false;
    }

    //Removes the element with max priority (at the back)
    @Override
    public Object poll() {
        if (!isEmpty()) {
            Object o = queue[size - 1];
            queue[size - 1] = null;
            size--;
            return o;
        } else {
            return null;
        }
    }

    //Checks the max priority element
    @Override
    public Object peek() {
        if (isEmpty()) return null;
        return queue[size-1];
    }

    //Returns size
    @Override
    public int size() {
        return size;
    }

    //Checks if the queue is empty
    @Override
    public boolean isEmpty() {
        return size==0;
    }
}
