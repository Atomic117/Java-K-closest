/*-----------------------------------------------------------------------
THOMAS TRAN CSI 2110
Priority queue implemented with the Priority queue import
The Imported Priority Queue's comparator is overrided to use a max priority instead of min
 ---------------------------------------------------------------------*/

//IMPORTS
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueue3 implements PriorityQueueIF{

    /* CLASS VARIABLES */

    //Creates the priority queue
    PriorityQueue<LabelledPoint> queue;
    //Creates the variable for capacity
    int capacity;

    /* CONSTRUCTORS */

    //Constructor to create empty queues
    public PriorityQueue3(int capacity){
        //OVERWRITING THE IMPORTED COMPARATOR
        queue = new PriorityQueue<LabelledPoint>(
                new Comparator<LabelledPoint>() {
                    @Override
                    public int compare(LabelledPoint o1, LabelledPoint o2) {
                        return (o2.compareTo(o1));
                    }
                }
        );
        this.capacity = capacity;
    }

    //Constructor to create queues from ArrayList
    public PriorityQueue3(ArrayList<LabelledPoint> list){
        int size = list.size();
        queue = new PriorityQueue<>(size);
        for (int i = 0; i < size; i++){
            offer(list.get(i));
        }
    }


    /*METHODS PROVIDED BY PRIORITY QUEUE INTERFACE*/

    //Method to insert elements into the queue, checks if the size hasn't exceeded capacity
    @Override
    public boolean offer(Object o) {
        if (size() != capacity){
            queue.offer((LabelledPoint) o);
            return true;
        } else {
            return false;
        }
    }

    //Removes max priority
    @Override
    public Object poll() {
        return queue.poll();
    }

    //Checks max priority
    @Override
    public Object peek() {
        return queue.peek();
    }

    //Return size
    @Override
    public int size() {
        return queue.size();
    }


    //Return if the queue is empty
    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
